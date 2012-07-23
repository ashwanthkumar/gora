package org.apache.gora.persistency.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * Simple wrapper to let you use a collection as a set. The set is backed by the
 * collection. Modifications made to the set are reflected in the collection and
 * vice-versa.
 * 
 * @param <T>
 *          The type of the set.
 */
public class CollectionSet<T> implements Set<T> {

  private Collection<T> delegate;

  public CollectionSet(Collection<T> delegate) {
    this.delegate = delegate;
  }

  public int size() {
    return delegate.size();
  }

  public boolean isEmpty() {
    return delegate.isEmpty();
  }

  public boolean contains(Object o) {
    return delegate.contains(o);
  }

  public Iterator<T> iterator() {
    return delegate.iterator();
  }

  public Object[] toArray() {
    return delegate.toArray();
  }

  public <T> T[] toArray(T[] a) {
    return delegate.toArray(a);
  }

  public boolean add(T e) {
    return delegate.add(e);
  }

  public boolean remove(Object o) {
    return delegate.remove(o);
  }

  public boolean containsAll(Collection<?> c) {
    return delegate.containsAll(c);
  }

  public boolean addAll(Collection<? extends T> c) {
    return delegate.addAll(c);
  }

  public boolean removeAll(Collection<?> c) {
    return delegate.removeAll(c);
  }

  public boolean retainAll(Collection<?> c) {
    return delegate.retainAll(c);
  }

  public void clear() {
    delegate.clear();
  }

  public boolean equals(Object o) {
    return delegate.equals(o);
  }

  public int hashCode() {
    return delegate.hashCode();
  }

}
