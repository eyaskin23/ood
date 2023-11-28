package cs3500.klondike;

import org.junit.Assert;
import org.junit.Test;

import cs3500.klondike.model.hw04.LimitedDrawKlondike;

import static org.junit.Assert.assertEquals;


/**
 * Sets up tests for the limited Draw class.
 */
public class TestLimitedDraw {
  private int numReDrawAllowed;

  LimitedDrawKlondike model = new LimitedDrawKlondike(numReDrawAllowed);

  @Test
  public void testGetDeck() {
    model.startGame(model.getDeck(), false, 8, 1);
    assertEquals("A♠", model.getDeck().get(0).toString());
  }

  @Test
  public void testGetDeck2() {
    model.startGame(model.getDeck(), false, 8, 1);
    assertEquals("A♠", model.getDeck().get(0).toString());
  }

  @Test
  public void testMoveToFoundation() {
    LimitedDrawKlondike model = new LimitedDrawKlondike(numReDrawAllowed);
    model.startGame(model.getDeck(), false, 8, 4);
    Assert.assertThrows(IllegalStateException.class,
        () -> model.moveToFoundation(1, 1));
  }

  @Test
  public void testMoveDraw() {
    LimitedDrawKlondike model = new LimitedDrawKlondike(numReDrawAllowed);
    model.startGame(model.getDeck(), false, 8, 4);
    Assert.assertThrows(IllegalStateException.class,
        () -> model.moveDraw(5));
  }
}
