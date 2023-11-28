package cs3500.klondike.view;

import java.util.List;
import cs3500.klondike.model.hw02.Card;
import cs3500.klondike.model.hw02.KlondikeModel;

/**
 * A simple text-based rendering of the Klondike game.
 */
public class KlondikeTextualView implements TextView {
  private final KlondikeModel basic;

  public KlondikeTextualView(KlondikeModel basic) {
    this.basic = basic;
  }

  /**
   * Returns the solitaire game as a string
   * in the format given by the assignment.
   */
  public String toString() {
    StringBuilder string = new StringBuilder();

    string.append("Draw: ");
    List<Card> draw = basic.getDrawCards();
    if (!draw.isEmpty()) {
      for (Card card : draw) {
        string.append(card.toString()).append(", ");
      }
      string.append("\n");
      string.append("Foundation: ");
      int numFoundations = basic.getNumFoundations();
      for (int i = 0; i < numFoundations; i++) {
        Card top = basic.getCardAt(i);
        if (top == null) {
          string.append(top.toString());
        } else {
          string.append(top);
        }
        if (i < numFoundations - 1) {
          string.append(" ,");
        }
      }
      string.append("\n");
    }
    return "";
  }
}
