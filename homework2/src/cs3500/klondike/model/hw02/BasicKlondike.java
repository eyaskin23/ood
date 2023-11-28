package cs3500.klondike.model.hw02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This is a stub implementation of the {@link cs3500.klondike.model.hw02.KlondikeModel}
 * interface. You may assume that the actual implementation of BasicKlondike will have a
 * zero-argument (i.e. default) constructor, and that all the methods below will be
 * implemented.  You may not make any other assumptions about the implementation of this
 * class (e.g. what fields it might have, or helper methods, etc.).
 *
 * <p>Once you've implemented all the constructors and methods on your own, you can
 * delete the placeholderWarning() method.
 */

public class BasicKlondike implements cs3500.klondike.model.hw02.KlondikeModel {
  List<FoundationPile> lof = new ArrayList<>();
  private DrawPile drawPile;
  private int numDraw;
  private CardImpl topCard;
  public Suit suit;
  private boolean started;
  private List<CascadePile> cascadePiles;

  /**
   * Sets up a BasicKlondike game using :
   * a list of foundation piles, a draw pile,
   * cards made up of suit and ranks, and a list
   * of cascade piles.
   */
  public BasicKlondike() {
    cascadePiles = new ArrayList<>();
    started = false;
    drawPile = new DrawPile();
  }

  /**
   * Sets up a deck of cards, based on
   * each set of suits having exactly 14 cards.
   */
  public List<Card> getDeck() {
    List<Card> custom = new ArrayList<>();
    for (Suit suit : Suit.values()) {
      //custom.add(new CardImpl(Rank.Ace, suit));
      for (int i = 1; i <= 13; i++) {
        custom.add(new CardImpl(Rank.values()[i - 1], suit));
      }
    }
    return custom;
  }


  /**
   * Starts a game of solitaire by taking in a
   * deck of cards, whether the deck is being shuffled,
   * the num of cascadepiles and the num of drawcards.
   */
  public void startGame(List<Card> deck, boolean shuffle, int numPiles, int numDraw)
          throws IllegalArgumentException, IllegalStateException {
    if (started) {
      throw new IllegalStateException("Game has already started.");
    }
    if (!isValidDeck(deck)) {
      throw new IllegalArgumentException("Invalid deck.");
    }

    cascadePiles.clear();
    for (int i = 0; i < numPiles; i++) {
      cascadePiles.add(new CascadePile());
    }

    lof.clear();
    for (Card card : deck) {
      if (card.getRank() == Rank.Ace) {
        lof.add(new FoundationPile(card.getSuit()));
      }
    }

    ArrayList<Card> loc = new ArrayList<>();
    loc.addAll(deck);

    if (shuffle) {
      Collections.shuffle(loc);
    }

    for (int i = 0; i < numPiles; i++) {
      for (int j = i; j < numPiles; j++) {
        cascadePiles.get(j).addCardSetup((CardImpl) loc.remove(0));
      }
    }

    drawPile.addCards(loc);
    started = true;
  }

  private boolean isValidRun(Card one, Card two) {
    return one.getRank().ordinal() == two.getRank().ordinal() - 1 && one.getSuit() == two.getSuit();
  }

  private boolean isValidDeck(List<Card> deck) {

    if (deck == null) {
      throw new IllegalArgumentException("Deck cannot be null");
    }

    if (!(deck.size() % countAces(deck) == 0)) {
      throw new IllegalArgumentException();
    }

    ArrayList<String> los = new ArrayList<>();

    for (Card card : deck) {
      if (card.getRank() == Rank.Ace) {
        los.add(card.getSuit().toString());
      }
    }

    los.sort(Comparator.naturalOrder());

    ArrayList<String> loc = new ArrayList<>();

    for (int i = 1; i < deck.size() / countAces(deck); i++) {
      loc.clear();
      for (Card card : deck) {
        if (card.getRank().getValue() == i) {
          loc.add(card.getSuit().toString());
        }
      }
      loc.sort(Comparator.naturalOrder());

      if (!los.equals(loc)) {
        throw new IllegalArgumentException("Deck isn't valid");
      }
    }
    return true;
  }


