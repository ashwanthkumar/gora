package org.apache.gora.persistency;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

public final class Tombstones {

	private Tombstones() {

	}

	public static final class MapTombstone<K, V> implements Tombstone,
			Map<K, V> {

		private MapTombstone() {
		}

		private static final Map DELEGATE = Collections.EMPTY_MAP;

		public int size() {
			return DELEGATE.size();
		}

		public boolean isEmpty() {
			return DELEGATE.isEmpty();
		}

		public boolean containsKey(Object key) {
			return DELEGATE.containsKey(key);
		}

		public boolean containsValue(Object value) {
			return DELEGATE.containsValue(value);
		}

		public V get(Object key) {
			return (V) DELEGATE.get(key);
		}

		public V put(Object key, Object value) {
			return (V) DELEGATE.put(key, value);
		}

		public V remove(Object key) {
			return (V) DELEGATE.remove(key);
		}

		public void putAll(Map m) {
			DELEGATE.putAll(m);
		}

		public void clear() {
			DELEGATE.clear();
		}

		public Set keySet() {
			return DELEGATE.keySet();
		}

		public Collection values() {
			return DELEGATE.values();
		}

		public Set entrySet() {
			return DELEGATE.entrySet();
		}

		public boolean equals(Object o) {
			return DELEGATE.equals(o);
		}

		public int hashCode() {
			return DELEGATE.hashCode();
		}

	}

	public static final class ListTombstone<T> implements List<T>, Tombstone {
		private static final List DELEGATE = Collections.EMPTY_LIST;

		private ListTombstone() {

		}

		public int size() {
			return DELEGATE.size();
		}

		public boolean isEmpty() {
			return DELEGATE.isEmpty();
		}

		public boolean contains(Object o) {
			return DELEGATE.contains(o);
		}

		public Iterator iterator() {
			return DELEGATE.iterator();
		}

		public Object[] toArray() {
			return DELEGATE.toArray();
		}

		public Object[] toArray(Object[] a) {
			return DELEGATE.toArray(a);
		}

		public boolean add(Object e) {
			return DELEGATE.add(e);
		}

		public boolean remove(Object o) {
			return DELEGATE.remove(o);
		}

		public boolean containsAll(Collection c) {
			return DELEGATE.containsAll(c);
		}

		public boolean addAll(Collection c) {
			return DELEGATE.addAll(c);
		}

		public boolean addAll(int index, Collection c) {
			return DELEGATE.addAll(index, c);
		}

		public boolean removeAll(Collection c) {
			return DELEGATE.removeAll(c);
		}

		public boolean retainAll(Collection c) {
			return DELEGATE.retainAll(c);
		}

		public void clear() {
			DELEGATE.clear();
		}

		public boolean equals(Object o) {
			return DELEGATE.equals(o);
		}

		public int hashCode() {
			return DELEGATE.hashCode();
		}

		public T get(int index) {
			return (T) DELEGATE.get(index);
		}

		public Object set(int index, Object element) {
			return DELEGATE.set(index, element);
		}

		public void add(int index, Object element) {
			DELEGATE.add(index, element);
		}

		public T remove(int index) {
			return (T) DELEGATE.remove(index);
		}

		public int indexOf(Object o) {
			return DELEGATE.indexOf(o);
		}

		public int lastIndexOf(Object o) {
			return DELEGATE.lastIndexOf(o);
		}

		public ListIterator listIterator() {
			return DELEGATE.listIterator();
		}

		public ListIterator listIterator(int index) {
			return DELEGATE.listIterator(index);
		}

		public List subList(int fromIndex, int toIndex) {
			return DELEGATE.subList(fromIndex, toIndex);
		}

	}

	public static final MapTombstone MAP_TOMBSTONE = new MapTombstone();

	public static <K, V> MapTombstone<K, V> getMapTombstone() {
		return MAP_TOMBSTONE;
	}

	public static final ListTombstone LIST_TOMBSTONE = new ListTombstone();

	public static final <T> ListTombstone<T> getListTombstone() {
		return LIST_TOMBSTONE;
	}

	public static boolean isTombstone(Object o) {
		return (o instanceof Tombstone);
	}

}
