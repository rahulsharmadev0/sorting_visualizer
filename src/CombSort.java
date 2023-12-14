package src;

import java.util.List;
import src.models.AlgoProfile;
import src.models.TimeComplexity;

/**
 * Comb sort class
 *
 * @author rahulsharmadev
 */
public class CombSort extends SortingAlgorithm {

  /**
   * Sets the time for the sleep by calling the parent constructor.
   *
   * @param sleepTime the value of the sleep time
   */
  public CombSort(int sleepTime) {
    super(sleepTime);
  }

  @Override
  public void Sort(List<Column> columns, boolean highlight) {
    boolean swapped = true;
    double divider = 4 / 3.0;
    for (
      int space = (int) (columns.size() / divider);
      swapped || space > 1;
      space /= space > divider ? divider : 1
    ) {
      swapped = false;
      for (int first = 0; first < columns.size() - space; first++) {
        if (highlight) {
          columns.get(first).Highlight();
          columns.get(first + space).Highlight();
          sleep();
        }
        if (
          columns.get(first).GetValue() > columns.get(first + space).GetValue()
        ) {
          Column temp = columns.get(first);
          columns.set(first, columns.get(first + space));
          columns.set(first + space, temp);
          swapped = true;
          if (highlight) sleep();
        }
        if (highlight) {
          columns.get(first).Unhighlight();
          columns.get(first + space).Unhighlight();
        }
      }
    }
  }

  @Override
  public String toString() {
    return "Comb sort";
  }

  @Override
  public AlgoProfile note() {
    return new AlgoProfile(
      "Insertion Sort",
      new TimeComplexity("O(n^2)", "O(n log n)", "O(n^2 / 2^p)"),
      "O(1)",
      "Włodzimierz Dobosiewicz",
      "Comb Sort is a variation of Bubble Sort that improves upon its worst-case performance. The algorithm gets its name because it employs a technique called \"combing\" the list. It eliminates small turtles (small values near the end of the list) by comparing elements that are widely spaced apart and swapping them if necessary."
    );
  }
}
