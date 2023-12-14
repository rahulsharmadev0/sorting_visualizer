package src;

import java.util.List;
import src.models.AlgoProfile;
import src.models.TimeComplexity;

/**
 * Select sort class
 *
 * @author rahulsharmadev
 */
public class SelectSort extends SortingAlgorithm {

  /**
   * Sets the time for the sleep by calling the parent constructor.
   *
   * @param sleepTime the value of the sleep time
   */
  public SelectSort(int sleepTime) {
    super(sleepTime);
  }

  @Override
  public void Sort(List<Column> columns, boolean highlight) {
    for (int i = 0; i < columns.size(); i++) {
      if (highlight) {
        columns.get(i).Highlight();
        sleep();
      }
      int minIndex = i;
      for (int j = i; j < columns.size(); j++) {
        if (highlight) {
          columns.get(j).Highlight();
          sleep();
        }
        if (columns.get(j).GetValue() < columns.get(minIndex).GetValue()) {
          if (highlight && minIndex != i) columns.get(minIndex).Unhighlight();
          minIndex = j;
        } else if (highlight && j != i) columns.get(j).Unhighlight();
      }
      if (highlight) {
        columns.get(minIndex).Highlight();
        sleep();
      }
      Column temp = columns.get(minIndex);
      columns.set(minIndex, columns.get(i));
      columns.set(i, temp);
      if (highlight) {
        sleep();
        columns.get(minIndex).Unhighlight();
        columns.get(i).Unhighlight();
      }
    }
  }

  @Override
  public String toString() {
    return "Selection sort";
  }

  @Override
  public AlgoProfile note() {
    return new AlgoProfile(
      "Selection Sort",
      new TimeComplexity("O(n^2)", " O(n^2)", "O(n^2)"),
      "O(1)",
      "Unknown",
      "Selection Sort is a simple comparison-based sorting algorithm. The basic idea is to divide the input list into a sorted and an unsorted region. The algorithm repeatedly selects the smallest (or largest, depending on the sorting order) element from the unsorted region and swaps it with the first unsorted element. This process continues until the entire list is sorted."
    );
  }
}
