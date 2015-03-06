package model;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Sezer on 6/03/2015.
 */
public class Splash extends JFrame {

    public Splash() throws HeadlessException{
        JWindow window = new JWindow();
        window.getContentPane().add(
                new JLabel("", new ImageIcon("image/Splash.png"), SwingConstants.CENTER));
        window.setSize(900, 600);
        window.setVisible(true);
        window.setLocationRelativeTo(this);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        window.setVisible(false);
        Player player = new Player();
        window.dispose();
    }
}
