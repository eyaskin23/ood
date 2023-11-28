package cs3500.klondike.model.hw02;

import java.awt.Color;

/**
 * Sets up suits for a deck of cards.
 */
public enum Suit {

  Hearts("♡", Color.red), Diamonds("♢", Color.red),
  Clubs("♣", Color.black), Spades("♠", Color.black);
  private final String suitSymbol;
  private final Color color;

  Suit(String suitSymbol, Color color) {
    this.suitSymbol = suitSymbol;
    this.color = color;
  }

  /**
   * Returns the suit of a card in string format.
   */
  public String toString() {
    return suitSymbol;
  }

  /**
   * Returns the suit of a card.
   * Helper method for toString.
   */
  public static Suit ofString(String string) {
    if (string == null) {
      throw new IllegalArgumentException();
    }
    for (Suit value : Suit.values()) {
      if (value.equals(value.suitSymbol)) {
        return value;
      }
    }
    throw new IllegalArgumentException("Invalid Suit");
  }

  /**
   * Returns the value of a card.
   */
  public Suit valueOfCard(Card card) {
    String value = card.toString();
    return Suit.ofString(value.substring(value.length() - 1));
  }

  /**
   * Returns the color of a card.
   */
  public boolean hasSameColor(Suit other) {
    return this.color == other.color;
  }

}
