package src;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import src.models.AlgoProfile;
import src.widgets.SizedBox;

/**
 * Class for window which draws the column charts.
 *
 * @author rahulsharmadev
 */
public class MaterialApp extends JFrame {

  private MaterialApp thisWindow = this;
  private JDialog dialog = new JDialog(thisWindow);
  private JDialog licenceDialog = new JDialog(thisWindow);
  private Integer[] countArray = {
    30,
    40,
    50,
    60,
    80,
    120,
    160,
    240,
    480,
    960,
  };
  private Integer[] speedArray = { 25, 20, 15, 13, 11, 9, 7, 5, 3, 1 };
  private int activeSpeed = speedArray[0];
  private SortingAlgorithm[] algorithmsListView = {
    new CountingSort(activeSpeed),
    new MergeSort(activeSpeed),
    new SelectSort(activeSpeed),
    new InsertSort(activeSpeed),
    new ShellSort(activeSpeed),
    new GnomeSort(activeSpeed),
    new HeapSort(activeSpeed),
    new QuickSort(activeSpeed),
    new CocktailShakerSort(activeSpeed),
    new CombSort(activeSpeed),
  };
  JComboBox<Integer> counts = new JComboBox<>(countArray);

  private List<Column> columns;
  private ColumnPanel panels;
  private Random rnd = new Random();
  private JPanel menu = new JPanel();

  public class AlgoThread {

    private Thread thread = new Thread(new Execution());

    private class Execution extends Thread {

      @Override
      public void run() {
        panels.GetColumns().Sort(true);
      }
    }

    boolean isAlive() {
      return thread.isAlive();
    }

    void stop() {
      thread.stop();
    }

    void start() {
      thread = new Thread(new Execution());
      thread.start();
    }
  }

  AlgoThread sortingThread = new AlgoThread();

  /**
   * Contructor which sets the width and height of the window and the column
   * collection, it also works as a main method of the class, so it will show the
   * window and run all the needed threads.
   *
   * @param w       window width
   * @param h       window height
   * @param columns column collection which implements the List interface
   */
  public MaterialApp(int w, int h) {
    super("Sorting Visualizer");
    menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
    this.addWindowListener(
        new WindowAdapter() {
          @Override
          public void windowClosing(WindowEvent winEvt) {
            sortingThread.stop();
          }
        }
      );
    this.columns = getRndColumnArray(countArray[0]);

    setPanels(w - 200, h);
    setSortingAlgorithms();
    setCounts();
    setSpeed();

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(w + 10, h + 85);
    this.setLocationRelativeTo(null);
    this.setVisible(true);
    this.setResizable(false);
    panels.GetColumns().Sort(false);
    startRepaintThread();
    sortingThread.start();
  }

  /**
   * Generates random column sequence.
   *
   * @param count column count
   * @return generated sequence
   */
  private ArrayList<Column> getRndColumnArray(int count) {
    ArrayList<Column> columns = new ArrayList<Column>(0);
    for (int i = 0; i < count; i++) columns.add(
      new Column(rnd.nextInt(99) + 1)
    );
    return columns;
  }

