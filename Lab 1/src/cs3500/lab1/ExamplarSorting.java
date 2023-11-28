
import cs3500.lab1.SortUtils;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class ExamplarSorting {
  @Test
  public void exampleEmptyBaseCase() {
    List<String> empty = new ArrayList<>();
    List<String> sorted = SortUtils.sortStrings(empty, Comparator.naturalOrder());
    List<String> expected = new ArrayList<>();
    Assert.assertEquals("Empty lists should already be sorted", expected, sorted);
  }

  @Test
  public void exampleSortedCase() {
    List<String> sortedCase = Arrays.asList("a", "b", "c", "d");
    List<String> sorted = SortUtils.sortStrings(sortedCase, Comparator. naturalOrder());
    List<String> expected = Arrays.asList("a", "b", "c", "d");
    Assert.assertEquals("List should be sorted alphabetically a->z", expected, sorted);
  }

  @Test
  public void exampleSortNums() {
    List<Integer> sortedCase = Arrays.asList(4, 2, 3, 1);
    List<Integer> sorted = SortUtils.sort(sortedCase, Comparator.naturalOrder());
    List<Integer> expected = Arrays.asList(1, 2, 3, 4);
    Assert.assertEquals("List of numbers should be sorted by value", expected, sorted);
  }

  @Test
  public void exampleReversed() {
    List<String> reversedCase = Arrays.asList("c", "b", "a");
    List<String> sorted = SortUtils.sortStrings(reversedCase, Comparator. reverseOrder());
    List<String> expected = Arrays.asList("c", "b", "a");
    Assert.assertEquals("List should be sorted alphabetically z->a", expected, sorted);
  }

//  @Test
//  public void exampleReversedNums() {
//    List<Integer> reversedCase = Arrays.asList(2, 3, 4, 1);
//    List<Integer> sorted = SortUtils.sort(reversedCase, Comparator. reverseOrder());
//    List<Integer> expected = Arrays.asList(4, 3, 2, 1);
//    Assert.assertEquals("List of numbers should be sorted largest to smallest", expected, sorted);
//  }

  @Test
  public void exampleNull() {
    List<String> list = Arrays.asList("a", null, "b");
    List<String> sorted = SortUtils.sortStrings(list, Comparator.nullsFirst(Comparator.naturalOrder()));
    List<String> expected = Arrays.asList(null, "a", "b");
    Assert.assertEquals("A null list should already be sorted regardless of null values", expected, sorted);
  }

  @Test
  public void exampleNullGeneric() {
    List<Integer> list = Arrays.asList(1, null, 2);
    List<Integer> sorted = SortUtils.sort(list, Comparator.nullsFirst(Comparator.naturalOrder()));
    List<Integer> expected = Arrays.asList(null, 1, 2);
    Assert.assertEquals("A generic null list should already be sorted regardless of null values", expected, sorted);
  }

  @Test
  public void exampleMutation() {
    List<String> unSorted = Arrays.asList("a", "c", "b");
    List<String> sorted = SortUtils.sortStrings(unSorted, Comparator.naturalOrder());
    List<String> expected = Arrays.asList("a", "c", "b");
    Assert.assertEquals("A mutated list should stay the same", expected, unSorted);
  }

  @Test
  public void exampleNegativeInt() {
    List<String> compareNegativeInts = Arrays.asList("-1", "-2", "-3", "-4");
    List<String> sorted = SortUtils.sortStrings(compareNegativeInts, Comparator.comparingInt(Integer::parseInt));
    List<String> expected = Arrays.asList("-4", "-3", "-2", "-1");
    Assert.assertEquals("Negative Integers List", expected, sorted);
  }

  @Test
  public void exampleMutationGen() {
    List<Integer> list = Arrays.asList(1, 3, 2);
    List<Integer> expected = Arrays.asList(1, 3, 2);
    List<Integer> sorted = SortUtils.sort(list, Comparator.naturalOrder());
    Assert.assertEquals("Mutation undetected", expected, list);
  }

  @Test
  public void exampleSameValue() {
    List<String> list =  Arrays.asList("a", "a", "a");
    List<String> sorted = SortUtils.sortStrings(list, Comparator.naturalOrder());
    List<String> expected = Arrays.asList("a", "a", "a");
    Assert.assertEquals("A list of the same value should already be sorted", expected, sorted);
  }
}