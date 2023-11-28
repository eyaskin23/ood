package cs3500.klondike.controller;

import cs3500.klondike.model.hw02.BasicKlondike;
import cs3500.klondike.model.hw02.Card;
import cs3500.klondike.model.hw02.KlondikeModel;
import cs3500.klondike.view.KlondikeTextualView;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/** Controls a game of klondike and prints it.
 * Takes inputs from the model and
 * handles them as string builders.
 */
public class KlondikeTextualController implements KlondikeController {
  private Readable input;
  private Appendable output;

  /**
   * Takes in a readable and appendable
   * to control the game.
   */
  public KlondikeTextualController(Readable r, Appendable a) {
    this.input = r;
    this.output = a;
    if (r == null || a == null) {
      throw new IllegalArgumentException("Fields cannot be null.");
    }
  }

  /**
   * Starts a game of solitaire by taking in a model,
   * a deck of cards, whether the deck is being shuffled,
   * the num of cascade piles and the num of draw cards.
   */
  public void playGame(KlondikeModel model, List<Card> deck,
                       boolean shuffle, int numRows, int numDraw) {
    KlondikeTextualView view = new KlondikeTextualView(model);
    try {
      model.startGame(deck, shuffle, numRows, numDraw);
      Scanner scanner = new Scanner(this.input);

      if (deck.isEmpty()) {
        throw new IllegalStateException("Deck cannot be empty.");
      }
      while (!model.isGameOver()) {
        view.render();

        String userInput = scanner.nextLine().toLowerCase();

        if (userInput.equals("mpp")) {
          handleMovePile(userInput);
        } else if (userInput.equals("md")) {
          handleMoveDraw(userInput);
        } else if (userInput.equals("mpf")) {
          handleMoveToFoundation(userInput);
        } else if (userInput.equals("mdf")) {
          handleMoveDrawToFoundation(numDraw);
        } else if (userInput.equals("dd")) {
          handleDiscardDraw();
        } else if (userInput.equals("q")) {
          handleQuit(numRows, numDraw);
          return;
        } else {
          this.output.append("Invalid input.");
        }
      }

      view.render();
      if (model.getScore() == 0) {
        this.output.append("Game quit!\n" +
                "State of game when quit:\n" +
                "Draw: " + model.getDrawCards().toString() +
                "\nFoundation: <none>, <none>, <none>, <none>\n"
                + view.renderCascades(model, numRows - 1, numDraw - 1)
                + "Score: "
                + model.getScore());
      } else {
        output.append("Game over. Score: ").append(Integer.toString(model.getScore())).append("\n");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  /**
   * Handles the move pile call.
   */
  private void handleMovePile(String userInput) {
    //unfinished
  }

  /**
   * Handles the move draw call.
   */
  private void handleMoveDraw(String userInput) {
    //unfinished
  }

  /**
   * Handles the move to foundation call.
   */
  private void handleMoveToFoundation(String userInput) {
    //unfinished
  }

  /**
   * Handles the move draw to Foundation call.
   */
  private void handleMoveDrawToFoundation(int numDraw) {
    BasicKlondike model = new BasicKlondike();
    KlondikeTextualView view = new KlondikeTextualView(model);
    boolean canMove = model.canMoveDrawToFoundation(numDraw);
    try {
      if (canMove) {
        this.output.append("");
      } else {
        this.output.append("Invalid move.");
      }

      view.render();

      if (model.isGameOver()) {
        if (model.getScore() == 0) {
          this.output.append("You win!\n");
        } else {
          this.output.append("Game over. Score: ")
                  .append(Integer.toString(model.getScore())).append("\n");
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Handles the discard draw call.
   */
  private void handleDiscardDraw() {
    // unfinished
  }

  /**
   * Handles the quit call.
   */
  private void handleQuit(int cardNum, int pileNum) {
    BasicKlondike model = new BasicKlondike();
    KlondikeTextualView view = new KlondikeTextualView(model);
    try {
      view.render();
      this.output.append("Game quit!\n");
      this.output.append("State of game when quit:\n");
      this.output.append("Draw: ");
      List<Card> draw = model.getDrawCards();
      for (Card card : draw) {
        this.output.append(card.toString()).append(", ");
      }
      this.output.append("\n");

      this.output.append(view.renderCascades(model, cardNum, pileNum));
      this.output.append("Score: ").append(Integer.toString(model.getScore())).append("\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
