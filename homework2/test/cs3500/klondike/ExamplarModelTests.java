package cs3500.klondike;

import cs3500.klondike.model.hw02.BasicKlondike;
import cs3500.klondike.model.hw02.Card;
import cs3500.klondike.model.hw02.CardImpl;
import cs3500.klondike.model.hw02.Rank;
import cs3500.klondike.model.hw02.Suit;
import cs3500.klondike.view.KlondikeTextualView;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import static org.junit.Assert.assertThrows;

/**
 * Tests a game of Solitaire by testing
 * each individual method, and starting a game
 * with "rigged" decks.
 */

public class ExamplarModelTests {

  @Test
  public void testMovePileAllSame() {
    BasicKlondike basic = new BasicKlondike();
    basic.startGame(basic.getDeck(), false, 4, 5);
    assertThrows(IllegalArgumentException.class, () -> basic.movePile(2, 2, 2));
  }

  @Test
  public void testFoundations() {
    BasicKlondike basic = new BasicKlondike();
    basic.getNumFoundations();
    assertThrows(IllegalStateException.class, () -> basic.movePile(2, 2, 2));
  }

  @Test
  public void testMovePileSrcandDestSame() {
    BasicKlondike basic = new BasicKlondike();
    basic.startGame(basic.getDeck(), false, 4, 5);
    assertThrows(IllegalArgumentException.class, () -> basic.movePile(2, 3, 2));
  }

  @Test
  public void testMovePileDescending() {
    BasicKlondike basic = new BasicKlondike();
    basic.startGame(basic.getDeck(), false, 4, 5);
    assertThrows(IllegalArgumentException.class, () -> basic.movePile(3, 2, 0));
  }

  @Test
  public void testMovePileLessNumDraw() {
    BasicKlondike basic = new BasicKlondike();
    basic.startGame(basic.getDeck(), false, 3, 2);
    assertThrows(IllegalArgumentException.class, () -> basic.movePile(2, 1, 0));
  }

  @Test
  public void testDiscardDraw() {
    BasicKlondike basic = new BasicKlondike();
    basic.startGame(basic.getDeck(), false, 7, 24);

    int numDraw = basic.getNumDraw();
    for (int i = 0; i < numDraw; i++) {
      basic.discardDraw();
    }
    assertThrows(IllegalArgumentException.class, () -> basic.moveDraw(0));
  }

  @Test
  public void testMoveDraw() {
    BasicKlondike basic = new BasicKlondike();
    basic.startGame(basic.getDeck(), false, 7, 24);

    basic.moveDraw(0);
    assertThrows(IllegalArgumentException.class, () -> basic.startGame(
            basic.getDeck(), false, 7, 1));
  }


  @Test
  public void testMoveDrawToFoundation() {
    BasicKlondike basic = new BasicKlondike();
    basic.startGame(basic.getDeck(), false, 7, 24);

    Assert.assertFalse(basic.getDrawCards().isEmpty());
    basic.moveDrawToFoundation(0);
  }


  @Test
  public void testMoveAceToFoundation() {
    BasicKlondike basic = new BasicKlondike();
    basic.startGame(basic.getDeck(), false, 4, 3);

    CardImpl aceOfSpades = new CardImpl(Rank.Ace, Suit.Spades);
    Assert.assertNotNull(aceOfSpades);
    Assert.assertEquals(Rank.Ace, aceOfSpades.getRank());

    assertThrows(IllegalArgumentException.class, () -> basic.moveToFoundation(0, 0));
  }

  @Test
  public void testInvalidDeck() {
    BasicKlondike basic = new BasicKlondike();
    List<Card> customDeck = new ArrayList<>();

    CardImpl aceOfSpades = new CardImpl(Rank.Ace, Suit.Spades);
    CardImpl aceOfSpades2 = new CardImpl(Rank.Ace, Suit.Spades);

    customDeck.add(aceOfSpades);
    customDeck.add(aceOfSpades2);

    assertThrows(IllegalArgumentException.class, () -> basic.startGame(
            customDeck, false, 7, 1));
  }

  @Test
  public void testVisibleCardMethod() {

    BasicKlondike basic = new BasicKlondike();
    basic.startGame(basic.getDeck(), false, 4, 5);

    Assert.assertFalse(basic.isCardVisible(0, 1));
  }

  @Test
  public void testGetScore() {
    BasicKlondike basic = new BasicKlondike();
    basic.startGame(basic.getDeck(), false, 4, 5);

    assertThrows(IllegalStateException.class, () ->
            basic.startGame(basic.getDeck(), false, 0, 1));
  }

  @Test
  public void testStartGameFoundationPiles() {
    BasicKlondike basic = new BasicKlondike();

    List<Card> customDeck = new ArrayList<>();
    customDeck.add(new CardImpl(Rank.Ace, Suit.Spades));
    customDeck.add(new CardImpl(Rank.Ace, Suit.Hearts));
    customDeck.add(new CardImpl(Rank.Ace, Suit.Diamonds));
    customDeck.add(new CardImpl(Rank.Ace, Suit.Clubs));

    basic.startGame(customDeck, false, 4, 3);
    int expectedFoundationPiles = 4;
    Assert.assertEquals(expectedFoundationPiles, basic.getNumFoundations());
  }

  @Test
  public void testMaxPiles() {
    BasicKlondike basic = new BasicKlondike();
    basic.startGame(basic.getDeck(), false, 13, 5);
    Assert.assertEquals(13, basic.getNumPiles());
  }

  @Test
  public void testMinDraw() {
    BasicKlondike basic = new BasicKlondike();
    basic.startGame(basic.getDeck(), false, 7, 1);
    Assert.assertEquals(3, basic.getNumDraw());
  }

