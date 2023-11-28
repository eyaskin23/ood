package cs3500.klondike;

import org.junit.Assert;
import org.junit.Test;

import cs3500.klondike.model.hw04.WhiteheadKlondike;

import static org.junit.Assert.assertEquals;

/**
 * Tests whitehead methods.
 */
public class TestWhitehead {
  WhiteheadKlondike model = new WhiteheadKlondike();

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
  public void testVisible() {
    model.startGame(model.getDeck(), false, 8, 1);
    Assert.assertTrue(model.isCardVisible(0, 0));
    Assert.assertTrue(model.isCardVisible(1, 1));
    Assert.assertTrue(model.isCardVisible(5, 4));
  }

  @Test
  public void testMoveDraw() {
    model.startGame(model.getDeck(), false, 7, 4);
    Assert.assertThrows(IllegalStateException.class,
        () -> model.moveDraw(0));
  }

  @Test
  public void testNotStarted() {
    Assert.assertThrows(IllegalStateException.class,
        () -> model.moveToFoundation(0, 0));
  }
}
