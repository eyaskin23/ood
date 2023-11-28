package cs3500.hw01.ebooks;

import java.util.ArrayList;
import java.util.List;

/**
 * A cs3500.hw01.ebooks.cs3500.hw01.ebooks.Hyperlink of an e-book consists of a string destination,
 * followed by a sequence of ebook flows (f.e. texts or paragraphs)
 */
public class Hyperlink implements EBookFlow {
  List<EBookFlow> contents;
  String destination;

  /**
   * Constructs a hyperlink which consists of an ebook flow
   * and a string destination.
   */
  public Hyperlink(EBookFlow content, String destination) {
    this.contents = new ArrayList<>();
    this.destination = destination;
  }

  /**
   * Counts the amount of words in a hyperlink.
   */
  public int countWords() {
    int words = 0;
    for (EBookFlow flow : contents) {
      words += flow.countWords();
    }
    return words;
  }


  /**
   * Checks if an ebookflow contains a specific word in the flow.
   */
  public boolean contains(String word) {
    boolean containsWord = false;
    for (EBookFlow ebkf : contents) {
      containsWord |= ebkf.contains(word);
    }
    return containsWord;
  }

  /**
   * Reformats an ebook flow by adding an extra line
   * and checking the length of the link.
   */
  public String format(int lineWidth) {
    String formattedText = "";

    for (EBookFlow word : this.contents) {
      formattedText = formattedText.concat(word.format(lineWidth) + "\n\n");
    }

    return formattedText.substring(0, formattedText.length() - 2);
  }
}
