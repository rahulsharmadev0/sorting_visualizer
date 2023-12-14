package src;

import java.awt.*;

/**
 * Column class.
 *
 * @author rahulsharmadev
 */
public class Column {

  private static final Color H_COL = new Color(255, 0, 0);
  private static final Color NH_COL = new Color(0, 255, 0);
  private static final Color DARK_H_COL = new Color(127, 0, 0);
  private static final Color DARK_NH_COL = new Color(0, 127, 0);

  private int value;
  private boolean highlighted = false;

  /**
   * Constructor for setting the column value.
   *
   * @param value column value
   */
  public Column(int value) {
    this.value = value;
  }

  /**
   * Column value getter
   *
   * @return column value
   */
  public int GetValue() {
    return value;
  }

  /**
   * Column value setter
   *
   * @param value
   */
  public void SetValue(int value) {
    this.value = value;
  }

  /**
   * Highlights the column.
   */
  public void Highlight() {
    this.highlighted = true;
  }

  /**
   * Cancels the column highlighting
   */
  public void Unhighlight() {
    this.highlighted = false;
  }

  /**
   * Draws the column.
   *
   * @param bottom bottom position of column y
   * @param xPos   left position of column x
   * @param width  column width
   * @param unit   multiplier of the column height, the resulting height is
   *               (value * unit) pixels
   */
  public void Draw(int bottom, int xPos, int width, float unit, Graphics g) {
    final int Y_POS = (int) (bottom - unit * value + 1);
    final int HEIGHT = (int) (bottom - unit * value);

    final int DEPTH = (width < 100 ? width / 6 : 25);

    if (width > 12) {
      g.setColor(highlighted ? DARK_H_COL : DARK_NH_COL);
      for (int i = DEPTH + 1; i >= 1; i--) g.fill3DRect(
        xPos + i,
        Y_POS - i,
        width,
        bottom - HEIGHT,
        true
      );
    }
    g.setColor(highlighted ? H_COL : NH_COL);
    g.fill3DRect(
      xPos,
      (int) (bottom - unit * value + 1),
      width,
      bottom - (int) (bottom - unit * value),
      true
    );
  }
}
