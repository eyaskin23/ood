package cs3500.klondike.model.hw02;

import java.awt.Color;

/**
 * Sets up suits for a deck of cards.
 */
public enum Suit {

  Hearts("♡"), Diamonds("♢"), Clubs("♣"), Spades("♠");

  private final String suitSymbol;

  Suit(String suitSymbol) {
    this.suitSymbol = suitSymbol;
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
  public Color getColor() {
    if (suitSymbol.equals(Suit.Hearts) || suitSymbol.equals(Suit.Diamonds)) {
      return Color.RED;
    } else {
      return Color.BLACK;
    }
  }


  /**
   * Returns the suit of a card.
   */
  public String getSuit() {
    return suitSymbol;
  }
}
