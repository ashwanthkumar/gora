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

package org.apache.gora.persistency;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericArray;
import org.apache.avro.generic.GenericData;

/**
 * An {@link ArrayList} based implementation of Avro {@link GenericArray}.
 */
public class ListGenericArray<T> implements GenericArray<T>
  , Comparable<ListGenericArray<T>> {

  private static final int LIST_DEFAULT_SIZE = 10;
  
  private List<T> list;
  
  @Override
  public int size() {
    return list.size();
  }

  @Override
  public boolean isEmpty() {
    return list.isEmpty();
  }

  @Override
  public boolean contains(Object o) {
    return list.contains(o);
  }

  @Override
  public Object[] toArray() {
    return list.toArray();
  }

  @Override
  public <T> T[] toArray(T[] a) {
    return list.toArray(a);
  }

  @Override
  public boolean add(T e) {
    return list.add(e);
  }

  @Override
  public boolean remove(Object o) {
    return list.remove(o);
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    return list.containsAll(c);
  }

  @Override
  public boolean addAll(Collection<? extends T> c) {
    return list.addAll(c);
  }

  @Override
  public boolean addAll(int index, Collection<? extends T> c) {
    return list.addAll(index, c);
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    return list.removeAll(c);
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    return list.retainAll(c);
  }

  @Override
  public T get(int index) {
    return list.get(index);
  }

  @Override
  public T set(int index, T element) {
    return list.set(index, element);
  }

  @Override
  public void add(int index, T element) {
    list.add(index, element);
  }

  @Override
  public T remove(int index) {
    return list.remove(index);
  }

  @Override
  public int indexOf(Object o) {
    return list.indexOf(o);
  }

  @Override
  public int lastIndexOf(Object o) {
    return list.lastIndexOf(o);
  }

  @Override
  public ListIterator<T> listIterator() {
    return list.listIterator();
  }

  @Override
  public ListIterator<T> listIterator(int index) {
    return list.listIterator(index);
  }

  @Override
  public List<T> subList(int fromIndex, int toIndex) {
    return list.subList(fromIndex, toIndex);
  }

  private Schema schema;

  public ListGenericArray(Schema schema, List<T> list) {
    this.schema = schema;
    this.list = list;
  }

  public ListGenericArray(Schema schema) {
    this(LIST_DEFAULT_SIZE, schema);
  }
  
  public ListGenericArray(int size, Schema schema) {
    this.schema = schema;
    this.list = new ArrayList<T>(size);
  }

  @Override
  public void clear() {
    list.clear();
  }

  @Override
  public T peek() {
    return null;
  }

  @Override
  public Iterator<T> iterator() {
    return list.iterator();
  }

  @Override
  public Schema getSchema() {
    return schema;
  }

  @Override
  public int hashCode() {
    return this.list.hashCode();
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Override
  public boolean equals(Object obj) {
    if (obj == this) return true;
    if (!(obj instanceof ListGenericArray)) return false;
    ListGenericArray that = (ListGenericArray)obj;
    if (!schema.equals(that.schema))
      return false;
    return this.compareTo(that) == 0;
  }

  @Override
  public int compareTo(ListGenericArray<T> o) {
    return GenericData.get().compare(this, o, schema);
  }
  
  @Override
  public String toString() {
    return list.toString();
  }

  @Override
  public void reverse() {
    Collections.reverse(list);
  }

}
