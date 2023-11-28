package cs3500.hw01.ebooks;

import java.util.List;
import java.util.ArrayList;

/**
 * A cs3500.hw01.ebooks.cs3500.hw01.ebooks.Paragraph of an e-book consists of a chunk of plain text.
 */
public class Paragraph implements EBookChunk {
  private final List<EBookFlow> contents;

  public Paragraph(List<EBookFlow> contents) {
    validateContents(contents);
    this.contents = new ArrayList<>(contents);
  }

  private static void validateContents(List<EBookFlow> content) {
    if (content == null) {
      throw new IllegalArgumentException("Content list cannot be null");
    }
    if (content.stream().anyMatch(c -> c == null)) {
      throw new IllegalArgumentException("Content list cannot contain null content");
    }
  }

  public int countWords() {
    return this.contents.stream()
            .mapToInt(c -> c.countWords()).sum();
  }

  public boolean contains(String word) {
    return this.contents.stream().anyMatch(c -> c.contains(word));
  }

  /**
   * Reformats an EbookFlow by adding an extra line
   * and checking the length of the flow.
   */
  public String format(int lineWidth) {
    String formattedText = "";

    for (EBookFlow chunk : this.contents) {
      formattedText = formattedText.concat(chunk.format(lineWidth) + "\n");
    }
    return formattedText.substring(0, formattedText.length() - 1);
  }
}