  @Test
  public void testMaxDraw() {
    BasicKlondike basic = new BasicKlondike();
    basic.startGame(basic.getDeck(), false, 7, 24);
    Assert.assertEquals(3, basic.getNumDraw());
  }

  @Test
  public void testEmptyDrawPile() {
    BasicKlondike basic = new BasicKlondike();
    basic.startGame(basic.getDeck(), false, 7, 0);
    assertThrows(IllegalArgumentException.class, () -> basic.movePile(0, 1, 0));
  }

  @Test
  public void testMoveToCascade() {
    BasicKlondike basic = new BasicKlondike();
    basic.startGame(basic.getDeck(), false, 7, 3);
    assertThrows(IllegalArgumentException.class, () -> basic.movePile(0, 1, 0));
  }

  @Test
  public void testIsGameOver() {
    BasicKlondike basic = new BasicKlondike();
    basic.startGame(basic.getDeck(), false, 7, 3);
    for (int i = 0; i <= 7; i++) {
      basic.moveToFoundation(i, i);
    }
    Assert.assertTrue(basic.isGameOver());
  }

  ArrayList<Card> deck = new ArrayList<>();

  //  @Test
  //  public void isValidDeck() {
  //
  //    BasicKlondike basic = new BasicKlondike();
  //    basic.startGame(basic.getDeck(), true, 4, 3);
  //
  //    HashMap<Card, Integer> frequency = new HashMap<>();
  //    for (Card card : deck) {
  //      if (frequency.containsKey(card)) {
  //        frequency.put(card, frequency.get(card) + 1);
  //      } else {
  //        frequency.put(card, 1);
  //      }
  //    }
  //  }

  //
  //  @Test
  //  public void testMovePileWithValidInputsToEmptyPile() {
  //    BasicKlondike basic = new BasicKlondike();
  //
  //     basic.movePile(0, 1, 1);
  //
  //    Assert.assertTrue(basic.get(0).isEmpty());
  //    Assert.assertEquals(expectedMovedCards, basic.getPile(1).getCards());
  //  }

  @Test
  public void testInValidDeck() {
    BasicKlondike basic = new BasicKlondike();

    java.util.List<Card> invalidDeck = new java.util.ArrayList<>();
    invalidDeck.add(new CardImpl(Rank.Two, Suit.Spades));

    assertThrows(IllegalArgumentException.class,
        () -> basic.startGame(invalidDeck, false, 4, 1));
  }

  @Test
  public void testToString() {

    CardImpl aceOfSpades = new CardImpl(Rank.Ace, Suit.Spades);
    CardImpl queenOfHearts = new CardImpl(Rank.Queen, Suit.Hearts);
    CardImpl tenOfDiamonds = new CardImpl(Rank.Ten, Suit.Diamonds);
    CardImpl twoOfClubs = new CardImpl(Rank.Two, Suit.Clubs);

    Assert.assertEquals("A♠", aceOfSpades.toString());
    Assert.assertEquals("Q♡", queenOfHearts.toString());
    Assert.assertEquals("10♢", tenOfDiamonds.toString());
    Assert.assertEquals("2♣", twoOfClubs.toString());
  }

//  @Test
//  public void testView() {
//
//    BasicKlondike basic = new BasicKlondike();
//    basic.startGame(basic.getDeck(), true, 4, 3);
//
//    basic.toString();
//
//    KlondikeTextualView klondike = new KlondikeTextualView(basic);
//    klondike.toString();
//
//  }

  @Test
  public void test52Cards() {

    BasicKlondike basic = new BasicKlondike();
    basic.startGame(basic.getDeck(), true, 4, 3);
    Assert.assertEquals(52, basic.getDeck().size());
  }

  @Test
  public void testGetDeck() {
    BasicKlondike basic = new BasicKlondike();
    List<Card> deck = basic.getDeck();
    Assert.assertEquals(52, deck.size());
    Set<Card> uniqueCards = new HashSet<>(deck);
    Assert.assertEquals(52, uniqueCards.size());
  }

  @Test
  public void testInvalidNumDraw() {
    BasicKlondike basic = new BasicKlondike();
    basic.startGame(basic.getDeck(), true, 4, 0);
    assertThrows(IllegalStateException.class,
        () -> basic.startGame(basic.getDeck(), false, 4, 0));
  }

  @Test
  public void testGetCardAtInvalidCardNumber() {
    BasicKlondike basic = new BasicKlondike();
    basic.startGame(basic.getDeck(), true, 4, 0);
    basic.getCardAt(0, 10);
    assertThrows(IllegalStateException.class,
        () -> basic.startGame(basic.getDeck(), false, 4, 0));
  }

  @Test
  public void testStartGameWithInvalidDeck() {
    BasicKlondike basic = new BasicKlondike();

    List<Card> invalidDeck = new ArrayList<>();
    invalidDeck.add(new CardImpl(Rank.Ace, Suit.Hearts));

    IllegalArgumentException invalid =
            assertThrows(IllegalArgumentException.class,
        () -> basic.startGame(invalidDeck, false, 7, 1)
    );

    Assert.assertEquals("Invalid deck.", invalid.getMessage());
  }

  @Test
  public void testInvalidMovePileInvalidMove() {
    BasicKlondike basic = new BasicKlondike();
    basic.startGame(basic.getDeck(), false, 4, 3);
    assertThrows(IllegalArgumentException.class,
        () -> basic.movePile(0, 1, 2));
  }

  @Test
  public void testInvalidMovePileInvalidNumCards() {
    BasicKlondike basic = new BasicKlondike();
    basic.startGame(basic.getDeck(), false, 4, 3);
    assertThrows(IllegalArgumentException.class,
        () -> basic.movePile(0, 5, 2));
  }
}