  private int countAces(List<Card> deck) {
    int count = 0;
    for (Card card : deck) {
      if (card.getRank() == Rank.Ace) {
        count++;
      }
    }
    return count;
  }

  private boolean onlyUniqueCards(List<Card> deck) {
    Set<Card> unique = new HashSet<>();
    for (Card card : deck) {
      if (!unique.add(card)) {
        return false;
      }
    }
    return true;
  }


  /**
   * Moves a pile based on which card is being moved,
   * the amount of cards being moved, and the destination pile.
   */
  public void movePile(int srcPile, int numCards, int destPile) {
    if (!started) {
      throw new IllegalStateException("Game has not started.");
    }
    if (srcPile < 0 || srcPile >= cascadePiles.size()
            || destPile < 0 || destPile >= cascadePiles.size()) {
      throw new IllegalArgumentException("Invalid pile.");
    }

    CascadePile src = cascadePiles.get(srcPile);
    CascadePile dest = cascadePiles.get(destPile);

    if (numCards <= 0 || numCards > src.getPileSize()) {
      throw new IllegalArgumentException("Invalid number of cards to move.");
    }

    List<CardImpl> cardsToMove = src.getCards(numCards);

    if (dest.canAddCards(cardsToMove)) {
      dest.addCards(cardsToMove);
    } else {
      src.addCards(cardsToMove);
      throw new IllegalArgumentException("Invalid move.");
    }
  }

  /**
   * Moves a pile from the draw pile to the cascade piles.
   */
  public void moveDraw(int destPile) {
    if (!started) {
      throw new IllegalStateException("Game has not been started.");
    }
    if (destPile < 0 || destPile >= getNumPiles()) {
      throw new IllegalArgumentException("Invalid destination pile number.");
    }

    Card move = drawPile.getTopCard();

    if (move == null) {
      throw new IllegalStateException("The draw pile is empty.");
    }

    boolean canMove = cascadePiles.get(destPile).canAddCards((List<CardImpl>) move);

    if (canMove) {
      drawPile.removeTop();

      topCard = cascadePiles.get(destPile).getLastCard();
    } else {
      throw new IllegalArgumentException("Move is not allowable.");
    }
  }


  /**
   * Moves a pile of cards to a foundation pile.
   */
  public void moveToFoundation(int srcPile, int foundationPile) {
    if (!started) {
      throw new IllegalStateException("Game has not started.");
    }
    if (srcPile < 0 || srcPile >= cascadePiles.size()
            || foundationPile < 0 || foundationPile >= lof.size()) {
      throw new IllegalArgumentException("Invalid pile or foundation.");
    }

    CascadePile src = cascadePiles.get(srcPile);
    FoundationPile foundation = lof.get(foundationPile);

    if (src.getPileSize() == 0) {
      throw new IllegalArgumentException("Source pile is empty.");
    }

    CardImpl cardToMove = src.getLastCard();

    if (foundation.canAddCard(cardToMove)) {
      foundation.addCard(cardToMove);
      src.removeTopCard();
    } else {
      throw new IllegalArgumentException("Invalid move to foundation.");
    }
  }

  /**
   *  Moves a pile from the draw pile to the foundation piles.
   */
  private Card topCardInDrawPile;

  /**
   *  Moves a pile from the draw pile to the foundation pile.
   */
  public void moveDrawToFoundation(int foundationPile) {
    if (!started) {
      throw new IllegalStateException("Game has not started yet.");
    }

    if (foundationPile < 0 || foundationPile >= lof.size()) {
      throw new IllegalArgumentException("Invalid index.");
    }

    if (topCard == null) {
      throw new IllegalArgumentException("Draw pile is empty.");
    }

    lof.get(foundationPile).addCard(topCard);
  }

  /**
   * Discards a card from the draw pile.
   */
  @Override
  public void discardDraw() throws IllegalStateException {
    if (!started) {
      throw new IllegalStateException("Game has not started.");
    }

    if (drawPile.getPileSize() == 0) {
      throw new IllegalStateException("Draw pile is empty.");
    }

    drawPile.removeTop();
  }

