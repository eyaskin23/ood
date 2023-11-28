package cs3500.klondike.model.hw02;

import java.util.ArrayList;
import java.util.List;

/**
 * Sets up a cascade pile with a list of cards.
 */
public class CascadePile {

  private List<CardImpl> pile;

  /**
   * Sets up a cascade pile.
   */
  public CascadePile() {
    pile = new ArrayList<>();
  }

  /**
   * Checks if a certain cascade card is visible(faced up).
   */
  public boolean isThisCardVisible(int index) {
    if (index >= 0 && index < pile.size()) {
      return pile.get(index).isCardVisible();
    }
    return false;
  }

  /**
   * Checks that the next card in a cascade pile
   * is in sequential order.
   */
  public boolean isNextNum(CardImpl card) {
    if (pile.isEmpty()) {
      return card.getRank() == Rank.Two;
    } else {
      CardImpl lastCard = pile.get(pile.size() - 1);
      Rank lastRank = lastCard.getRank();
      Rank currentRank = card.getRank();
      return currentRank.ordinal() - lastRank.ordinal() == 1;
    }
  }

  /**
   * Checks that the next card in a cascade pile
   * is a different color.
   */
  public boolean isDifferentColor(Card card) {
    if (pile.isEmpty()) {
      return true;
    } else {
      Card lastCard = pile.get(pile.size() - 1);
      return !lastCard.getColor().equals(card.getColor());
    }
  }

  /**
   * Returns the lastcard in a cascade pile.
   */
  public CardImpl getLastCard() {
    return pile.isEmpty() ? null : pile.get(pile.size() - 1);
  }

  /**
   * Helper method that adds cards to a cascade pile.
   */
  public void addCards(List<CardImpl> newCards) {
    pile.addAll(newCards);
  }

  /**
   * Removes the top card in a cascade pile.
   */
  public CardImpl removeTopCard() {
    if (!pile.isEmpty()) {
      return pile.remove(pile.size() - 1);
    }
    return null;
  }

  /**
   * Checks if a list can accept cascade cards.
   */
  public boolean canAddCards(List<CardImpl> newCard) {
    if (pile.isEmpty()) {
      return newCard.get(0).getRank().equals(2);
    } else {
      CardImpl lastCard = pile.get(pile.size() - 1);
      return lastCard.isNextRank(newCard.get(0)) && lastCard.isAlternatingColor(newCard.get(0));
    }
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
  public List<CardImpl> getCards(int numCards) {
    if (numCards <= 0 || numCards > pile.size()) {
      throw new IllegalArgumentException();
    }
    List<CardImpl> cardsToGet = new ArrayList<>();
    for (int i = 0; i < numCards; i++) {
      cardsToGet.add(pile.remove(pile.size() - 1));
    }
    return cardsToGet;
  }

  /**
   * Returns the card at a certain index
   * of a cascade pile.
   */
  public Card getCardAt(int index) throws IllegalArgumentException {
    if (index < 0 || index >= pile.size()) {
      throw new IllegalArgumentException("Invalid card index.");
    }
    return pile.get(index);
  }

  /**
   * Checks if a certain card in a cascade pile is right side up.
   */
  public boolean isCardVisible(int index) {
    if (index >= 0 && index < pile.size()) {
      return pile.get(index).isCardVisible();
    }
    return false;
  }

  /**
   * Sets up a cascade pile.
   */
  public void addCard(CardImpl currentCard) {
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

  public void addCardSetup(CardImpl currentCard) {
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
      throw new IllegalArgumentException("Invalid card index.");
    }
  }
}
