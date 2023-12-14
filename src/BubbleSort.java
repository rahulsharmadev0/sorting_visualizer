package src;

import java.util.List;
import src.models.AlgoProfile;
import src.models.TimeComplexity;

/**
 * Bubble sort class.
 *
 * @author rahulsharmadev
 */
public class BubbleSort extends SortingAlgorithm {

  /**
   * Sets the time for the sleep by calling the parent constructor.
   *
   * @param sleepTime the value of the sleep time
   */
  public BubbleSort(int sleepTime) {
    super(sleepTime);
  }

  @Override
  public void Sort(List<Column> columns, boolean highlight) {
    for (
      int i = 0, last = columns.size();
      i < columns.size();
      i++, last--
    ) for (int j = 0; j < last - 1; j++) {
      if (highlight) {
        columns.get(j).Highlight();
        columns.get(j + 1).Highlight();
        sleep();
      }
      if (columns.get(j).GetValue() > columns.get(j + 1).GetValue()) {
        Column temp = columns.get(j);
        columns.set(j, columns.get(j + 1));
        columns.set(j + 1, temp);
        if (highlight) sleep();
      }
      if (highlight) {
        columns.get(j).Unhighlight();
        columns.get(j + 1).Unhighlight();
      }
    }
  }

  @Override
  public String toString() {
    return "Bubble sort";
  }

  @Override
  public AlgoProfile note() {
    return new AlgoProfile(
      "Bubble Sort",
      new TimeComplexity("O(n^2)", "O(n)", "O(n^2)"),
      "O(1)",
      "Unknown",
      "Bubble Sort is a simple sorting algorithm that repeatedly steps through the list, compares adjacent elements, " +
      "and swaps them if they are in the wrong order. The pass through the list is repeated until the list is sorted.  " +
      "The algorithm gets its name because smaller elements \"bubble\" to the top of the list. " +
      " It is one of the simplest sorting algorithms, but it is inefficient for large lists."
    );
  }
}
