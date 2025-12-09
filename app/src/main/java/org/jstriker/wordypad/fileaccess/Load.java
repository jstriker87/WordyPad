package org.jstriker.wordypad.fileaccess;
import java.io.*;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jstriker.wordypad.ui.MainUI;

/**
 * Class that allows the user to save the current working project
 */
public class Load {
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
  public Load(MainUI frame) {
    fileChooser = new JFileChooser();
    fileChooser.setFileFilter(filter);
  }

  public String initiateLoad() {
    StringBuilder sb = new StringBuilder();
    int option = fileChooser.showOpenDialog(frame);
    if (option == JFileChooser.APPROVE_OPTION) {
      File file = new File(fileChooser.getSelectedFile().getPath());
      try (Scanner reader = new Scanner(file)) {
        while (reader.hasNextLine()) {
          sb.append(reader.nextLine()).append("\n");
        }
        return sb.toString();
      } catch (Exception e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
        return "";
      }
    }
    return "";
  }
}
