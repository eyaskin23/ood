package cs3500.hw01.ebooks;

import java.util.List;
import java.util.ArrayList;

/**
 * A simple representation of an e-book, that can contain an arbitrary
 * vertical collection of horizontally-wrapped text content.
 *
 * @implNote: The starter code given to you uses loops and stream operations
 *            interchangeably (for instance, {@link EBook#countWords()} and
 *            {@link Paragraph#countWords()} compute very similar things) ---
 *            this is to give examples of how to use streams fluently.
 */
public final class EBook implements EBookFlow {
  private final List<EBookChunk> chunks;

  /**
   * Constructs an Ebook that takes in a list of EBookChunks.
   * Sets up an Ebook that is used by paragraph, section,
   * and textflow classes.
   */
  public EBook(List<EBookChunk> chunks) {
    validateChunks(chunks);
    this.chunks = new ArrayList<>(chunks);
  }

  // validates a list of chunks to make sure it's not null
  private static void validateChunks(List<EBookChunk> chunks) {
    if (chunks == null) {
      throw new IllegalArgumentException("Chunk list cannot be null");
    }
    if (chunks.stream().anyMatch(c -> c == null)) {
      throw new IllegalArgumentException("Chunk list cannot contain null chunk");
    }
  }

  /** Counts the amount of words in an EBook.
   * Takes in a list of chunks and counts the numbers of words
   */
  public int countWords() {
    int ans = 0;
    for (EBookChunk chunk : this.chunks) {
      ans += chunk.countWords();
    }
    return ans;
  }

  // checks if a list of chunks contains a certain word
  public boolean contains(String word) {
    String trimWord = word.trim();
    return this.chunks.stream().anyMatch(c -> c.contains(trimWord));
  }

  /** Reformats an ebook with a space between words
   * and an extra line at the end of sentences.
   */
  public String format(int lineWidth) {
    String formedText = " ";

    for (EBookChunk chunk : this.chunks) {
      formedText = formedText.concat(chunk.format(lineWidth) + "\n\n");
    }

    return formedText.substring(0, formedText.length() - 2);
  }
}
