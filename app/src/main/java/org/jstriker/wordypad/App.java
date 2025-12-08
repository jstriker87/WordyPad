package org.jstriker.wordypad;
import org.jstriker.wordypad.ui.MainUI;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        FlatLightLaf.setup();
        SwingUtilities.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                try {
                    //@SuppressWarnings("unused")                 
                    MainUI Frame = new MainUI();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
    }
}
