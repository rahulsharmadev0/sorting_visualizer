package src;

import java.util.List;
import src.models.AlgoProfile;
import src.models.TimeComplexity;

/**
 * Shell sort class
 *
 * @author rahulsharmadev
 */
public class ShellSort extends SortingAlgorithm {

  /**
   * Sets the time for the sleep by calling the parent constructor.
   *
   * @param sleepTime the value of the sleep time
   */
  public ShellSort(int sleepTime) {
    super(sleepTime);
  }

  @Override
  public void Sort(List<Column> columns, boolean highlight) {
    for (int space = columns.size() / 2; space > 0; space /= 2) for (
      int i = space;
      i < columns.size();
      i++
    ) {
      int moving = columns.get(i).GetValue();
      int pos = i;
      for (int j = i - space; j >= 0; j -= space) {
        if (highlight) {
          columns.get(j + space).Highlight();
          sleep();
          columns.get(j + space).Unhighlight();
        }
        if (moving < columns.get(j).GetValue()) {
          pos = j;
          columns.get(j + space).SetValue(columns.get(j).GetValue());
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
    return "Shell sort";
  }

  @Override
  public AlgoProfile note() {
    return new AlgoProfile(
      "Shell Sort",
      new TimeComplexity("O(n^2)", "O(n log n)", "O(n log n)"),
      "O(1)",
      "Donald Shell",
      "Shell Sort, also known as diminishing increment sort, is an in-place comparison-based sorting algorithm. " +
      "It is an extension of insertion sort where the array is divided into smaller subarrays, and then those " +
      "subarrays are sorted using an insertion sort. The unique feature of Shell Sort is that it starts by sorting" +
      " pairs of elements far apart from each other and progressively reduces the gap between elements to be compared."
    );
  }
}
