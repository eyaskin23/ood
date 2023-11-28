package cs3500.klondike.model.hw02;

import java.util.List;
import java.util.Objects;


/**
 * Sets up a card with a suit
 * and a rank value. It includes
 * color, suit, and rank.
 */
public class CardImpl implements Card {
  private final Suit suit;
  private final Rank rank;
  private boolean rightSideUp = true;
  private List<Card> cascadePiles;


  public CardImpl(Rank rank, Suit suit) {
    this.rank = rank;
    this.suit = suit;
  }


  /**
   * Checks if a list of cards
   * is in a sequential order.
   */
  public boolean isNextRank(Card other) {
    List<Rank> rank = List.of(Rank.Ace, Rank.Two, Rank.Three, Rank.Four, Rank.Five,
            Rank.Six, Rank.Seven, Rank.Eight, Rank.Nine,
            Rank.Ten, Rank.Jack, Rank.Queen, Rank.King);
    int thisIndex = rank.indexOf(this.rank);
    int otherIndex = rank.indexOf(other.getRank());
    return thisIndex + 1 == otherIndex;
  }

  /**
   * Checks if a list of cards
   * are alternating colors in sequential order.
   */
  public boolean isAlternatingColor(Card card) {
    if (cascadePiles.isEmpty()) {
      return true;
    } else {
      Card lastCard = cascadePiles.get(cascadePiles.size() - 1);
      return !lastCard.getColor().equals(card.getColor());
    }
  }

  @Override
  public boolean isFaceUp(int cardNum, int pileNum) {
    return isCardVisible(cardNum, pileNum);
  }


  /**
   * Checks if a specific guitar is
   * faced up so that it's visible.
   */
  public boolean isCardVisible(int cardNum, int pileNum) {
    if (cardNum < 0 || pileNum < 0) {
      throw new IllegalArgumentException("Invalid inputs.");
    }
    return true;
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

  public String toString() {
    return rank.toString() + suit.toString();
  }

  /**
   * Sees if a card is equal to another one.
   */
  public boolean equalsCard(CardImpl other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }
    CardImpl card = other;
    return rank == card.rank && suit == card.suit;
  }

  @Override
  public int hashCode() {
    return Objects.hash(rank, suit);
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
