package cs3500.hw01.ebooks;

/**
 * An cs3500.hw01.ebooks.cs3500.hw01.ebooks.EBookFlow is a contiguous chunk of text,
 * like a paragraph, that can line-wrap and flow to resize to fit the e-book reader.
 */
public interface EBookFlow {
  /**
   * How many words are in this flow?  Only complete, non-empty words count.
   *
   * @return the relevant count
   */
  int countWords();

  /**
   * Does this flow contain the given word?  Only complete words count:
   * for instance, the document "hello" does not contain the word "ell".
   *
   * @return true iff the given word appears in this e-book
   * @throws IllegalArgumentException if the given word is null,
   *         or contains spaces.
   */
  boolean contains(String word);

  public String format(int lineWidth);
}
