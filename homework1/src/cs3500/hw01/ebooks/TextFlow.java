package cs3500.hw01.ebooks;

import java.util.Arrays;
import java.util.Objects;

/**
 * Sets up a text flow with a string of content.
 * A textflow has the methods countWords, contains, and format.
 */
public class TextFlow implements EBookFlow {
  private final String content;

  /**
   * Sets up a text flow with a string of content.
   */
  public TextFlow(String content) {
    this.content = Objects.requireNonNull(content);
    if (content.contains("\n")) {
      throw new IllegalArgumentException("Text flows cannot contain line breaks");
    }
  }

  /**
   * Counts the amount of words in a Text Flow.
   */
  public int countWords() {
    return Arrays.stream(this.content.split("\\s+")).filter(s -> !s.isEmpty())
            .mapToInt(s -> 1).sum();
  }

  /**
   * Checks if a text flow contains a specific given word.
   */
  public boolean contains(String word) {
    if (word == null || word.contains(" ")) {
      throw new IllegalArgumentException("");
    }
    if (word.isEmpty()) {
      return false;
    }
    return Arrays.asList(this.content.split("\\s+")).contains(word);
  }

  /**
   * Reformats an EbookFlow by adding an extra line
   * and checking the length of the flow.
   */
  public String format(int lineWidth) {
    if (lineWidth <= 0) {
      throw new IllegalArgumentException("Line width cannot be negative.");
    }
    String[] low = this.content.split("\\s+");
    String formedText = "";
    int length = 0;

    for (String title : low) {
      if (title.length() >= lineWidth) {
        throw new IllegalArgumentException("Words cannot be longer than the line width.");
      }
      if (length == 0) {
        formedText = title;
        length = title.length();
      } else if (length + title.length() + 1 <= lineWidth) {
        formedText = formedText.concat(" " + title);
        length += title.length() + 1;
      } else {
        formedText = formedText.concat("\n" + title);
        length = title.length();
      }
    }
    return formedText;
  }
}

