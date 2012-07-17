package org.apache.gora.persistency;

/**
 * Interface designed to accomodate structures that can be dirty, such as maps
 * and lists, but whose dirtyness should not be set directly because dirtyness
 * results from structural modification or content change, and which have no
 * notion of ordered fields (such as Persistent).
 */
public interface Dirtyable {

  /**
   * Returns whether any of the fields of the object has been modified after
   * construction or loading.
   * 
   * @return whether any of the fields of the object has changed
   */
  boolean isDirty();

  /**
   * Clears the dirty state.
   */
  void clearDirty();

}
