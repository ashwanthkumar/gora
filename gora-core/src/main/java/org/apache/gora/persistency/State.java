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

/**
 * Persistency state of an object or field.
 */
public final class State {
  
  private State() {}

  /** The value of the field has not been changed after loading */
  public static final int CLEAN = 0;

  /** The value of the field has been altered */
  public static final int DIRTY = 1;

  /** The object or field is deleted */
  public static final int DELETED = 1 << 1;

  /**
   * The object or field is overwritten, a combination of {@link #DELETED} and
   * {@link #DIRTY}
   */
  public static final int OVERWRITTEN = DIRTY | DELETED;

  public static boolean isDirty(int stateMask) {
    return (DIRTY & stateMask) != 0;
  }

  public static boolean isDeleted(int stateMask) {
    return (DELETED & stateMask) != 0;
  }
}
