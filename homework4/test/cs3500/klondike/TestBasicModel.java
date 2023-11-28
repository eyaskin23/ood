package cs3500.klondike;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import cs3500.klondike.controller.KlondikeController;
import cs3500.klondike.controller.KlondikeTextualController;
import cs3500.klondike.model.hw02.BasicKlondike;
import cs3500.klondike.model.hw02.Card;
import cs3500.klondike.model.hw02.CardImpl;
import cs3500.klondike.model.hw02.Rank;
import cs3500.klondike.model.hw02.Suit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests the methods in basicklondike.
 */
public class TestBasicModel {
  List<Card> deck = new ArrayList<>();
  BasicKlondike model = new BasicKlondike();

  @Before
  public void init() {
    this.fill();
    BasicKlondike game = new BasicKlondike();
    this.model = game;
  }

  @Test
  public void testGetDeck() {
    List<Card> deck = this.model.getDeck();
    assertEquals("A♠", deck.get(0).toString());
    assertEquals("2♠", deck.get(1).toString());
    assertEquals("3♠", deck.get(2).toString());
    assertEquals("10♣", deck.get(48).toString());
    assertEquals(deck.size(), 52);
  }

  @Test
  public void testStartGame() {
    List<Card> deck = this.model.getDeck();

    this.model.startGame(deck, false, 8, 1);
    int countCascadePiles = this.model.getNumPiles();

    assertEquals(8, countCascadePiles);
    assertTrue(!this.model.isGameOver());
  }

  //  @Test
  //  public void moveSuccessful() {
  //
  //    moveCardToPlace(Rank.ACE, Suit.CLUBS, 3);
  //    moveCardToPlace(Rank.THREE, Suit.HEARTS, 0);
  //    moveCardToPlace(Rank.TWO, Suit.HEARTS, 1);
  //    moveCardToPlace(Rank.TWO, Suit.CLUBS, 2);
  //    moveCardToPlace(Rank.ACE, Suit.SPADES, 7);
  //
  //    this.model.startGame(this.model.getDeck(), false, 4, 1);
  //
  //  }

