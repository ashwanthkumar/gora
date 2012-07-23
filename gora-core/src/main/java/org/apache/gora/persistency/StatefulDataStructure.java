package org.apache.gora.persistency;

import java.util.Map;

public interface StatefulDataStructure<K> {

  /**
   * Get the state of this map. State is represented using integer codes from
   * {@link State}.
   * 
   * @return The state of each key in this map.
   */
  public Map<K, Integer> getState();

  /**
   * Clears all the state tracking thats been done in this data structure.
   */
  public void clearStates();

}
