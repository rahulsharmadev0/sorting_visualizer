package src;

import java.util.List;
import src.models.AlgoProfile;
import src.models.TimeComplexity;

/**
 * Counting sort class
 *
 * @author rahulsharmadev
 */
public class CountingSort extends SortingAlgorithm {

  /**
   * Sets the time for the sleep by calling the parent constructor.
   *
   * @param sleepTime the value of the sleep time
   */
  public CountingSort(int sleepTime) {
    super(sleepTime);
  }

  @Override
  public void Sort(List<Column> columns, boolean highlight) {
    int min = columns.get(0).GetValue();
    int max = columns.get(0).GetValue();
    for (int i = 0; i < columns.size(); i++) {
      if (highlight) {
        columns.get(i).Highlight();
        sleep();
      }
      if (columns.get(i).GetValue() < min) min = columns.get(i).GetValue();
      if (columns.get(i).GetValue() > max) max = columns.get(i).GetValue();
      if (highlight) columns.get(i).Unhighlight();
    }
    int[] counts = new int[max - min + 1];
    for (int i = 0; i < columns.size(); i++) {
      if (highlight) {
        columns.get(i).Highlight();
        sleep();
      }
      counts[columns.get(i).GetValue() - min]++;
      if (highlight) columns.get(i).Unhighlight();
    }
    for (int i = 0, j = 0; i < counts.length; i++) {
      while (counts[i]-- > 0) {
        columns.get(j).SetValue(i + min);
        if (highlight) {
          columns.get(j).Highlight();
          sleep();
          columns.get(j).Unhighlight();
        }
        j++;
      }
    }
  }

  @Override
  public String toString() {
    return "Counting sort";
  }

  @Override
  public AlgoProfile note() {
    return new AlgoProfile(
      "Counting Sort",
      new TimeComplexity("O(n + k)", "O(n + k)", "O(n + k)"),
      "O(k)",
      "Unknown",
      "Counting Sort is a non-comparison-based sorting algorithm that works well for integers or objects with a finite and small range of values. It operates by counting the number of occurrences of each element and using that information to determine the position of each element in the sorted output." +
      "<p>&nbsp;</p>\n" +
      "<p>The key steps of Counting Sort are as follows:</p>\n" +
      "\n" +
      "<p>&nbsp;</p>\n" +
      "\n" +
      "<p>1. Identify the range of input values (k).</p>\n" +
      "\n" +
      "<p>2. Create an auxiliary array (count array) of size k to store the count of each input element.</p>\n" +
      "\n" +
      "<p>3. Traverse the input array and increment the corresponding count in the count array.</p>\n" +
      "\n" +
      "<p>4. Modify the count array to store the cumulative count of elements.</p>\n" +
      "\n" +
      "<p>5. Create the output array.</p>\n" +
      "\n" +
      "<p>6. Place each element at its correct position in the output array based on the count array.</p>\n" +
      "\n" +
      "<p>7. Decrement the count for each element in the count array.</p>\n" +
      "\n" +
      "<p>Counting Sort has linear time complexity O(n + k), making it highly efficient for datasets where the range of values (k) is not significantly larger than the number of elements (n). However, it requires additional space proportional to the range of input values.</p>\n" +
      ""
    );
  }
}
