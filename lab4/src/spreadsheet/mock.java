package spreadsheet;

import java.io.StringReader;

public class mock implements SpreadSheet {
  public StringBuilder log;


  public mock(StringBuilder log) {
    this.log = log;
  }


  @Override
  public double get(int row, int col) throws IllegalArgumentException {
     log.append(row).append(" : ").append(col);
     return 0;
  }

  @Override
  public void set(int row, int col, double value) throws IllegalArgumentException {
    log.append(row).append(" ").append(col);
  }

  @Override
  public boolean isEmpty(int row, int col) throws IllegalArgumentException {
    return false;
  }

  @Override
  public int getWidth() {
    return 0;
  }

  @Override
  public int getHeight() {
    return 0;
  }
}
