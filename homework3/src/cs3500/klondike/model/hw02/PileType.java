package cs3500.klondike.model.hw02;

/**
 * Creates an enum for pile type.
 */
public enum PileType {
  CASCADE,
  FOUNDATION,
  DRAW;

  /**
   * Returns a string for the pile type.
   */
  public static PileType fromString(String str) {
    for (PileType pileType : PileType.values()) {
      if (pileType.name().equalsIgnoreCase(str)) {
        return pileType;
      }
    }
    return null;
  }
}

