package org.jstriker.wordypad.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.*;
import org.jstriker.wordypad.fileaccess.Load;
import org.jstriker.wordypad.fileaccess.Save;

/**
 * This class builds the main application window that the program will run in.
 */
public class MainUI extends JFrame implements ActionListener {
  private static MainUI frame;
  private int previewAreaWidth =
      (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
  private int previewAreaHeight =
      (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
  private int frameWidth;
  private int frameHeight;
  private JMenuBar menu_bar;
  static JMenu file_menu, edit_menu, view_menu;
  static JMenuItem new_window, open, save, save_as, page_setup, print, exit,
      undo, cut, copy, paste, delete, find, replace, select_all, font,
      status_bar;

  private JTextArea text_area = new JTextArea();
  private Load load_f;
  private Save save_f;
  //@SuppressWarnings("unused")

  /**
   * Default constructor for MainUI class, initliases swing components, such as
   * the toolbar, menu bar and editor window and adds them to the frame.
   */
  @SuppressWarnings("unused")

  @Override
  public void actionPerformed(ActionEvent e) {
    text_area.add(new JScrollPane());
    String name = e.getActionCommand();
    System.out.println("Menu clicked: " + name);
    switch (name) {
    case "Exit":
      try {
        System.exit(0);
      } catch (Exception err) {
        System.err.println(err);
      }
      break;

    case "Open":
      try {
        load_f = new Load(frame);
        String str = load_f.initiateLoad();
        SwingUtilities.invokeLater(() -> text_area.setText(str));

      } catch (Exception err) {
        System.err.println(err);
      }
      break;

    case "Save":
      try {
        save_f = new Save(frame);
        save_f.initiateSave(text_area.getText());
        //SwingUtilities.invokeLater(() -> text_area.setText(str));

      } catch (Exception err) {
        System.err.println(err);
      }
      break;
    default:
      break;
    }
  }

  private void createMenu(String[] menuData, JMenuBar menu) {
    JMenu mBar = new JMenu(menuData[0].toString());
    for (int i = 1; i < menuData.length; i++) {
      JMenuItem item = new JMenuItem(menuData[i]);
      item.addActionListener(this);
      mBar.add(item);
    }

    menu_bar.add(mBar);
  }
  public MainUI() throws Exception {
    frame = this;
    this.setTitle("Wordy");
    this.getContentPane().setBackground(Color.WHITE);
    this.setLayout(new BorderLayout());
    frameWidth =
        (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 50;
    frameHeight =
        (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 50;

    menu_bar = new JMenuBar();

    String[] fileMenuData = {"File",    "New Window", "Open",  "Save",
                             "Save As", "Page Setup", "Print", "Exit"};
    String[] editMenuData = {"Edit",   "Undo", "Cut",     "Copy",       "Paste",
                             "Delete", "Find", "Replace", "Select All", "Font"};
    String[] viewMenuData = {"View", "Status Bar"};

    createMenu(fileMenuData, menu_bar);
    createMenu(editMenuData, menu_bar);
    createMenu(viewMenuData, menu_bar);

    menu_bar.setBackground(Color.LIGHT_GRAY);
    JScrollPane scrollPane = new JScrollPane(text_area,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    this.add(scrollPane, BorderLayout.CENTER);
    this.setJMenuBar(menu_bar);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(500, 500);
    this.setLocationRelativeTo(null);
    this.setVisible(true);

    // this.setLayout(new BorderLayout());
  }
}
