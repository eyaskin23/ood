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

  public int getValue() {
    return value;
  }

  public String toString() {
    return rank;
  }
}