  /**
   * Adds all the sorting algorithms into the sortingAlgorithms combobox and sets
   * the change value event for the combobox
   */
  private void setSortingAlgorithms() {
    JPanel panel = new JPanel();
    panel.setBorder(new EmptyBorder(8, 8, 8, 8));
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    JPanel listContainer = new JPanel(new GridLayout(2, 2, 4, 4));
    listContainer.setMaximumSize(new Dimension(200, 80));

    Button button = new Button("Start");
    Button random = new Button("Random");
    Button about = new Button("About");
    Button license = new Button("License");

    random.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          columns = getRndColumnArray((int) counts.getSelectedItem());
          panels.GetColumns().SetColumns(columns);
          sortingThread.stop();
        }
      }
    );

    about.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          dialogUpdate();
          dialog.setVisible(true);
        }
      }
    );

    license.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          licenceDialog();
        }
      }
    );
    listContainer.add(button);
    listContainer.add(random);
    listContainer.add(about);
    listContainer.add(license);

    button.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          if (!sortingThread.isAlive()) {
            sortingThread.start();
            button.setLabel("Stop");
          } else {
            sortingThread.stop();
            button.setLabel("Start");
          }
        }
      }
    );

    panel.add(listContainer);
    panel.add(SizedBox.height(8));

    JList<SortingAlgorithm> jlist = new JList<>(algorithmsListView);
    jlist.setMaximumSize(new Dimension(200, 300));
    jlist.setFixedCellWidth(150);
    jlist.setFixedCellHeight(20);

    Font awtFont = new Font("Dialog", Font.PLAIN, 12); // Example font, adjust as needed
    jlist.setFont(awtFont);

    TitledBorder border = BorderFactory.createTitledBorder(
      "Sorting Algorithms"
    );
    border.setTitleColor(Color.DARK_GRAY);
    jlist.setBackground(new Color(238, 238, 238));

    jlist.setBorder(border);
    panel.add(jlist);
    jlist.addMouseListener(
      new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          sortingThread.stop();
          panels.GetColumns().SetColumns(columns);
          panels
            .GetColumns()
            .SetSortingAlgorithm((SortingAlgorithm) jlist.getSelectedValue());
          panels.GetColumns().GetSortingAlgorithm().SetSleepTime(activeSpeed);

          sortingThread.start();
          dialogUpdate();
          super.mouseClicked(e);
        }
      }
    );

    menu.add(panel);
    this.add(menu, BorderLayout.EAST);
  }

  private void setSpeed() {
    JPanel countContainer = new JPanel();
    countContainer.setMaximumSize(new Dimension(200, 48));
    countContainer.setAlignmentX(RIGHT_ALIGNMENT);
    countContainer.setAlignmentY(TOP_ALIGNMENT);
    JComboBox<String> speed = new JComboBox<String>();
    JLabel Label = new JLabel("Speed:");
    countContainer.add(Label);
    countContainer.add(speed);
    for (int i = 0; i < speedArray.length; i++) {
      speed.addItem(i + 1 + "x");
    }
    menu.add(countContainer);
    speed.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          activeSpeed = speedArray[speed.getSelectedIndex()];
          panels.GetColumns().GetSortingAlgorithm().SetSleepTime(activeSpeed);
          System.out.println(activeSpeed);
        }
      }
    );
  }

  /**
   * Adds all the required values into the counts combobox and sets change value
   * event for that combobox
   */
  private void setCounts() {
    JPanel countContainer = new JPanel();
    countContainer.setMaximumSize(new Dimension(200, 48));
    countContainer.setAlignmentX(RIGHT_ALIGNMENT);
    countContainer.setAlignmentY(TOP_ALIGNMENT);
    JLabel label = new JLabel("Size:");
    countContainer.add(label);
    countContainer.add(counts);
    menu.add(countContainer);
    counts.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          sortingThread.stop();
          columns = getRndColumnArray((int) counts.getSelectedItem());
          panels.GetColumns().SetColumns(columns);
          panels.repaint();
          panels.GetColumns().GetSortingAlgorithm().SetSleepTime(activeSpeed);
          sortingThread.start();
        }
      }
    );
  }

  /**
   * Sets panels for drawing columns
   *
   * @param w whole window width
   * @param h whole window height
   */
  private void setPanels(int w, int h) {
    panels = new ColumnPanel(columns);
    panels.setPreferredSize(new Dimension(w, h));
    panels.setVisible(true);
    this.add(panels, BorderLayout.CENTER);
  }

  /**
   * Starts re-rendering thread of the third (bottom) panel, this thread will run
   * untill the window is not closed.
   */
  private void startRepaintThread() {
    Thread t = new Thread(
      new Runnable() {
        @Override
        public void run() {
          while (thisWindow.isVisible()) {
            panels.repaint();
            try {
              Thread.sleep(10);
            } catch (Exception ex) {}
          }
        }
      }
    );
    t.start();
  }

  private void dialogUpdate() {
    AlgoProfile algoProfile = panels.GetColumns().GetSortingAlgorithm().note();
    dialog.setTitle(algoProfile.name);
    dialog.setSize(new Dimension(600, 400));
    dialog.setResizable(false);
    JPanel container = new JPanel();
    container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

    container.setBorder(new EmptyBorder(5, 5, 5, 5));

    String text =
      "<html>" +
      "<p style=\"text-align: center;\"><span style=\"text-decoration: underline;\"><strong>" +
      algoProfile.name +
      "</strong></span></p>" +
      "<p style=\"text-align: left;\"><strong>Algritham author:</strong> <em>" +
      algoProfile.author +
      "</em></p>" +
      "<p style=\"text-align: left;\"><strong>Time Complexity:</strong>&nbsp;Worst Case: <em>" +
      algoProfile.timeComplexity.worstCase +
      "</em>&nbsp; &nbsp; &nbsp;Best Case: <em>" +
      algoProfile.timeComplexity.bestCase +
      "</em>&nbsp; &nbsp; &nbsp;Average Case: <em>" +
      algoProfile.timeComplexity.averageCase +
      "</em></p>" +
      "<p style=\"text-align: left;\"><em><strong>Space Complexity:&nbsp;</strong>" +
      algoProfile.spaceComplexity +
      "</em></p>" +
      "<p style=\"text-align: left;\">&nbsp;</p>" +
      "<p style=\"text-align: left;\">" +
      algoProfile.about +
      "</p>" +
      "</html>";
    JLabel jText = new JLabel(text);

    container.add(jText);
    dialog.add(container);
  }

  private void licenceDialog() {
    licenceDialog.setTitle("Copyright (c) 2023 RAHUL SHARMA");
    licenceDialog.setSize(new Dimension(600, 450));
    licenceDialog.setResizable(false);
    JPanel container = new JPanel();

    container.setBorder(new EmptyBorder(5, 5, 5, 5));

    String text =
      "<html>" +
      "<p><span style=\"font-size:14px\"><u><strong>MIT License </strong></u></span><a href=\"https://github.com/rahulsharmadev-community/SortingVisualizer\">GitHub</a></p>" +
      "<p><u><strong>Copyright (c) 2023 RAHUL SHARMA</strong></u></p>" +
      "<p>&nbsp;</p>" +
      "<p><u>Permission is hereby granted, free of charge, to any person obtaining a copy<br />" +
      "of this software and associated documentation files (the &quot;Software&quot;), to deal<br />" +
      "in the Software without restriction, including without limitation the rights<br />" +
      "to use, copy, modify, merge, publish, distribute, sublicense, and/or sell<br />" +
      "copies of the Software, and to permit persons to whom the Software is<br />" +
      "furnished to do so, subject to the following conditions:</u></p>" +
      "<p><u>The above copyright notice and this permission notice shall be included in all<br />" +
      "copies or substantial portions of the Software.</u></p>" +
      "<p>&nbsp;</p>" +
      "<p><u>THE SOFTWARE IS PROVIDED &quot;AS IS&quot;, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR<br />" +
      "IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,<br />" +
      "FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE<br />" +
      "AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER<br />" +
      "LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,<br />" +
      "OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE<br />" +
      "SOFTWARE.</u></p>" +
      "</html>";
    JLabel license = new JLabel(text);
    license.addMouseListener(
      new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          openUrl(
            "https://github.com/rahulsharmadev-community/Sorting-Visualizer/blob/master/LICENSE"
          );
        }
      }
    );

    JPanel infoPanel = new JPanel();
    JLabel info = new JLabel(
      "<html><p>Developer: Rahul Sharma</p><p>GitHub: rahulsharmadev</p></html>"
    );
    info.addMouseListener(
      new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          openUrl("https://github.com/rahulsharmadev-community");
        }
      }
    );
    Button button = new Button("Source Code");

    infoPanel.add(info);
    infoPanel.add(SizedBox.height(8));
    infoPanel.add(button);

    button.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          openUrl(
            "https://github.com/rahulsharmadev-community/Sorting-Visualizer"
          );
        }
      }
    );
    button.setMaximumSize(new Dimension(100, 42));

    container.add(license);
    container.add(infoPanel);
    container.add(button);
    licenceDialog.add(container);
    licenceDialog.setVisible(true);
  }

  void openUrl(String url) {
    try {
      Desktop.getDesktop().browse(new URI(url));
    } catch (IOException e1) {
      e1.printStackTrace();
    } catch (URISyntaxException e1) {
      e1.printStackTrace();
    }
  }
}
