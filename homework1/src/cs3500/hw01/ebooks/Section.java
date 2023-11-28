package cs3500.hw01.ebooks;

import java.util.Objects;
import java.util.List;
import java.util.ArrayList;

/**
 * A cs3500.hw01.ebooks.cs3500.hw01.ebooks.Section of an e-book consists of a section title,
 * followed by a sequence of e-book chunks (which could be paragraphs, sub-sections, etc.)
 */
public class Section implements EBookChunk {
  private final TextFlow title;
  private final List<EBookChunk> contents;

  /**
   * Constructs a section based on a title and a list of ebooks chunks.
   */
  public Section(String title, List<EBookChunk> contents) {
    Objects.requireNonNull(title);
    this.title = new TextFlow(title);
    if (title.contains("\n")) {
      throw new IllegalArgumentException("Titles cannot contain line breaks");
    }
    this.contents = new ArrayList<>(Objects.requireNonNull(contents));
  }

  public int countWords() {
    return this.contents.stream().mapToInt(EBookChunk::countWords).sum()
            + this.title.countWords();
  }

  /**
   * Counts the amount of words in a list of ebook chunks.
   */
  public boolean contains(String word) {
    if (word == null) {
      throw new IllegalArgumentException("Word is invalid.");
    }
    if (word.isEmpty()) {
      throw new IllegalArgumentException("Word is empty.");
    }
    return this.title.contains(word)
           || this.contents.stream().anyMatch(c -> c.contains(word));
  }

  /**
   * Reformats an EbookFlow by adding an extra line
   * and checking the length of the flow.
   */
  public String format(int lineWidth) {
    StringBuilder formattedText = new StringBuilder();

    for (EBookChunk chunk : this.contents) {
      String chunkFormatted = chunk.format(lineWidth);
      formattedText.append(chunkFormatted).append("\n");
    }

    if (formattedText.length() > 0) {
      formattedText.deleteCharAt(formattedText.length() - 1);
    }

    return formattedText.substring(0, formattedText.length() - 2);
  }
}

