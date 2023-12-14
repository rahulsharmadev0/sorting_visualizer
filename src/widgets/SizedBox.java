package src.widgets;

import java.awt.*;
import javax.swing.*;

public abstract class SizedBox {

  public static JPanel raw(int w, int h) {
    JPanel emptySpace = new JPanel();
    emptySpace.setMaximumSize(new Dimension(w, h));
    return emptySpace;
  }

  public static JPanel width(int value) {
    JPanel emptySpace = new JPanel();
    emptySpace.setMaximumSize(new Dimension(value, 1));
    return emptySpace;
  }

  public static JPanel height(int value) {
    JPanel emptySpace = new JPanel();
    emptySpace.setMaximumSize(new Dimension(1, value));
    return emptySpace;
  }
}
