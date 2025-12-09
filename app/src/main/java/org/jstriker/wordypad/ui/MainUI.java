package org.jstriker.wordypad.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.io.*;
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
  public File save_path = null;
  //@SuppressWarnings("unused")

  /**
   * Default constructor for MainUI class, initliases swing components, such as
   * the toolbar, menu bar and editor window and adds them to the frame.
   */
  @SuppressWarnings("unused")

  public File getSavePath() {
    return save_path;
  }

  public KeyListener addKeyListener() {

    KeyListener listener = new KeyListener() {
      @Override
      public void keyPressed(KeyEvent event) {
        printEventInfo("Key Pressed", event);
        int code = event.getKeyCode();
        if (event.isControlDown()) {
          switch (code) {
          case KeyEvent.VK_S:
            System.out.println("Save");
            save_f = new Save(frame);
            File filePath = save_f.initiateSave(text_area.getText(), getSavePath());
            setSavePath(filePath);

            break;
          case KeyEvent.VK_O:
            System.out.println("Open");
            break;
          }
        }
      }
      @Override
      public void keyReleased(KeyEvent event) {
        printEventInfo("Key Released", event);
      }
      @Override
      public void keyTyped(KeyEvent event) {
        printEventInfo("Key Typed", event);
      }
      private void printEventInfo(String str, KeyEvent e) {
        // System.out.println(str);
        // int code = e.getKeyCode();
        // System.out.println("   Code: " + KeyEvent.getKeyText(code));
        // System.out.println("   Char: " + e.getKeyChar());
        // int mods = e.getModifiersEx();
        // System.out.println("    Mods: " + KeyEvent.getModifiersExText(mods));
        // System.out.println("    Action? " + e.isActionKey());
        // if (KeyEvent.getKeyText(code) == "Ctrl") {

        //   System.out.println("YES");
        // }
      }
    };

    return listener;
  }

  public void setSavePath(File new_path) { this.save_path = new_path; }

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
        String[] ret = load_f.initiateLoad();
        if (ret[0] != null && ret[1] != null) {
          File filePath = new File(ret[1]);
          setSavePath(filePath);
          SwingUtilities.invokeLater(() -> text_area.setText(ret[0]));
        }

      } catch (Exception err) {
        System.err.println(err);
      }
      break;

    case "Save":
      try {
        save_f = new Save(frame);
        File filePath = save_f.initiateSave(text_area.getText(), getSavePath());
        setSavePath(filePath);

      } catch (Exception err) {
        System.err.println(err);
      }
      break;

    case "Save As":
      try {
        setSavePath(null);
        save_f = new Save(frame);
        File filePath = save_f.initiateSave(text_area.getText(), getSavePath());
        setSavePath(filePath);

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
    JScrollPane scrollPane =
        new JScrollPane(text_area, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    KeyListener listy = addKeyListener();
    text_area.addKeyListener(listy);
    this.add(scrollPane, BorderLayout.CENTER);
    this.setJMenuBar(menu_bar);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(500, 500);
    this.setLocationRelativeTo(null);
    this.setVisible(true);
    this.setFocusable(true);
    this.requestFocusInWindow();

    // this.setLayout(new BorderLayout());
  }
}
