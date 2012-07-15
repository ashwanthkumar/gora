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

import java.nio.ByteBuffer;

import org.apache.avro.Schema.Field;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.gora.persistency.Persistent;

/**
 * Base classs implementing common functionality for Persistent classes.
 */
public abstract class PersistentBase extends SpecificRecordBase implements
    Persistent {

  @Override
  public void clearDirty() {
    ByteBuffer dirtyBytes = getDirtyBytes();
    assert (dirtyBytes.position() == 0);
    for (int i = 0; i < dirtyBytes.limit(); i++) {
      dirtyBytes.put(i, (byte) 0);
    }
  }

  @Override
  public void clearDirty(int fieldIndex) {
    ByteBuffer dirtyBytes = getDirtyBytes();
    assert (dirtyBytes.position() == 0);
    int byteOffset = fieldIndex / 8;
    int bitOffset = fieldIndex % 8;
    byte currentByte = dirtyBytes.get(byteOffset);
    currentByte = (byte) ((~(1 << bitOffset)) & currentByte);
    dirtyBytes.put(byteOffset, currentByte);
  }

  @Override
  public void clearDirty(String field) {
    clearDirty(getSchema().getField(field).pos());
  }

  @Override
  public boolean isDirty() {
    ByteBuffer dirtyBytes = getDirtyBytes();
    assert (dirtyBytes.position() == 0);
    boolean dirty = false;
    for (int i = 0; i < dirtyBytes.limit(); i++) {
      dirty |= dirtyBytes.get(i) != 0;
    }
    return dirty;
  }

  @Override
  public boolean isDirty(int fieldIndex) {
    ByteBuffer dirtyBytes = getDirtyBytes();
    assert (dirtyBytes.position() == 0);
    int byteOffset = fieldIndex / 8;
    int bitOffset = fieldIndex % 8;
    byte currentByte = dirtyBytes.get(byteOffset);
    return 0 != ((1 << bitOffset) & currentByte);
  }

  @Override
  public boolean isDirty(String field) {
    return isDirty(getSchema().getField(field).pos());
  }

  @Override
  public void setDirty() {
    ByteBuffer dirtyBytes = getDirtyBytes();
    assert (dirtyBytes.position() == 0);
    for (int i = 0; i < dirtyBytes.limit(); i++) {
      dirtyBytes.put(i, (byte) -128);
    }
  }

  @Override
  public void setDirty(int fieldIndex) {
    ByteBuffer dirtyBytes = getDirtyBytes();
    assert (dirtyBytes.position() == 0);
    int byteOffset = fieldIndex / 8;
    int bitOffset = fieldIndex % 8;
    byte currentByte = dirtyBytes.get(byteOffset);
    currentByte = (byte) ((1 << bitOffset) & currentByte);
    dirtyBytes.put(byteOffset, currentByte);
  }

  @Override
  public void setDirty(String field) {
    setDirty(getSchema().getField(field).pos());
  }

  private ByteBuffer getDirtyBytes() {
    return (ByteBuffer) get(0);
  }
  
  @Override
  public void clear() {
    
  }

  @Override
  public PersistentBase clone() {
    return null;
  }
}
