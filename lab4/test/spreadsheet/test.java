package spreadsheet;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.io.StringReader;
import java.util.Arrays;



public class test {

  @Test
  public void testWelcome() {
    SparseSpreadSheet sparse = new SparseSpreadSheet();
    StringReader input =  new StringReader("q");
    StringBuilder log = new StringBuilder();
    SpreadSheetController sheet = new SpreadSheetController(sparse, input, log);
    sheet.go();
    String[] lines = log.toString().split("\n");
    String msg = String.join("\n", Arrays.copyOfRange(lines, 0, 6));

    Assert.assertEquals("Welcome to the spreadsheet program!\n" +
            "Supported user instructions are: \n" +
            "assign-value row-num col-num value (set a cell to a value)\n" +
            "print-value row-num col-num (print the value at a given cell)\n" +
            "menu (Print supported instruction list)\n" +
            "q or quit (quit the program) ", msg);
  }

  @Test
  public void testFarewell() {
    SparseSpreadSheet sparse = new SparseSpreadSheet();
    StringReader input =  new StringReader("q");
    StringBuilder log = new StringBuilder();
    SpreadSheetController sheet = new SpreadSheetController(sparse, input, log);
    sheet.go();
    String[] lines = log.toString().split("\r\n");
    String msg = String.join("\r\n", Arrays.copyOfRange(lines, 0, 6));
    Assert.assertTrue(msg.contains("Thank you for using this program!"));
  }


  @Test
  public void testSet() {
    StringBuilder log = new StringBuilder();
    SpreadSheet mock = new mock(log);
    StringReader input =  new StringReader("assign-value a 1 4.0 q");
    StringBuilder log2 = new StringBuilder();
    SpreadSheetController sheet = new SpreadSheetController(mock, input, log2);
    sheet.go();
    String msg = log.toString();
    Assert.assertEquals("0 : 0", msg);
  }

  @Test
  public void testCorrectInputs() {
    StringBuilder log = new StringBuilder();
    SpreadSheet mock = new mock(log);
    StringReader input =  new StringReader("assign-value a 1 4.0 print-value a 1 q");
    StringBuilder log2 = new StringBuilder();
    SpreadSheetController sheet = new SpreadSheetController(mock, input, log2);
    sheet.go();
    String msg = log.toString();
    Assert.assertEquals("0 00 : 0", msg);
  }
}
