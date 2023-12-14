package src;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Column collection class
 *
 * @author rahulsharmadev
 */
public class ColumnCollection {

  private List<Column> columns;
  private SortingAlgorithm sortingAlgorithm = new QuickSort(0);

  /**
   * Constructor for initialization of column collection.
   *
   * @param columns collection of columns which implements the List interface
   */
  public ColumnCollection(List<Column> columns) {
    SetColumns(columns);
  }

  /**
   * Column collection setter
   *
   * @param columns column collection which implements List interface
   */
  public void SetColumns(List<Column> columns) {
    this.columns = new ArrayList<Column>();
    for (int i = 0; i < columns.size(); i++) this.columns.add(
        new Column(columns.get(i).GetValue())
      );
  }

  /**
   * Sorting algorithm setter
   *
   * @param sa object of the class which extends the abstract class
   *           SortingAlgorithm
   */
  public void SetSortingAlgorithm(SortingAlgorithm sa) {
    this.sortingAlgorithm = sa;
  }

  public SortingAlgorithm GetSortingAlgorithm() {
    return this.sortingAlgorithm;
  }

  /**
   * Draws all the columns
   *
   * @param xBegin x the beginning of the space for drawing columns
   * @param xEnd   x the end of the space for drawing columns
   * @param yBegin y the beginning of the space for drawing columns
   * @param yEnd   y the end of the space for drawing columns
   * @param g      canvas
   */
  public void Draw(int xBegin, int xEnd, int yBegin, int yEnd, Graphics g) {
    int width = (xEnd - xBegin) / columns.size();

    int space = (int) (width * 0.2);

    float unit = (float) (yEnd - yBegin) / 100f;
    for (int i = 0; i < columns.size(); i++) {
      if (width < 1) width = 1;
      columns.get(i).Draw(yEnd, xBegin + i * width, width - space, unit, g);
    }
  }

  /**
   * Method for sorting the column collection in the given object.
   *
   * @param highlight whether to highlight the columns while sorting or not, the
   *                  thread sleep is related to this
   */
  public void Sort(boolean highlight) {
    sortingAlgorithm.Sort(columns, highlight);
  }

  /**
   * Returns the column collection (something like getter).
   *
   * @return column collection of the given object returned in the object
   *         implementing List interface
   */
  public List<Column> ToList() {
    return columns;
  }
}
