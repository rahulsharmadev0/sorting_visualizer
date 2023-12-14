package src;

import java.util.List;
import src.models.AlgoProfile;
import src.models.TimeComplexity;

/**
 * Gnome sort class
 *
 * @author rahulsharmadev
 */
public class GnomeSort extends SortingAlgorithm {

  /**
   * Sets the time for the sleep by calling the parent constructor.
   *
   * @param sleepTime the value of the sleep time
   */
  public GnomeSort(int sleepTime) {
    super(sleepTime);
  }

  @Override
  public void Sort(List<Column> columns, boolean highlight) {
    for (int i = 1; i < columns.size(); i++) {
      if (highlight) {
        columns.get(i).Highlight();
        sleep();
      }
      for (
        int j = i;
        columns.get(j).GetValue() < columns.get(j > 0 ? j - 1 : j).GetValue();
        j--
      ) {
        if (highlight) {
          columns.get(j).Highlight();
          columns.get(j > 0 ? j - 1 : j).Highlight();
          sleep();
        }
        Column temp = columns.get(j);
        columns.set(j, columns.get(j - 1));
        columns.set(j - 1, temp);
        if (highlight) {
          sleep();
          columns.get(j).Unhighlight();
          columns.get(j > 0 ? j - 1 : j).Unhighlight();
        }
      }
      if (highlight) columns.get(i).Unhighlight();
    }
  }

  @Override
  public String toString() {
    return "Gnome sort";
  }

  @Override
  public AlgoProfile note() {
    return new AlgoProfile(
      "Gnome Sort",
      new TimeComplexity("O(n^2)", "O(n)", "O(n^2)"),
      "O(1)",
      "Unknown",
      "Gnome Sort, also known as Stupid Sort, is a simple sorting algorithm that is similar to Insertion Sort. It works by repeatedly swapping adjacent elements that are out of order until the entire list is sorted. The algorithm gets its name from the way a gnome in a garden might sort a line of flower pots – by comparing adjacent pots and moving backward if necessary." +
      "<p>&nbsp;</p>\n" +
      "<p>The steps of Gnome Sort can be summarized as follows:</p>\n" +
      "<p>&nbsp;</p>\n" +
      "<ol>\n" +
      "\t<li>Start with the first element of the list.</li>\n" +
      "\t<li>Compare the current element with the previous one.</li>\n" +
      "\t<li>If they are in the correct order, move to the next element.</li>\n" +
      "\t<li>If they are out of order, swap them and move one step backward.</li>\n" +
      "\t<li>Repeat steps 2-4 until the entire list is sorted.</li>\n" +
      "</ol>\n" +
      "\n" +
      "<p>Gnome Sort has a quadratic time complexity, making it less efficient than many other sorting algorithms for large datasets. It is often used for educational purposes due to its simplicity.</p>\n"
    );
  }
}
