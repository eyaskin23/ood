package cs3500.lab1;

import org.junit.Assert;
import org.junit.Test;

public class SimpleFraction {
  private int numerator;
  private int denominator;

  public SimpleFraction(int numerator, int denominator) {
    this.numerator = numerator;
    this.denominator = denominator;
    if (denominator == 0) {
      throw new IllegalArgumentException("Denominator cannot be 0");
    }
    if (numerator < 0 || denominator < 0) {
      throw new IllegalArgumentException("Fraction cannot be negative");
    }
  }

  @Override
  public String toString() {
    return numerator + "/" + denominator;
  }

  @Test
  public void testToString() {
    SimpleFraction fraction = new SimpleFraction(4, 9);
    Assert.assertEquals("4/9", fraction.toString());
  }
}
