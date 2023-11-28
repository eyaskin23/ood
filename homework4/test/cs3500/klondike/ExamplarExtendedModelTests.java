package cs3500.klondike;

import cs3500.klondike.model.hw02.Card;
import cs3500.klondike.model.hw04.LimitedDrawKlondike;
import cs3500.klondike.model.hw04.WhiteheadKlondike;

import org.junit.Assert;
import org.junit.Test;

import java.util.stream.Collectors;


/**
 * Tests the full draw pile.
 */
public class ExamplarExtendedModelTests {

  WhiteheadKlondike model = new WhiteheadKlondike();
  Card aceOfHearts = model.getDeck().stream().
          filter(card -> card.toString().equals("A♡")).collect(Collectors.toList()).get(0);

  Card fourOfHearts = model.getDeck().stream().
          filter(card -> card.toString().equals("4♡")).collect(Collectors.toList()).get(0);

  Card fourOfClubs = model.getDeck().stream().
          filter(card -> card.toString().equals("4♣")).collect(Collectors.toList()).get(0);
  Card twoOfHearts = model.getDeck().stream().
          filter(card -> card.toString().equals("2♡")).collect(Collectors.toList()).get(0);
  Card threeOfHearts = model.getDeck().stream().
          filter(card -> card.toString().equals("3♡")).collect(Collectors.toList()).get(0);

  Card threeOfDiamonds = model.getDeck().stream().
          filter(card -> card.toString().equals("3♢")).collect(Collectors.toList()).get(0);
  Card aceOfSpades = model.getDeck().stream().
          filter(card -> card.toString().equals("A♠")).collect(Collectors.toList()).get(0);

  Card fourOfSpades = model.getDeck().stream().
          filter(card -> card.toString().equals("4♠")).collect(Collectors.toList()).get(0);

  Card fiveOfSpades = model.getDeck().stream().
          filter(card -> card.toString().equals("5♠")).collect(Collectors.toList()).get(0);
  Card twoOfSpades = model.getDeck().stream().
          filter(card -> card.toString().equals("2♠")).collect(Collectors.toList()).get(0);
  Card threeOfSpades = model.getDeck().stream().
          filter(card -> card.toString().equals("3♠")).collect(Collectors.toList()).get(0);

  @Test
  public void testFullDrawPile() {
    LimitedDrawKlondike model = new LimitedDrawKlondike(2);
    model.startGame(model.getDeck(), false, 9, 7);

    int total = model.getNumDraw() * (2 + 1);

    for (int i = 0; i < total; i++) {
      model.discardDraw();
    }

    Assert.assertThrows(IllegalStateException.class,
        () -> model.discardDraw());
  }
}