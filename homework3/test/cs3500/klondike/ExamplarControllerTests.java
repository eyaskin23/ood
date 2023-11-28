package cs3500.klondike;

import org.junit.Assert;
import org.junit.Test;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import cs3500.klondike.controller.KlondikeController;
import cs3500.klondike.controller.KlondikeTextualController;
import cs3500.klondike.model.hw02.BasicKlondike;
import cs3500.klondike.model.hw02.Card;
import cs3500.klondike.model.hw02.CardImpl;
import cs3500.klondike.model.hw02.Rank;
import cs3500.klondike.model.hw02.Suit;
import cs3500.klondike.model.hw02.KlondikeModel;
import cs3500.klondike.view.KlondikeTextualView;
import cs3500.klondike.view.TextualView;

/**
 * Tests game inputs and outputs,
 * as well as methods.
 */
public class ExamplarControllerTests {

  //  @Test
  //  public void game() {
  //
  //    KlondikeModel model = new BasicKlondike();
  //    Readable r = new StringReader("q");
  //    Appendable a = new StringBuilder();
  //    KlondikeController controller = new KlondikeTextualController(r, a);
  //    controller.playGame(model, model.getDeck(), true, 7, 3);
  //    String expectedOutput = "Game quit!\n" +
  //            "State of game when quit:\n" +
  //            "Draw: A♠, 8♣, K♣\n" +
  //            "Foundation: <none>, <none>, <none>, <none>\n" +
  //            " 2♠  ?  ?  ?  ?  ?  ?\n" +
  //            "    6♣  ?  ?  ?  ?  ?\n" +
  //            "       A♢  ?  ?  ?  ?\n" +
  //            "          K♣  ?  ?  ?\n" +
  //            "             7♠  ?  ?\n" +
  //            "                7♣  ?\n" +
  //            "                   5♡\n" +
  //            "Score: 0";
  //    String actualOutput = a.toString().trim();
  //    Assert.assertEquals(expectedOutput, actualOutput);
  //  }
  //
  //


  @Test
  public void testRenderInitialGameState() {
    BasicKlondike model = new BasicKlondike();
    TextualView view = new KlondikeTextualView(model);
    StringWriter output = new StringWriter();
    model.startGame(model.getDeck(), true, 7, 4);
    view.render();

    String expectedOutput = "Current game state:\n" +
            "Draw: <empty>\n" +
            "Foundation: <none>, <none>, <none>, <none>\n" +
            " 2♠  ?  ?  ?  ?  ?  ?\n" +
            "    6♣  ?  ?  ?  ?  ?\n" +
            "       A♢  ?  ?  ?  ?\n" +
            "          K♣  ?  ?  ?\n" +
            "             7♠  ?  ?\n" +
            "                7♣  ?\n" +
            "                   5♡\n" +
            "Score: 0\n";
    Assert.assertEquals(expectedOutput, output.toString());
  }

  @Test
  public void testMovesAndQuit() {
    KlondikeModel model = new BasicKlondike();
    Readable r = new StringReader("md 4 mpf 6 3 mdf 2 dd 1 q");
    Appendable a = new StringBuilder();
    KlondikeController controller = new KlondikeTextualController(r, a);
    controller.playGame(model, model.getDeck(), false, 7, 3);
    Assert.assertTrue(a.toString().contains("Invalid move."));
  }

  @Test
  public void testDeck() {
    BasicKlondike model = new BasicKlondike();
    List<Card> custom = model.getDeck();
    String expectedCard = "A♡";
    String actualCard = custom.get(0).toString();
    Assert.assertEquals(expectedCard, actualCard);
  }

  @Test
  public void testStartGame() {
    KlondikeModel basic = new BasicKlondike();
    List<Card> deck = basic.getDeck();
    basic.startGame(deck, false, 8, 4);
    int countCascades = basic.getNumPiles();
    Assert.assertEquals(8, countCascades);
    Assert.assertFalse(!basic.isGameOver());
  }

