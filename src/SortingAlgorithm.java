package src;

import java.util.List;
import src.models.AlgoProfile;

/**
 * Abstract class used as an interface for the sorting algorithms.
 *
 * @author rahulsharmadev
 */
public abstract class SortingAlgorithm {

  private int sleepTime;

  /**
   * Sets the time for the sleep
   *
   * @param sleepTime the value of the sleep time
   */
  protected SortingAlgorithm(int sleepTime) {
    this.sleepTime = sleepTime;
  }

  /**
   * sleppTime setter
   *
   * @param sleepTime sleep time value
   */
  public void SetSleepTime(int sleepTime) {
    this.sleepTime = sleepTime;
  }

  /**
   * Put the thread to sleep on the specified time. Used to visualize the sorting
   * after the column is highlighted.
   */
  protected void sleep() {
    try {
      Thread.sleep(sleepTime);
    } catch (Exception ex) {}
  }

  /**
   * Method for sorting, each algorithm implements it by itself.
   *
   * @param columns   column collection to sort, any collection implementing
   *                  java.util.List interface can be given
   * @param highlight whether to highlight the column that are being sorted or
   *                  not, the thread sleep is related with this
   */
  public abstract void Sort(List<Column> columns, boolean highlight);

  /**
   * Returns the name of the sorting algorithm
   *
   * @return name of the sorting algorithm
   */
  @Override
  public abstract String toString();

  public abstract AlgoProfile note();
}