  /**
   * Returns the number of rows in a game, based on
   * cascade piles.
   */
  @Override
  public int getNumRows() throws IllegalStateException {
    if (!started) {
      throw new IllegalStateException("Game has not started.");
    }

    int maxCascadeHeight = 0;
    for (CascadePile pile : cascadePiles) {
      int pileHeight = pile.getPileSize();
      if (pileHeight > maxCascadeHeight) {
        maxCascadeHeight = pileHeight;
      }
    }
    return maxCascadeHeight;
  }

  /**
   * Returns the number of piles in a game.
   */
  public int getNumPiles() {
    return this.cascadePiles.size();
  }

  public int getNumDraw() throws IllegalStateException {
    return 3;
  }

  /**
   * Sees if the game is over based on piles.
   */
  @Override
  public boolean isGameOver() {
    if (!started) {
      throw new IllegalStateException("Game has not started yet.");
    }

    if (drawPile.getPileSize() > 0) {
      return false;
    }

    for (FoundationPile foundationPile : lof) {
      List<Card> pileCards = foundationPile.getCards();
      if (pileCards.size() != 13 || pileCards.get(0).getRank() != Rank.Ace) {
        return false;
      }
      for (int i = 1; i < pileCards.size(); i++) {
        if (pileCards.get(i).getRank().ordinal() != pileCards.get(i - 1).getRank().ordinal() + 1) {
          return false;
        }
      }
    }

    return true;
  }


  /**
   * Returns the score of a game.
   */
  @Override
  public int getScore() throws IllegalStateException {
    if (!started) {
      throw new IllegalStateException("Game has not started.");
    }

    int score = 0;

    for (FoundationPile pile : lof) {
      score += pile.getNumCards() * 10;
    }

    for (CascadePile pile : cascadePiles) {
      score -= pile.getPileSize();
    }

    if (score <= 0) {
      throw new IllegalArgumentException("Game score invalid.");
    }

    score -= drawPile.getPileSize();

    return score;
  }

  /**
   * Returns the height of piles in a game
   * based on cascade piles.
   */
  @Override
  public int getPileHeight(int pileNum) {
    if (!started || pileNum == 0 || pileNum >= cascadePiles.size()) {
      throw new IllegalStateException();
    }
    return cascadePiles.get(pileNum).getPileSize();
  }

  /**
   * Returns whether a card in a cascade pile
   * is right side up.
   */
  @Override
  public boolean isCardVisible(int pileNum, int card)
          throws IllegalArgumentException, IllegalStateException {
    if (!started) {
      throw new IllegalStateException("Game has not started.");
    }

    if (pileNum < 0 || pileNum >= cascadePiles.size() || card < 0) {
      throw new IllegalArgumentException("Invalid coordinates.");
    }

    CascadePile pile = cascadePiles.get(pileNum);
    return pile.isCardVisible(card);
  }

  /**
   * Returns a specific card based on pile number.
   */
  public Card getCardAt(int pileNum, int card)
          throws IllegalStateException, IllegalArgumentException {
    if (!started) {
      throw new IllegalStateException("Game has not started yet.");
    }

    if (pileNum < 0 || pileNum >= cascadePiles.size()) {
      throw new IllegalArgumentException("Invalid pile.");
    }

    CascadePile pile = cascadePiles.get(pileNum);

    if (card < 0 || card >= pile.getPileSize()) {
      throw new IllegalArgumentException("Invalid card number.");
    }

    return pile.getCard(card);
  }


  /**
   * Returns a specific card based on foundationpile.
   */
  public Card getCardAt(int foundationPile) throws IllegalArgumentException, IllegalStateException {
    if (!started) {
      throw new IllegalStateException("Game has not started yet.");
    }

    if (foundationPile < 0 || foundationPile >= lof.size()) {
      throw new IllegalArgumentException("Invalid pile number.");
    }

    FoundationPile pile = lof.get(foundationPile);
    return pile.getLastCard();
  }

  /**
   * Returns a pile that exists in the draw pile.
   */
  public List<Card> getDrawCards() throws IllegalStateException {
    if (!started) {
      throw new IllegalStateException("Game has not started.");
    }

    return drawPile.getTopCards(numDraw);

  }

  /**
   * Returns the number of foundation piles.
   */
  public int getNumFoundations() {
    return lof.size();
  }
}