  @Test
  public void testMove() {
    KlondikeModel model = new BasicKlondike();
    Readable r = new StringReader("mpp 4 2 5 q");
    Appendable a = new StringBuilder();
    KlondikeController controller = new KlondikeTextualController(r, a);
    controller.playGame(model, model.getDeck(), false, 7, 3);
    Assert.assertFalse(a.toString().contains("Invalid move."));
  }

  @Test
  public void testValidMoveMPP() {
    KlondikeModel model = new BasicKlondike();
    Readable r = new StringReader("mpp 1 1 2 q");
    Appendable a = new StringBuilder();
    KlondikeController controller = new KlondikeTextualController(r, a);
    controller.playGame(model, model.getDeck(), false, 5, 12);
    Assert.assertFalse(a.toString().contains("Invalid move."));
  }

  @Test
  public void testValidMoveDraw() {
    KlondikeModel model = new BasicKlondike();
    Readable r = new StringReader("md 1 q");
    Appendable a = new StringBuilder();
    KlondikeController controller = new KlondikeTextualController(r, a);
    controller.playGame(model, model.getDeck(), false, 7, 3);
    Assert.assertFalse(a.toString().contains("Invalid move."));
  }

  @Test
  public void testInvalidInputs() {
    KlondikeModel model = new BasicKlondike();
    Readable r = new StringReader("mpp A 1 1 q");
    Appendable a = new StringBuilder();
    KlondikeController controller = new KlondikeTextualController(r, a);
    controller.playGame(model, model.getDeck(), false, 5, 12);
    Assert.assertFalse(a.toString().contains("Invalid move."));
  }

  @Test
  public void testQuitText() {
    KlondikeModel model = new BasicKlondike();
    Readable r = new StringReader("mpp 1 1 2 q");
    Appendable a = new StringBuilder();
    KlondikeController controller = new KlondikeTextualController(r, a);
    List<Card> deck = model.getDeck();
    controller.playGame(model, deck, false, 7, 4);
    Assert.assertTrue(a.toString().contains(
            "Game quit!\n" +
                    "State of game when quit:\n"));
  }

  @Test
  public void testQuitScore() {
    KlondikeModel model = new BasicKlondike();
    Readable r = new StringReader("q");
    Appendable a = new StringBuilder();
    KlondikeController controller = new KlondikeTextualController(r, a);
    List<Card> deck = model.getDeck();
    controller.playGame(model, deck, false, 7, 4);
    Assert.assertFalse(a.toString().contains("\nScore: 0"));
  }

  @Test
  public void moveDraw() {
    KlondikeModel model = new BasicKlondike();
    Readable in = new StringReader("dd dd md 2 q");
    Appendable out = new StringBuilder();
    KlondikeController controller = new KlondikeTextualController(in, out);
    controller.playGame(model, model.getDeck(), false, 3, 4);
    Assert.assertEquals("8♡", model.getCardAt(1, 2).toString());
  }

  @Test
  public void testDraw() {
    KlondikeModel model = new BasicKlondike();
    model.getNumDraw();
    Assert.assertEquals(3, model.getNumDraw());
  }

  @Test
  public void testValidMovePile() {
    BasicKlondike model = new BasicKlondike();
    List<Card> deck = model.getDeck();
    model.startGame(deck, false, 7, 1);
    try {
      model.movePile(0, 1, 1);
    } catch (IllegalArgumentException | IllegalStateException e) {
      Assert.fail("Expected a valid move but got an exception: " + e.getMessage());
    }
  }

  @Test
  public void testCardAt() {
    KlondikeModel model = new BasicKlondike();
    model.getNumDraw();
    CardImpl fiveOfHearts = new CardImpl(Rank.Five, Suit.Hearts);
    Readable in = new StringReader("dd dd md 2 q");
    Appendable out = new StringBuilder();
    KlondikeController controller = new KlondikeTextualController(in, out);
    controller.playGame(model, model.getDeck(), false, 3, 4);
    Assert.assertEquals(fiveOfHearts, model.getCardAt(1, 1));
  }

