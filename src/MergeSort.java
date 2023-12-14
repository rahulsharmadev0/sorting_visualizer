package src;

import java.util.ArrayList;
import java.util.List;
import src.models.AlgoProfile;
import src.models.TimeComplexity;

/**
 * Merge sort class
 *
 * @author rahulsharmadev
 */
public class MergeSort extends SortingAlgorithm {

  private List<Column> columns;
  private List<Column> helpList;

  /**
   * Sets the time for the sleep by calling the parent constructor.
   *
   * @param sleepTime the value of the sleep time
   */
  public MergeSort(int sleepTime) {
    super(sleepTime);
  }

  /**
   * Copies all the collection items into the auxiliary collection.
   *
   * @param min       the index of the first item to copy
   * @param max       the index of the last item to copy
   * @param highlight whether to highlight the columns that are being sorted or
   *                  not, the thread sleep is related with this
   */
  private void copy(int min, int max, boolean highlight) {
    for (int i = min; i <= max; i++) {
      if (highlight) {
        columns.get(i).Highlight();
        sleep();
      }
      helpList.get(i).SetValue(columns.get(i).GetValue());
      if (highlight) columns.get(i).Unhighlight();
    }
  }

  /**
   * Merges two sorted parts of the collection into one sorted part
   *
   * @param min1      the index of the first item in the first part of the
   *                  collection
   * @param max1      index of the last item in the first part of the collection
   * @param min2      index of the first item in the second part of the collection
   * @param max2      index of the last item in the second part of the collection
   * @param highlight whether to highlight the columns that are being sorted or
   *                  not, the thread sleep is related with this
   */
  private void merge(
    int min1,
    int max1,
    int min2,
    int max2,
    boolean highlight
  ) {
    copy(min1, max2, highlight);
    for (int i = min1, pointer1 = min1, pointer2 = min2; i <= max2; i++) {
      if (highlight) {
        columns.get(i).Highlight();
      }
      if (pointer1 <= max1 && pointer2 <= max2) {
        if (
          helpList.get(pointer1).GetValue() < helpList.get(pointer2).GetValue()
        ) {
          columns.get(i).SetValue(helpList.get(pointer1).GetValue());
          pointer1++;
        } else {
          columns.get(i).SetValue(helpList.get(pointer2).GetValue());
          pointer2++;
        }
      } else if (pointer1 <= max1) {
        columns.get(i).SetValue(helpList.get(pointer1).GetValue());
        pointer1++;
      } else if (pointer2 <= max2) {
        columns.get(i).SetValue(helpList.get(pointer2).GetValue());
        pointer2++;
      }
      if (highlight) {
        sleep();
        columns.get(i).Unhighlight();
      }
    }
  }

  /**
   * Method which do the sorting itself, it recursivelly calls itself in each
   * level for another two parts of the collection.
   *
   * @param min1      the index of the first item in the first part of the
   *                  collection
   * @param max1      index of the last item in the first part of the collection
   * @param min2      index of the first item in the second part of the collection
   * @param max2      index of the last item in the second part of the collection
   * @param highlight whether to highlight the columns that are being sorted or
   *                  not, the thread sleep is related with this
   */
  private void mergeSort(
    int min1,
    int max1,
    int min2,
    int max2,
    boolean highlight
  ) {
    if (min1 < max1) {
      int middle = (min1 + max1) / 2;
      mergeSort(min1, middle, middle + 1, max1, highlight);
    }
    if (min2 < max2) {
      int middle = (min2 + max2) / 2;
      mergeSort(min2, middle, middle + 1, max2, highlight);
    }
    merge(min1, max1, min2, max2, highlight);
  }

  @Override
  public void Sort(List<Column> columns, boolean highlight) {
    this.columns = columns;
    this.helpList = new ArrayList<Column>(0);
    for (int i = 0; i < columns.size(); i++) helpList.add(new Column(0));
    int middle = columns.size() / 2;
    mergeSort(0, middle, middle + 1, columns.size() - 1, highlight);
  }

  @Override
  public String toString() {
    return "Merge sort";
  }

  @Override
  public AlgoProfile note() {
    return new AlgoProfile(
      "Merge Sort",
      new TimeComplexity("O(n log n)", "O(n log n)", "O(n log n)"),
      "O(n)",
      "John von Neumann",
      "Merge Sort is a comparison-based sorting algorithm that follows the divide-and-conquer paradigm. It divides the input array into two halves, recursively sorts each half, and then merges the sorted halves to produce the final sorted array."
    );
  }
}
