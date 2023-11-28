import org.junit.Test;
import org.junit.Assert;

import java.util.List;

import cs3500.hw01.ebooks.EBook;
import cs3500.hw01.ebooks.Paragraph;
import cs3500.hw01.ebooks.Section;
import cs3500.hw01.ebooks.TextFlow;

/**
 * Sets up a class of examplars to test if ebooks are properly set up.
 * Tests if the count words method works, and if spaces are correctly
 * counted for.
 */
public class ExamplarEBooks {

  /**
   * Sets up example to test if ebooks are properly set up.
   */
  @Test
  public void exampleTestFlow() {
    EBook ebk = new EBook(
            List.of(new Section("hello world", List.of(
                    new Paragraph(List.of(new TextFlow("apple pear"),
                            new TextFlow("banana orange strawberry"))),
                    new Paragraph(List.of(new TextFlow("apbos")))))));
    Assert.assertEquals(8, ebk.countWords());
  }

  @Test
  public void exampleCount() {
    EBook ebk = new EBook(
            List.of(new Section("hello world", List.of(
            new Paragraph(List.of(new TextFlow("hello world"),
            new TextFlow("hello"))),
            new Paragraph(List.of(new TextFlow("evelyn")))))));
    Assert.assertEquals(6, ebk.countWords());
  }

  // Tests that a list of the same paragraphs will throw a runtime exception
  @Test
  public void testTextFlowContainsSpace() {
    EBook ebk = new EBook(
            List.of(new Paragraph(List.of(new TextFlow("Hello World ")))));
    try {
      ebk.contains("Hello World");
    } catch (RuntimeException e) {
      throw e;
    }
    Assert.assertEquals(6,ebk.contains("evelyn yaskin"));
  }

  // Tests that an empty list throws a runtime exception, since it does not contain words
  @Test
  public void exampleTestContains2() {
    EBook ebk = new EBook(List.of(
            new Section("evelyn yaskin",
                    List.of())));
    Assert.assertTrue(ebk.contains("evelyn yaskin"));
  }

  // Tests that a substring of a word within a paragraph does not count as being "the same word"
  // will assert false
  @Test
  public void exampleTestContains1() {
    EBook ebk = new EBook(
            List.of(new Paragraph(List.of(new TextFlow("Hello World")))));
    Assert.assertFalse(ebk.contains("ell"));
  }

  // Tests that a substring of a word within a section does not count as being "the same word"
  // will assert false
  @Test
  public void example() {
    EBook ebk = new EBook(List.of(
            new Section("hello world",
                    List.of())));
    Assert.assertFalse(ebk.contains("ell"));
  }
}