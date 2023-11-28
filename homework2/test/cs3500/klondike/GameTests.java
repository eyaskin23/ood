package cs3500.klondike;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cs3500.klondike.model.hw02.BasicKlondike;
import cs3500.klondike.model.hw02.Card;
import cs3500.klondike.model.hw02.CardImpl;
import cs3500.klondike.model.hw02.Rank;
import cs3500.klondike.model.hw02.Suit;


/**
 * Additional tests for a game of Solitaire by testing
 * each individual method.
 */
public class GameTests {
  ArrayList<Card> deck = new ArrayList<>();

  //
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

  @Test
  public void testInValidDeck() {
    BasicKlondike basic = new BasicKlondike();

    java.util.List<Card> invalidDeck = new java.util.ArrayList<>();
    invalidDeck.add(new CardImpl(Rank.Two, Suit.Spades));

    Assert.assertThrows(IllegalArgumentException.class,
        () ->  basic.startGame(invalidDeck, false, 4, 1));
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
    Assert.assertThrows(IllegalStateException.class,
        () ->  basic.startGame(basic.getDeck(), false, 4, 0));
  }

  @Test
  public void testInvalidMovePileInvalidMove() {
    BasicKlondike basic = new BasicKlondike();
    basic.startGame(basic.getDeck(), false, 4, 3);
    Assert.assertThrows(IllegalArgumentException.class,
        () -> basic.movePile(0, 1, 2));
  }

  @Test
  public void testInvalidMovePileInvalidNumCards() {
    BasicKlondike basic = new BasicKlondike();
    basic.startGame(basic.getDeck(), false, 4, 3);
    Assert.assertThrows(IllegalArgumentException.class,
        () -> basic.movePile(0, 5, 2));
  }
}
