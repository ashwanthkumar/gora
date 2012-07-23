package org.apache.gora.persistency;

public interface Deletable {

  /**
   * Get the Tombstone that represents the deletion of this object.
   * 
   * @return The tombstone.
   */
  public Tombstone getTombstone();
  
}
