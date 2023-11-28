import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import cs3500.hw01.ebooks.Paragraph;
import cs3500.hw01.ebooks.EBook;
import cs3500.hw01.ebooks.Section;
import cs3500.hw01.ebooks.TextFlow;

/**
 * Sets up tests with format to tests
 * if ebooks are properly formatted.
 */
public class FormatTests {

  @Test
  public void testFormatParagraphs() {
    EBook ebk = new EBook(
            List.of(new Paragraph(List.of(new TextFlow("This is a sentence")))));
    String formattedContent = ebk.format(11);
    Assert.assertEquals(" This is a\nsentence", formattedContent);
  }

  @Test
  public void testFormat2() {
    EBook ebk = new EBook(
            List.of(new Section("hello world", List.of(
                    new Paragraph(List.of(new TextFlow("hello world"),
                            new TextFlow("hello this is a sentence"))),
                    new Paragraph(List.of(new TextFlow("evelyn yaskin")))))));
    String formattedContent = ebk.format(29);
    Assert.assertEquals(" hello world\nhello this is a sentence\nevelyn yaskin", formattedContent);
  }

  @Test
  public void testFormat3() {
    EBook ebk = new EBook(List.of(
            new Section("evelyn yaskin",
                    List.of())));
    String formattedContent = ebk.format(10);
    Assert.assertEquals("evelyn yaskin", formattedContent);
  }
}