  @Test(expected = IllegalArgumentException.class)
  public void failsOnInvalidDeck() {
    this.deck.remove(this.deck.size() - 1);
    this.model.startGame(this.deck, false, 8, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveFailedMoveAceToThree() {
    moveCardToPlace(Rank.ACE, Suit.CLUBS, 3);
    moveCardToPlace(Rank.THREE, Suit.HEARTS, 0);
    moveCardToPlace(Rank.TWO, Suit.HEARTS, 1);
    moveCardToPlace(Rank.TWO, Suit.CLUBS, 2);
    moveCardToPlace(Rank.ACE, Suit.SPADES, 7);

    this.model.startGame(this.deck, false, 4, 1);
    this.model.movePile(0, 12, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveFailedMoveAceToSameColor() {
    List<Card> validDeck = this.model.getDeck();
    moveCardToPlace(Rank.ACE, Suit.CLUBS, 3);
    moveCardToPlace(Rank.THREE, Suit.HEARTS, 0);
    moveCardToPlace(Rank.TWO, Suit.HEARTS, 1);
    moveCardToPlace(Rank.TWO, Suit.CLUBS, 2);
    moveCardToPlace(Rank.ACE, Suit.SPADES, 7);

    this.model.startGame(this.deck, false, 4, 1);
    this.model.movePile(0, 12, 1);
  }

  private void moveCardToPlace(Rank rank, Suit suit, int index) {
    for (int i = 0; i < 52; i++) {
      Card currentCard = this.model.getDeck().get(i);
      if (currentCard.getRank().equals(rank.getRank())
              && currentCard.getSuit() == suit.getSuit()) {

        if (index != i) {
          Card tempCard = this.model.getDeck().get(index);
          this.model.getDeck().set(index, currentCard);
          this.model.getDeck().set(i, tempCard);
        }
      }
    }
  }

  private void fill() {
    int i = 0;
    for (Suit s : Suit.values()) {
      for (Rank r : Rank.values()) {
        this.deck.add(new CardImpl(r, s));
        i++;
      }
    }
  }

  @Test
  public void testMove() {
    BasicKlondike model = new BasicKlondike();
    Readable r = new StringReader("mpp 4 2 5 q");
    Appendable a = new StringBuilder();
    KlondikeController controller = new KlondikeTextualController(r, a);
    controller.playGame(model, model.getDeck(), false, 7, 3);
    Assert.assertTrue(a.toString().contains("Invalid move."));
  }

  @Test
  public void testMovePileAllSame() {
    BasicKlondike basic = new BasicKlondike();
    basic.startGame(basic.getDeck(), false, 4, 5);
    Assert.assertThrows(IllegalArgumentException.class, () -> basic.movePile(2, 2, 2));
  }

  @Test
  public void testMovePileSrcandDestSame() {
    BasicKlondike basic = new BasicKlondike();
    basic.startGame(basic.getDeck(), false, 4, 5);
    Assert.assertThrows(IllegalArgumentException.class, () -> basic.movePile(2, 3, 2));
  }

  @Test
  public void testMovePileDescending() {
    BasicKlondike basic = new BasicKlondike();
    basic.startGame(basic.getDeck(), false, 4, 5);
    Assert.assertThrows(IllegalArgumentException.class, () -> basic.movePile(3, 2, 0));
  }

  @Test
  public void testMovePileLessNumDraw() {
    BasicKlondike basic = new BasicKlondike();
    basic.startGame(basic.getDeck(), false, 3, 2);
    Assert.assertThrows(IllegalArgumentException.class, () -> basic.movePile(2, 1, 0));
  }

  @Test
  public void testDiscardDraw() {
    BasicKlondike basic = new BasicKlondike();
    basic.startGame(basic.getDeck(), false, 7, 24);

    for (int i = 0; i <= basic.getNumDraw(); i++) {
      basic.discardDraw();
    }

    Assert.assertThrows(IllegalStateException.class, () -> basic.moveDraw(0));

  }

  @Test
  public void testMoveDrawToFoundation() {
    BasicKlondike basic = new BasicKlondike();
    basic.startGame(basic.getDeck(), false, 7, 24);

    for (int i = 0; i <= basic.getNumDraw(); i++) {
      basic.discardDraw();
    }
    Assert.assertThrows(IllegalStateException.class, () -> basic.moveDrawToFoundation(0));
  }

  @Test
  public void testMoveAceToFoundation() {

    BasicKlondike basic = new BasicKlondike();
    List<Card> customDeck = new ArrayList<>();

    Card aceOfSpades = basic.getDeck().stream().
            filter(card -> card.toString().equals("A♠")).collect(Collectors.toList()).get(0);
    Card aceOfHearts = basic.getDeck().stream().
            filter(card -> card.toString().equals("A♡")).collect(Collectors.toList()).get(0);

    customDeck.add(aceOfSpades);
    customDeck.add(aceOfHearts);

    basic.startGame(customDeck, false, 1, 1);
    basic.moveToFoundation(0, 0);
    Assert.assertThrows(IllegalStateException.class, () -> basic.moveDraw(0));
  }

  @Test
  public void testWrongSuit() {
    BasicKlondike basic = new BasicKlondike();
    List<Card> customDeck = new ArrayList<>();

    Card aceOfSpades = basic.getDeck().stream().
            filter(card -> card.toString().equals("A♠")).collect(Collectors.toList()).get(0);
    Card aceOfHearts = basic.getDeck().stream().
            filter(card -> card.toString().equals("A♡")).collect(Collectors.toList()).get(0);
    Card twoOfHearts = basic.getDeck().stream().
            filter(card -> card.toString().equals("2♡")).collect(Collectors.toList()).get(0);
    Card twoOfSpades = basic.getDeck().stream().
            filter(card -> card.toString().equals("2♠")).collect(Collectors.toList()).get(0);

    customDeck.add(aceOfSpades);
    customDeck.add(twoOfSpades);
    customDeck.add(twoOfHearts);
    customDeck.add(aceOfHearts);

    basic.startGame(customDeck, false, 1, 1);
    basic.moveToFoundation(0, 0);
    Assert.assertThrows(IllegalStateException.class, () -> basic.moveToFoundation(0, 1));
  }



  @Test
  public void test() {
    BasicKlondike basic = new BasicKlondike();
    List<Card> customDeck = new ArrayList<>();

    Card aceOfHearts = basic.getDeck().stream().
            filter(card -> card.toString().equals("A♠")).collect(Collectors.toList()).get(0);
    Card twoOfHearts = basic.getDeck().stream().
            filter(card -> card.toString().equals("2♠")).collect(Collectors.toList()).get(0);
    Card threeOfHearts = basic.getDeck().stream().
            filter(card -> card.toString().equals("3♠")).collect(Collectors.toList()).get(0);
    Card fourOfHearts = basic.getDeck().stream().
            filter(card -> card.toString().equals("4♠")).collect(Collectors.toList()).get(0);
    Card fiveOfHearts = basic.getDeck().stream().
            filter(card -> card.toString().equals("5♠")).collect(Collectors.toList()).get(0);
    Card sixOfHearts = basic.getDeck().stream().
            filter(card -> card.toString().equals("6♠")).collect(Collectors.toList()).get(0);

    customDeck.add(aceOfHearts);
    customDeck.add(twoOfHearts);
    customDeck.add(threeOfHearts);
    customDeck.add(fourOfHearts);
    customDeck.add(fiveOfHearts);
    customDeck.add(sixOfHearts);

    basic.startGame(customDeck, false, 2, 3);
    Assert.assertThrows(IllegalStateException.class, () -> basic.moveToFoundation(1, 0));
  }
}
