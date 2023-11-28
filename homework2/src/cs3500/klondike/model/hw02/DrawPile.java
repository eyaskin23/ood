package cs3500.klondike.model.hw02;

import java.util.ArrayList;
import java.util.List;

/**
 * Sets up a draw pile where cards can be drawed from and
 * moved to other piles.
 */
public class DrawPile {
  private List<Card> cards;

  /**
   * Initiates a list of cards for the draw pile.
   */
  public DrawPile() {
    cards = new ArrayList<>();
  }

  /**
   * Adds cascade cards to a draw pile.
   */
  public void addCards(ArrayList<Card> newCards) {
    cards.addAll(newCards);
  }

  /**
   * Removes the top card from the pile
   * when the draw method is called.
   */
  public Card removeTop() {
    if (!cards.isEmpty()) {
      return cards.remove(cards.size() - 1);
    }
    return null;
  }

  /**
   * Returns the size of a draw pile.
   */
  public int getPileSize() {
    return cards.size();
  }

  /**
   * Returns the top card of a draw pile.
   */
  public Card getTopCard() {
    if (cards.isEmpty()) {
      throw new IllegalArgumentException("No more cards in draw.");
    }
    return cards.get(0);
  }

  /**
   * Returns the top couple of cards of a draw pile.
   */
  public List<Card> getTopCards(int draw) {
    ArrayList<Card> loc = new ArrayList<>();
    for (int i = 0; i < Math.min(draw, cards.size()); i++) {
      loc.add(cards.get(i));
    }
    return loc;
  }
}
