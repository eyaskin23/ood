
package cs3500.lab1;

import java.util.List;
import java.util.Comparator;
public class SortUtils {
  public static <T> List<T> sortStrings(List<T> values, Comparator<T> compare) {
    values.sort(compare);
    return values;
  }
  public static <T> List<T> sort(List<T> values, Comparator<? super T> comp) {
    throw new RuntimeException("Not yet implemented");
  }
}
