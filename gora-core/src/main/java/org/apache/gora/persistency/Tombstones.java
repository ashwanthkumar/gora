package org.apache.gora.persistency;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Iterators;

/**
 * Provides utility methods related to Tombstones.
 * 
 */
public final class Tombstones {

  private Tombstones() {

  }

  public static final class MapTombstone<K, V> implements Tombstone, Map<K, V> {

    private MapTombstone(Map<K, V> delegate) {
      this.delegate = delegate;
    }

    private final Map<K, V> delegate;

    @Override
    public int size() {
      return delegate.size();
    }

    @Override
    public boolean isEmpty() {
      return delegate.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
      return delegate.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
      return delegate.containsValue(value);
    }

    @Override
    public V get(Object key) {
      return delegate.get(key);
    }

    @Override
    public V put(Object key, Object value) {
      throw new UnsupportedOperationException(
          "Tombstones do not support put operations.");
    }

    @Override
    public V remove(Object key) {
      throw new UnsupportedOperationException(
          "Tombstones do not support remove operations.");
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
      throw new UnsupportedOperationException(
          "Tombstones do not support put operations.");
    }

    @Override
    public void clear() {
      throw new UnsupportedOperationException(
          "Tombstones do not support clear operations.");
    }

    @Override
    public Set<K> keySet() {
      return Collections.unmodifiableSet(delegate.keySet());
    }

    @Override
    public Collection<V> values() {
      return Collections.unmodifiableCollection(delegate.values());
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
      return Collections.unmodifiableSet(delegate.entrySet());
    }

    @Override
    public boolean equals(Object o) {
      return delegate.equals(o);
    }

    @Override
    public int hashCode() {
      return delegate.hashCode();
    }

  }

  public static final class ListTombstone<T> implements List<T>, Tombstone {
    private final List<T> delegate;

    private ListTombstone(List<T> delegate) {
      this.delegate = delegate;
    }

    @Override
    public int size() {
      return delegate.size();
    }

    @Override
    public boolean isEmpty() {
      return delegate.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
      return delegate.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
      return Iterators.unmodifiableIterator(delegate.iterator());
    }

    @Override
    public Object[] toArray() {
      return delegate.toArray();
    }

    @Override
    public <U> U[] toArray(U[] a) {
      return delegate.toArray(a);
    }

    @Override
    public boolean add(T e) {
      throw new UnsupportedOperationException("Tombstones do not support add.");
    }

    @Override
    public boolean remove(Object o) {
      throw new UnsupportedOperationException(
          "Tombstones do not support remove.");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
      return delegate.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
      throw new UnsupportedOperationException(
          "Tombstones do not support addAll.");
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
      throw new UnsupportedOperationException(
          "Tombstones do not support addAll.");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
      throw new UnsupportedOperationException(
          "Tombstones do not support removeAll.");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
      throw new UnsupportedOperationException(
          "Tombstones do not support retainAll.");
    }

    @Override
    public void clear() {
      delegate.clear();
    }

    @Override
    public boolean equals(Object o) {
      return delegate.equals(o);
    }

    @Override
    public int hashCode() {
      return delegate.hashCode();
    }

    @Override
    public T get(int index) {
      return delegate.get(index);
    }

    @Override
    public T set(int index, Object element) {
      throw new UnsupportedOperationException("Tombstones do not support set.");
    }

    @Override
    public void add(int index, Object element) {
      throw new UnsupportedOperationException("Tombstones do not support add.");
    }

    @Override
    public T remove(int index) {
      throw new UnsupportedOperationException(
          "Tombstones do not support remove.");
    }

    @Override
    public int indexOf(Object o) {
      return delegate.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
      return delegate.lastIndexOf(o);
    }

    @Override
    public ListIterator<T> listIterator() {
      return Collections.unmodifiableList(delegate).listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
      return Collections.unmodifiableList(delegate).listIterator(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
      return Collections.unmodifiableList(delegate).subList(fromIndex, toIndex);
    }

  }

  public static <T> ListTombstone<T> getListTombstone(List<T> listToDelete) {
    return new ListTombstone<T>(listToDelete);
  }

  public static <K, V> MapTombstone<K, V> getMapTombstone(Map<K, V> mapToDelete) {
    return new MapTombstone<K, V>(mapToDelete);
  }

  public static boolean isTombstone(Object o) {
    return (o instanceof Tombstone);
  }

}
