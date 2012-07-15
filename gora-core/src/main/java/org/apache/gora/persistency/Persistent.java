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

import org.apache.avro.specific.SpecificRecord;

/**
 * Objects that are persisted by Gora implements this interface.
 */
public interface Persistent extends SpecificRecord, Cloneable {
  

  public static String DIRTY_BYTES_FIELD_NAME = "__g__dirty";
  
  /**
   * Clears the inner state of the object without any modification
   * to the actual data on the data store. This method should be called 
   * before re-using the object to hold the data for another result.  
   */
  void clear();
  
  /**
   * Returns whether any of the fields of the object has been modified 
   * after construction or loading. 
   * @return whether any of the fields of the object has changed
   */
  boolean isDirty();
  
  /**
   * Returns whether the field has been modified.
   * @param fieldIndex the offset of the field in the object
   * @return whether the field has been modified.
   */
  boolean isDirty(int fieldIndex);

  /**
   * Returns whether the field has been modified.
   * @param field the name of the field
   * @return whether the field has been modified.
   */
  boolean isDirty(String field);
  
  /**
   * Sets all the fields of the object as dirty.
   */
  void setDirty();
  
  /**
   * Sets the field as dirty.
   * @param fieldIndex the offset of the field in the object
   */
  void setDirty(int fieldIndex);
 
  /**
   * Sets the field as dirty.
   * @param field the name of the field
   */
  void setDirty(String field);
  
  /**
   * Clears the field as dirty.
   * @param fieldIndex the offset of the field in the object
   */
  void clearDirty(int fieldIndex);
  
  /**
   * Clears the field as dirty.
   * @param field the name of the field
   */
  void clearDirty(String field);
  
  /**
   * Clears the dirty state.
   */
  void clearDirty();
  
  Persistent clone();
}