  @Test
  public void testMovePileSrcandDestSame() {
    BasicKlondike basic = new BasicKlondike();
    basic.startGame(basic.getDeck(), false, 4, 5);
    Assert.assertThrows(IllegalStateException.class, () -> basic.movePile(2, 3, 2));
  }

  @Test
  public void testMovePileDescending() {
    BasicKlondike basic = new BasicKlondike();
    basic.startGame(basic.getDeck(), false, 4, 5);
    Assert.assertThrows(IllegalStateException.class, () -> basic.movePile(3, 2, 0));
  }

  @Test
  public void testMovePileLessNumDraw() {
    BasicKlondike basic = new BasicKlondike();
    basic.startGame(basic.getDeck(), false, 3, 2);
    Assert.assertThrows(IllegalStateException.class, () -> basic.movePile(2, 1, 0));
  }

  @Test
  public void testMoveDrawToFoundation() {
    BasicKlondike basic = new BasicKlondike();
    basic.startGame(basic.getDeck(), false, 7, 24);

    for (int i = 0; i <= basic.getNumDraw(); i++) {
      basic.discardDraw();
    }
    Assert.assertThrows(IllegalArgumentException.class, () -> basic.moveDrawToFoundation(0));
  }

  @Test
  public void testMoveFromDrawToCascade() {
    BasicKlondike basic = new BasicKlondike();
    basic.startGame(basic.getDeck(), false, 7, 4);
    Card cardToMove = new CardImpl(Rank.Nine, Suit.Hearts);
    Card cascadeTopCard = basic.getCardAt(1, 1);
    Assert.assertEquals(cardToMove, cascadeTopCard);
  }

  @Test
  public void testMovePileWrongSuits() {
    BasicKlondike basic = new BasicKlondike();
    List<Card> deck = basic.getDeck();
    basic.startGame(deck, false, 4, 4);

    int srcCascadeIndex = 0;
    int destCascadeIndex = 1;

    Card srcCard = basic.getCardAt(srcCascadeIndex, basic.getNumRows() - 1);
    Card destCard = basic.getCardAt(destCascadeIndex, basic.getNumRows() - 1);
    Assert.assertFalse(srcCard.isAlternatingColor(destCard));

    Card srcAfter = basic.getCardAt(srcCascadeIndex, basic.getNumRows() - 1);
    Card destAfter = basic.getCardAt(destCascadeIndex, basic.getNumRows() - 1);
    Assert.assertEquals(srcCard, srcAfter);
    Assert.assertEquals(destCard, destAfter);
  }

  @Test
  public void testBehaviorsWhenGameNotStarted() {
    KlondikeModel model = new BasicKlondike();
    Assert.assertThrows(IllegalStateException.class, () -> model.getNumDraw());
  }

  //  @Test
  //  public void examplarTestMovePileDestIndex() {
  //    BasicKlondike basic = new BasicKlondike();
  //    List<Card> deck = basic.getDeck();
  //    basic.startGame(deck, false, 7, 4);
  //
  //    int srcCascadeIndex = 1;
  //    int destCascadeIndex = 2;
  //
  //    Card cardToMove = basic.getCardAt(srcCascadeIndex, 0);
  //    basic.movePile(srcCascadeIndex, destCascadeIndex, 0);
  //    Assert.assertNull(basic.getCardAt(srcCascadeIndex, 0));
  //    Assert.assertEquals(cardToMove, basic.getCardAt(destCascadeIndex, 0));
  //  }


  @Test
  public void testMoveFromCascadeToFoundation() {
    BasicKlondike basic = new BasicKlondike();
    basic.startGame(basic.getDeck(), false, 7, 4);
    Assert.assertThrows(IllegalStateException.class, () -> basic.moveToFoundation(0, 1));
  }
}