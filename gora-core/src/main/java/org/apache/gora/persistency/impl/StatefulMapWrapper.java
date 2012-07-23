/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.gora.persistency.impl;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.gora.persistency.Deletable;
import org.apache.gora.persistency.DeletableContent;
import org.apache.gora.persistency.Dirtyable;
import org.apache.gora.persistency.Persistent;
import org.apache.gora.persistency.State;
import org.apache.gora.persistency.StatefulDataStructure;
import org.apache.gora.persistency.Tombstone;
import org.apache.gora.persistency.Tombstones;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterators;
import com.google.common.collect.Sets;

/**
 * Wraps maps to provide statefulness in the map.
 * 
 * @param <K>
 *          The key type of the map.
 * @param <V>
 *          The value type of the map.
 */
public class StatefulMapWrapper<K, V> implements Map<K, V>,
    StatefulDataStructure<K>, Dirtyable, Deletable, DeletableContent {

  /**
   * A map entry that cannot be modified.
   * 
   * @param <K>
   * @param <V>
   */
  public static class UnmodifiableEntry<K, V> implements Entry<K, V> {

    private final K k;
    private final V v;

    public UnmodifiableEntry(Entry<K, V> entry) {
      this.k = entry.getKey();
      this.v = entry.getValue();
    }

    public UnmodifiableEntry(K k, V v) {
      this.k = k;
      this.v = v;
    }

    @Override
    public K getKey() {
      return k;
    }

    @Override
    public V getValue() {
      return v;
    }

    @Override
    public V setValue(V value) {
      throw new UnsupportedOperationException();
    }

  }

  /** The map being wrapped */
  private final Map<K, V> delegateMap;
  /** Keeps track of weather or not elements are dirty */
  private final Map<K, Boolean> dirtyMap = new HashMap<K, Boolean>();
  /** Keeps track of weather or not things were deleted and what was deleted */
  private final Map<K, List<V>> deletedMap = new HashMap<K, List<V>>();
  /** Entry set for the state map */
  private final Set<Entry<K, Integer>> stateEntrySet = new Set<Entry<K, Integer>>() {

    @Override
    public int size() {
      return stateMap.size();
    }

    @Override
    public boolean isEmpty() {
      return stateMap.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
      if (o instanceof Entry) {
        Object key = ((Entry<?, ?>) o).getKey();
        Object stateMapValue = stateMap.get(key);
        return stateMapValue != null
            && stateMapValue.equals(((Entry<?, ?>) o).getValue());
      }
      return false;
    }

    @Override
    public Iterator<java.util.Map.Entry<K, Integer>> iterator() {
      return Iterators.transform(stateMap.keySet().iterator(),
          new Function<K, Entry<K, Integer>>() {
            @Override
            public Map.Entry<K, Integer> apply(K input) {
              return new UnmodifiableEntry<K, Integer>(input, stateMap
                  .get(input));
            }
          });
    }

    @Override
    public Object[] toArray() {
      return toArray(new Object[size()]);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] toArray(T[] a) {
      if (a.length < size()) {
        a = (T[]) Array.newInstance(a.getClass().getComponentType(), size());
      }
      Iterator<Entry<K, Integer>> iterator = iterator();
      int i = 0;
      while (iterator.hasNext()) {
        a[i] = (T) iterator.next();
      }
      return a;
    }

    @Override
    public boolean add(java.util.Map.Entry<K, Integer> e) {
      throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
      throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
      for (Object o : c) {
        if (!contains(o)) {
          return false;
        }
      }
      return true;
    }

    @Override
    public boolean addAll(
        Collection<? extends java.util.Map.Entry<K, Integer>> c) {
      throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
      throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
      throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
      throw new UnsupportedOperationException();
    }

  };
  /**
   * The state map - thinly wraps the other state maps to provide a consistent
   * view of this maps state.
   */
  private final Map<K, Integer> stateMap = new Map<K, Integer>() {

    @Override
    public int size() {
      int mainSize = delegateMap.size();
      for (K key : deletedMap.keySet()) {
        if (!delegateMap.containsKey(key)) {
          mainSize++;
        }
      }
      return mainSize;
    }

    @Override
    public boolean isEmpty() {
      return delegateMap.isEmpty() && deletedMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
      return delegateMap.containsKey(key) || deletedMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
      if (value instanceof Integer) {
        switch ((Integer) value) {
        case State.CLEAN:
          for (K key : delegateMap.keySet()) {
            if (!dirtyMap.containsKey(key)) {
              return true;
            }
          }
          return false;
        case State.DIRTY:
          for (K key : dirtyMap.keySet()) {
            if (!deletedMap.containsKey(key)) {
              return true;
            }
          }
          return false;
        case State.DELETED:
          for (K key : deletedMap.keySet()) {
            if (!dirtyMap.containsKey(key)) {
              return true;
            }
          }
          return false;
        case State.OVERWRITTEN:
          for (K key : deletedMap.keySet()) {
            if (dirtyMap.containsKey(key)) {
              return true;
            }
          }
          return false;
        default:
          return false;
        }
      }
      return false;
    }

    @Override
    public Integer get(Object key) {
      if (deletedMap.containsKey(key)) {
        if (dirtyMap.containsKey(key)) {
          return State.OVERWRITTEN;
        }
      }
      if (deletedMap.containsKey(key)) {
        return State.DELETED;
      }
      if (dirtyMap.containsKey(key)) {
        return State.DIRTY;
      }
      if (delegateMap.containsKey(key)) {
        return State.CLEAN;
      }
      return null;
    }

    @Override
    public Integer put(K key, Integer value) {
      throw new UnsupportedOperationException();
    }

    @Override
    public Integer remove(Object key) {
      throw new UnsupportedOperationException();
    }

    @Override
    public void putAll(Map<? extends K, ? extends Integer> m) {
      throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
      StatefulMapWrapper.this.clearStates();
    }

    @Override
    public Set<K> keySet() {
      return Sets.union(deletedMap.keySet(), delegateMap.keySet());
    }

    @Override
    public Collection<Integer> values() {
      return Collections2.transform(keySet(), new Function<K, Integer>() {
        @Override
        public Integer apply(K input) {
          return StatefulMapWrapper.this.stateMap.get(input);
        }
      });
    }

    @Override
    public Set<java.util.Map.Entry<K, Integer>> entrySet() {
      return stateEntrySet;
    }

  };

  public StatefulMapWrapper(Map<K, V> delegateMap) {
    this.delegateMap = delegateMap;
  }

  @Override
  public int size() {
    return delegateMap.size();
  }

  @Override
  public boolean isEmpty() {
    return delegateMap.isEmpty();
  }

  @Override
  public boolean containsKey(Object key) {
    return delegateMap.containsKey(key);
  }

  @Override
  public boolean containsValue(Object value) {
    return delegateMap.containsValue(value);
  }

  @Override
  public V get(Object key) {
    return delegateMap.get(key);
  }

  @Override
  public V put(K key, V value) {
    if (Tombstones.isTombstone(value)) {
      return remove(key);
    } else {
      V oldValue = delegateMap.put(key, value);
      dirtyMap.put(key, true);
      if (oldValue != null)
        appendOrInitializeMapList(deletedMap, key, oldValue);
      return oldValue;
    }
  }

  private static <K, V> void appendOrInitializeMapList(Map<K, List<V>> map,
      K key, V oldValue) {
    List<V> removals = map.get(key);
    if (removals == null) {
      removals = new ArrayList<V>();
      map.put(key, removals);
    }
    removals.add(oldValue);
  }

  @SuppressWarnings("unchecked")
  @Override
  public V remove(Object key) {
    V oldValue = delegateMap.get(key);
    if (oldValue == null) {
      return null;
    } else if (oldValue instanceof Persistent) {
      appendOrInitializeMapList(deletedMap, (K) key,
          (V) ((Persistent) oldValue).getTombstone());
    } else {
      appendOrInitializeMapList(deletedMap, (K) key, oldValue);
    }
    return oldValue;
  }

  @Override
  public void putAll(Map<? extends K, ? extends V> m) {
    for (Entry<? extends K, ? extends V> entry : m.entrySet()) {
      put(entry.getKey(), entry.getValue());
    }
  }

  @Override
  public void clear() {
    List<K> keyList = new ArrayList<K>(delegateMap.keySet());
    for (K key : keyList) {
      remove(key);
    }
  }

  @Override
  public Set<K> keySet() {
    return Collections.unmodifiableSet(delegateMap.keySet());
  }

  @Override
  public Collection<V> values() {
    return Collections.unmodifiableCollection(delegateMap.values());
  }

  @Override
  public Set<java.util.Map.Entry<K, V>> entrySet() {
    Set<Entry<K, V>> entries = Collections
        .unmodifiableSet(new CollectionSet<Entry<K, V>>(Collections2.transform(
            delegateMap.entrySet(), new Function<Entry<K, V>, Entry<K, V>>() {

              @Override
              public java.util.Map.Entry<K, V> apply(
                  java.util.Map.Entry<K, V> input) {
                return new UnmodifiableEntry<K, V>(input);
              }
            })));
    return entries;
  }

  public int getState(K key) {
    Boolean dirty = dirtyMap.get(key);
    boolean realDirty = dirty != null && dirty;
    List<V> deleted = deletedMap.get(key);
    boolean realDeleted = deleted != null;
    if (realDirty && realDeleted) {
      return State.OVERWRITTEN;
    } else if (realDirty) {
      return State.DIRTY;
    } else if (realDeleted) {
      return State.DELETED;
    } else {
      return State.CLEAN;
    }
  }

  void setDeletions(K key, List<V> deletions) {
    this.deletedMap.put(key, new ArrayList<V>(deletions));
  }

  public List<V> getRemovals(K key) {
    return Collections.unmodifiableList(this.deletedMap.get(key));
  }

  @Override
  public Map<K, Integer> getState() {
    return stateMap;
  }

  public void clearStates() {
    clearDeleted();
    clearDirty();
  }

  public void reuse() {
    delegateMap.clear();
    clearStates();
  }

  @Override
  public Tombstone getTombstone() {
    return Tombstones.getMapTombstone(this);
  }

  @Override
  public boolean isDirty() {
    return dirtyMap.size() > 0;
  }

  @Override
  public void clearDirty() {
    dirtyMap.clear();
  }

  @Override
  public void clearDeleted() {
    deletedMap.clear();
  }

}
