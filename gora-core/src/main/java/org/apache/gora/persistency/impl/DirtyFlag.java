package org.apache.gora.persistency.impl;

import org.apache.gora.persistency.Dirtyable;

final class DirtyFlag implements Dirtyable {

  private boolean dirty;

  @Override
  public boolean isDirty() {
    return dirty;
  }

  @Override
  public void clearDirty() {
    this.dirty = false;
  }

  /**
   * Set this DirtyFlag to dirty if the <tt>dirty</tt> operand is true. If not,
   * the state of the flag remains unchanged.
   * 
   * @param dirty
   *          Weather or not to set this flag to dirty. If false, the state is
   *          unchanged.
   */
  public void makeDirty(boolean dirty) {
    this.dirty = this.dirty || dirty;
  }

}
