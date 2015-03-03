package model;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Sezer on 3/03/2015.
 */
public class Menu extends JFrame{

    private JPanel knopPanel;
    private JPanel headerPanel;

    private JButton play;
    private JButton highscores;
    private JButton help;
    private JButton exit;

    private JLabel dotsTitel;

    public Menu() throws HeadlessException {
        super("Dots");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        maakAttributen();
        maakListeners();
        maakLayout();
        pack();

        setSize(900, 600);
        setLocationRelativeTo(this);
        setResizable(false);
        setVisible(true);
    }

    private void maakAttributen() {
        Font font = new Font("Impact", Font.PLAIN, 30);

        play = new JButton("PLAY");
        highscores = new JButton("HIGHSCORES");
        help = new JButton("HELP");
        exit = new JButton("EXIT");

        dotsTitel = new JLabel("Dots the Game");
        dotsTitel.setFont(font);
        dotsTitel.setForeground(Color.BLUE);
    }

    private void maakListeners() {

    }

    private void maakLayout() {
        knopPanel = new JPanel(new GridLayout(0, 2, 10, 10)) ;
        headerPanel = new JPanel(new FlowLayout());

        add(knopPanel, BorderLayout.CENTER);
        add(headerPanel, BorderLayout.PAGE_START);

        headerPanel.add(dotsTitel);

        knopPanel.add(play);
        knopPanel.add(highscores);
        knopPanel.add(help);
        knopPanel.add(exit);



    }




}
