package cs3500.klondike.model.hw02;

import java.util.ArrayList;
import java.util.List;

/**
 * Sets up a cascade pile with a list of cards.
 */
public class CascadePile {

  final private List<Card> pile;

  /**
   * Sets up a cascade pile.
   */
  public CascadePile() {
    this.pile = new ArrayList<>();
  }

  public boolean isEmpty() {
    return pile.isEmpty();
  }


  /**
   * Returns the last card in a cascade pile.
   */
  public Card getLastCard() {
    return pile.isEmpty() ? null : pile.get(pile.size() - 1);
  }

  /**
   * Helper method that adds cards to a cascade pile.
   */
  public void addCards(List<Card> newCards) {
    pile.addAll(newCards);
  }

  /**
   * Removes the top card in a cascade pile.
   */
  public Card removeTopCard() {
    if (!pile.isEmpty()) {
      return pile.remove(pile.size() - 1);
    }
    return null;
  }

  /**
   * Returns the number of cards in a pile.
   */
  public int getPileSize() {
    return pile.size();
  }

  /**
   * Returns the values of cards in a pile.
   */
  public List<Card> getCards(int numCards) {
    if (numCards <= 0 || numCards > pile.size()) {
      throw new IllegalArgumentException();
    }
    List<Card> cardsToGet = new ArrayList<>();
    for (int i = 0; i < numCards; i++) {
      cardsToGet.add(pile.remove(pile.size() - 1));
    }
    return cardsToGet;
  }

  /**
   * Checks if a certain card in a cascade pile is right side up.
   */
  public boolean isCardVisible(int index) {
    if (index >= 0 && index < pile.size()) {
      return pile.get(index).isCardVisible(1, 1);
    }
    return false;
  }

  /**
   * Sets up a cascade pile.
   */
  public void addCard(Card currentCard) {
    if (pile.isEmpty()) {
      pile.add(currentCard);
      return;
    }

    Card topCard = pile.get(pile.size() - 1);

    if (numsOfRun(topCard, currentCard)) {
      pile.add(currentCard);
    } else {
      throw new IllegalArgumentException("Card does not form a valid run.");
    }
  }

  public void addCardHelper(Card currentCard) {
    pile.add(currentCard);
  }

  private boolean numsOfRun(Card card1, Card card2) {
    return card1.getRank().ordinal() == card2.getRank().ordinal() - 1 &&
            card1.getSuit() == card2.getSuit();
  }

  /**
   * Returns a specific card based on its index.
   */
  public Card getCard(int index) {
    if (index >= 0 && index < pile.size()) {
      return pile.get(index);
    } else {
      return null;
    }
  }

  /**
   * Returns the top card in a list.
   */
  public Card getTopCard() {
    if (!pile.isEmpty()) {
      return pile.get(pile.size() - 1);
    }
    return null;
  }

  /**
   * Checks whether you can add a card
   * to a cascade pile.
   */
  public boolean canAddCard(Card card) {
    if (pile.isEmpty()) {
      return card.getRank() == Rank.King;
    } else {
      Card topCard = pile.get(pile.size() - 1);
      return card.isNextRank(topCard) && card.isAlternatingColor(topCard);
    }
  }
}
