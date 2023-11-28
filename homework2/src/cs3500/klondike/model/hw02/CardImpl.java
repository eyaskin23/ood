package cs3500.klondike.model.hw02;

import java.util.List;


/**
 * Sets up a card with a suit
 * and a rank value. It includes
 * color, suit, and rank.
 */
public class CardImpl implements Card {
  private final Suit suit;
  private final Rank rank;
  private boolean rightSideUp;

  private List<CardImpl> pile;

  public CardImpl(Rank rank, Suit suit) {
    this.rank = rank;
    this.suit = suit;
  }

  /**
   * Prints out a card in a string format.
   */
  public String toString() {
    return rank.toString() + suit.toString();
  }

  /**
   * Overrides the equal method to check a card
   * rank and suit are the same as another.
   */

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }
    CardImpl otherCard = (CardImpl) other;
    return this.rank == (otherCard.rank) && this.suit.equals(otherCard.suit);
  }

  @Override
  public int hashCode() {
    return toString().hashCode();
  }

  /**
   * Checks if a list of cards
   * is in a sequential order.
   */
  public boolean isNextRank(CardImpl other) {
    List<String> rank = List.of("2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A");
    int thisIndex = rank.indexOf(this.rank);
    int otherIndex = rank.indexOf(other.rank);

    return thisIndex + 1 == otherIndex;
  }

  /**
   * Checks if a specific guitar is
   * faced up so that it's visible.
   */
  public boolean isCardVisible() {
    return rightSideUp;
  }


  /**
   * Checks if a list of cards
   * is alternating colors.
   */
  public boolean isAlternatingColor(CardImpl card) {
    if (pile.isEmpty()) {
      return true;
    } else {
      CardImpl lastCard = pile.get(pile.size() - 1);
      return !lastCard.getColor().equals(card.getColor());
    }
  }

  /**
   * Returns the color of a card,
   * so that it can alternate colors in a list.
   */
  public String getColor() {
    if (this.suit == Suit.Hearts || this.suit == Suit.Diamonds) {
      return "red";
    } else {
      return "black";
    }
  }

  /**
   * Returns the value in a card
   * so that it can check if the list is sequential.
   */
  public String getNumber(int index) {
    if (index >= 0 && index < pile.size()) {
      return pile.get(index).getNumber(index);
    }
    return null;
  }

  /**
   * Returns the value of a card.
   */
  public Rank getRank() {
    return rank;
  }

  /**
   * Returns the suit of a card.
   */
  public Suit getSuit() {
    return suit;
  }
}
