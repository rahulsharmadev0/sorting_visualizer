package src;

import java.util.List;
import src.models.AlgoProfile;
import src.models.TimeComplexity;

/**
 * Heap sort class
 *
 * @author rahulsharmadev
 */
public class HeapSort extends SortingAlgorithm {

  /**
   * Sets the time for the sleep by calling the parent constructor.
   *
   * @param sleepTime the value of the sleep time
   */
  public HeapSort(int sleepTime) {
    super(sleepTime);
  }

  /**
   * Places the item at the correct position in the heap by comparing it with its
   * parent and if its greater it will swap them, the same thing is done at upper
   * level and so on untill the item is lower than its new father or untill it
   * reach the heap root
   *
   * @param columns   column collection
   * @param i         item index
   * @param highlight whether to highlight the column that are being sorted or
   *                  not, the thread sleep is related with this
   */
  private void up(List<Column> columns, int i, boolean highlight) {
    boolean done = false;
    while (i > 0 && !done) {
      if (highlight) {
        columns.get(i).Highlight();
        columns.get((i - 1) / 2).Highlight();
        sleep();
      }
      if (columns.get((i - 1) / 2).GetValue() < columns.get(i).GetValue()) {
        Column temp = columns.get((i - 1) / 2);
        columns.set((i - 1) / 2, columns.get(i));
        columns.set(i, temp);
        if (highlight) sleep();
      } else done = true;
      if (highlight) {
        columns.get(i).Unhighlight();
        columns.get((i - 1) / 2).Unhighlight();
      }
      i = (i - 1) / 2;
    }
  }

  /**
   * Repairs the heap, begins at root which is taken as a father and compared with
   * its sons if some of them is greater it is swapped with the father and so on
   * till there is something to swap or the loop reaches the end of the heap.
   *
   * @param columns   column collection
   * @param last      the index of the last item in the heap
   * @param highlight whether to highlight the columns that are being sorted or
   *                  not, the thread sleep is related with this
   */
  private void down(List<Column> columns, int last, boolean highlight) {
    int i = 0;
    boolean done = false;
    while (i < last && !done) {
      if (highlight) {
        if (2 * i + 1 < last) columns.get(2 * i + 1).Highlight();
        if (2 * i + 2 < last) columns.get(2 * i + 2).Highlight();
        sleep();
      }
      int biggerSon = 2 * i + 2;
      if (
        biggerSon >= last ||
        columns.get(2 * i + 1).GetValue() > columns.get(biggerSon).GetValue()
      ) biggerSon = 2 * i + 1;
      if (highlight) {
        if (2 * i + 1 < last) columns.get(2 * i + 1).Unhighlight();
        if (2 * i + 2 < last) columns.get(2 * i + 2).Unhighlight();
      }
      if (biggerSon >= last) break;
      if (highlight) {
        columns.get(i).Highlight();
        columns.get(biggerSon).Highlight();
        sleep();
      }
      if (columns.get(biggerSon).GetValue() > columns.get(i).GetValue()) {
        Column temp = columns.get(biggerSon);
        columns.set(biggerSon, columns.get(i));
        columns.set(i, temp);
        if (highlight) sleep();
      } else done = true;
      if (highlight) {
        columns.get(i).Unhighlight();
        columns.get(biggerSon).Unhighlight();
      }
      i = biggerSon;
    }
  }

  /**
   * Builds the heap using the up method
   *
   * @param columns   column collection
   * @param highlight whether to highlight the columns that are being sorted or
   *                  not, the thread sleep is related with this
   */
  private void makeHeap(List<Column> columns, boolean highlight) {
    for (int i = 1; i < columns.size(); i++) up(columns, i, highlight);
  }

  @Override
  public void Sort(List<Column> columns, boolean highlight) {
    makeHeap(columns, highlight);
    for (int last = columns.size() - 1; last >= 0; last--) {
      if (highlight) {
        columns.get(0).Highlight();
        columns.get(last).Highlight();
        sleep();
      }
      Column temp = columns.get(0);
      columns.set(0, columns.get(last));
      columns.set(last, temp);
      sleep();
      if (highlight) {
        sleep();
        columns.get(0).Unhighlight();
        columns.get(last).Unhighlight();
      }
      down(columns, last, highlight);
    }
  }

  @Override
  public String toString() {
    return "Heap sort";
  }

  @Override
  public AlgoProfile note() {
    return new AlgoProfile(
      "Heap Sort",
      new TimeComplexity("O(n log n)", "O(n log n)", "O(n log n)"),
      "O(1)",
      "J.W.J. Williams",
      "Heap Sort is a comparison-based sorting algorithm that uses a binary heap data structure to build a max-heap or min-heap. In the context of Heap Sort, the max-heap is commonly used. The algorithm has two main phases: heapify and sorting."
    );
  }
}
