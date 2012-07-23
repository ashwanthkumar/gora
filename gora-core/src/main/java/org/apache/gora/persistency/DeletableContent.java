package org.apache.gora.persistency;

public interface DeletableContent {
  
  /**
   * Clear any state tracking weather or not the contents of this data structure are deleted or not.
   */
  public void clearDeleted();

}
