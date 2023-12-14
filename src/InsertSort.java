package src;

import java.util.List;
import src.models.AlgoProfile;
import src.models.TimeComplexity;

/**
 * Insert sort class
 *
 * @author rahulsharmadev
 */
public class InsertSort extends SortingAlgorithm {

  /**
   * Sets the time for the sleep by calling the parent constructor.
   *
   * @param sleepTime the value of the sleep time
   */
  public InsertSort(int sleepTime) {
    super(sleepTime);
  }

  @Override
  public void Sort(List<Column> columns, boolean highlight) {
    for (int i = 1; i < columns.size(); i++) {
      int moving = columns.get(i).GetValue();
      int pos = i;
      for (int j = i - 1; j >= 0; j--) {
        if (highlight) {
          columns.get(j + 1).Highlight();
          sleep();
          columns.get(j + 1).Unhighlight();
        }
        if (moving < columns.get(j).GetValue()) {
          pos = j;
          columns.get(j + 1).SetValue(columns.get(j).GetValue());
        } else break;
      }
      columns.get(pos).SetValue(moving);
      if (highlight) {
        columns.get(pos).Highlight();
        sleep();
        columns.get(pos).Unhighlight();
      }
    }
  }

  @Override
  public String toString() {
    return "Insertion sort";
  }

  @Override
  public AlgoProfile note() {
    return new AlgoProfile(
      "Insertion Sort",
      new TimeComplexity("O(n^2)", "O(n)", "O(n^2)"),
      "O(1)",
      "Tony Hoare",
      "Insertion Sort is a simple and efficient in-place sorting algorithm. It builds the final sorted array one element at a time. The algorithm iterates through the list, removes one element at a time, and inserts it into its correct position within the sorted part of the array. This process is repeated until the entire list is sorted."
    );
  }
}
