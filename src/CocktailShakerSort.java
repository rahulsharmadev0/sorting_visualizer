package src;

import java.util.List;
import src.models.AlgoProfile;
import src.models.TimeComplexity;

/**
 * Coctail shaker sort class.
 *
 * @author rahulsharmadev
 */
public class CocktailShakerSort extends SortingAlgorithm {

  /**
   * Sets the time for the sleep by calling the parent constructor.
   *
   * @param sleepTime the value of the sleep time
   */
  public CocktailShakerSort(int sleepTime) {
    super(sleepTime);
  }

  @Override
  public void Sort(List<Column> columns, boolean highlight) {
    boolean done = false;
    for (
      int last = columns.size(), first = 0;
      last > first && !done;
      last--, first++
    ) {
      done = true;
      for (int j = first; j < last - 1; j++) {
        if (highlight) {
          columns.get(j).Highlight();
          columns.get(j + 1).Highlight();
          sleep();
        }
        if (columns.get(j).GetValue() > columns.get(j + 1).GetValue()) {
          Column temp = columns.get(j);
          columns.set(j, columns.get(j + 1));
          columns.set(j + 1, temp);
          done = false;
          if (highlight) sleep();
        }
        if (highlight) {
          columns.get(j).Unhighlight();
          columns.get(j + 1).Unhighlight();
        }
      }
      done = true;
      for (int j = last - 2; j > first - 1; j--) {
        if (highlight) {
          columns.get(j).Highlight();
          columns.get(j + 1).Highlight();
          sleep();
        }
        if (columns.get(j).GetValue() > columns.get(j + 1).GetValue()) {
          Column temp = columns.get(j);
          columns.set(j, columns.get(j + 1));
          columns.set(j + 1, temp);
          done = false;
          if (highlight) sleep();
        }
        if (highlight) {
          columns.get(j).Unhighlight();
          columns.get(j + 1).Unhighlight();
        }
      }
    }
  }

  @Override
  public String toString() {
    return "Cocktail shaker sort";
  }

  @Override
  public AlgoProfile note() {
    return new AlgoProfile(
      "Cocktail Shaker Sort",
      new TimeComplexity("O(n^2)", "O(n)", "O(n^2)"),
      "O(1)",
      "Unknown",
      "Cocktail Shaker Sort, also known as Bidirectional Bubble Sort, Shaker Sort, Ripple Sort, or Shuttle Sort, " +
      "is a variation of the Bubble Sort algorithm. It works by repeatedly stepping through the list, comparing " +
      "adjacent elements, and swapping them if they are in the wrong order. The process is then repeated in both " +
      "directions until no more swaps are needed. This bidirectional approach distinguishes Cocktail Shaker Sort from regular Bubble Sort."
    );
  }
}
