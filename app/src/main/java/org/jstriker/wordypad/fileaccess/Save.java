package org.jstriker.wordypad.fileaccess;
import org.jstriker.wordypad.ui.MainUI;
import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Class that allows the user to save the current working project 
 */
public class Save {
    private MainUI frame;
    private JFileChooser fileChooser;
    private FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files(*.txt)", "txt");

    /**
     * Constructor generates a window that allows the user to type the file name of the 
     * presentation before saving.
     * 
     * @param UIFrame The instance of the presentation to save
     */
    public Save(MainUI frame){
        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(filter);
        
    }

    public void initiateSave(){
            fileChooser.showSaveDialog(frame);
        }
        
    }

