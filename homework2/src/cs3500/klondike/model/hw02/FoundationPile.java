package cs3500.klondike.model.hw02;

import java.util.ArrayList;
import java.util.List;

/**
 * Sets up a foundation pile based
 * on cards.
 */
public class FoundationPile {
  private final Suit suit;
  private final List<CardImpl> cards;

  /**
   * Sets up a foundation pile based
   * on cards taking in its suit.
   */
  public FoundationPile(Suit suit) {
    this.suit = suit;
    this.cards = new ArrayList<>();
  }

  /**
   * Adds card to a foundation pile.
   */
  public void addCard(CardImpl card) {
    if (!canAddCard(card)) {
      throw new IllegalArgumentException("Invalid card to add to the foundation pile.");
    }
    cards.add(card);
  }

  /**
   * Sees if a foundation pile can accept a card.
   */
  public boolean canAddCard(Card cardToMove) {
    if (cards.isEmpty()) {
      return cardToMove.getRank() == Rank.Ace && cardToMove.getSuit() == suit;
    } else {
      Card top = cards.get(cards.size() - 1);
      return cardToMove.getSuit() == suit
              && cardToMove.getRank().ordinal() == top.getRank().ordinal() + 1;
    }
  }

  /**
   * Returns the number of cards in a foundation pile.
   */
  public int getNumCards() {
    return cards.size();
  }

  /**
   * Returns a list of the cards in a foundation pile.
   */
  public List<Card> getCards() {
    List<Card> allCards = new ArrayList<>(cards);
    return allCards;
  }

  public CardImpl getLastCard() {
    return cards.isEmpty() ? null : cards.get(cards.size() - 1);
  }
}
