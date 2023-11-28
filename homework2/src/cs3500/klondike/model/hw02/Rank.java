package cs3500.klondike.model.hw02;

/**
 * Sets up ranks for a deck of cards.
 */
public enum Rank {

  Ace("A", 1), Two("2", 2), Three("3", 3), Four("4", 4), Five("5", 5), Six("6", 6),
  Seven("7", 7), Eight("8", 8), Nine("9", 9), Ten("10", 10), Jack("J", 11),
  Queen("Q", 12),  King("K", 13);

  private final String rank;
  private int value;

  /**
   * Initializes the ranks and values of cards.
   */
  Rank(String rank, int value) {
    this.rank = rank;
    this.value = value;
  }

  /**
   * Returns the rank of a card.
   */
  public String toString() {
    return rank;
  }

  /**
   * Returns a Rank value.
   * Helper method for toString.
   */
  public static Rank ofString(String string) {
    if (string == null) {
      throw new IllegalArgumentException();
    }
    for (Rank value : Rank.values()) {
      if (value.equals(value.rank)) {
        return value;
      }
    }
    throw new IllegalArgumentException("Invalid Rank");
  }

  /**
   * Returns the value of a card.
   */
  public static Rank valueOfCard(Card card) {
    String value = card.toString();
    return Rank.ofString(value.substring(0, value.length() - 1));
  }

  public int getValue() {
    return value;
  }
}
