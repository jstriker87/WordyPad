package org.jstriker.wordypad.fileaccess;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jstriker.wordypad.ui.MainUI;

/**
 * Class that allows the user to save the current working project
 */
public class Save {
  private MainUI frame;
  private JFileChooser fileChooser;
  private FileNameExtensionFilter filter =
      new FileNameExtensionFilter("Text files(*.txt)", "txt");

  /**
   * Constructor generates a window that allows the user to type the file name
   * of the presentation before saving.
   *
   * @param UIFrame The instance of the presentation to save
   */

  public Save(MainUI frame) {
    fileChooser = new JFileChooser();
    fileChooser.setFileFilter(filter);
  }

  public void initiateSave(String text) {
    StringBuilder sb = new StringBuilder();
    int option = fileChooser.showSaveDialog(frame);
    if (option == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      if (!file.getName().contains(".")) {
        file = new File(file.getAbsolutePath() + ".txt");
      }
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
        writer.write(text);
        System.out.println("File saved successfully!");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
