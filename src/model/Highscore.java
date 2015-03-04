package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Sezer on 5/02/2015.
 */
public class Highscore extends JFrame {

    private int[] highscores;

    private JPanel titelPaneel;
    private JPanel textPaneel;
    private JPanel terugPaneel;

    private JLabel titelLabel;
    private JLabel highscoreLijst;
    private JLabel terug;

    private Color dankRed = new Color(178, 8, 8);

    public Highscore() throws HeadlessException {
        super("Dots | Highscores");
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

        titelPaneel = new JPanel(new FlowLayout());
        textPaneel = new JPanel();
        terugPaneel = new JPanel(new FlowLayout());

        titelPaneel.setBackground(dankRed);
        textPaneel.setBackground(dankRed);
        terugPaneel.setBackground(dankRed);

        titelLabel = new JLabel("Highscores");
        titelLabel.setFont(font);
        titelLabel.setForeground(Color.WHITE);

        highscoreLijst = new JLabel("<html><center>Sezer   25<br>Mick     24<br>Yannick  22<br>Sezer   22<br>Ismail  19<br>Ilias   14<br>Mick    9</center></html>");
        highscoreLijst.setFont(font);
        highscoreLijst.setForeground(Color.WHITE);

        terug = new JLabel("Terug");
        terug.setFont(font);
        terug.setForeground(Color.WHITE);

    }

    private void maakListeners() {
        terug.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Menu menu = new Menu();
                dispose();
            }
        });

    }

    private void maakLayout() {
        add(titelPaneel, BorderLayout.PAGE_START);
        add(textPaneel, BorderLayout.CENTER);
        add(terugPaneel, BorderLayout.PAGE_END);

        titelPaneel.add(titelLabel);
        textPaneel.add(highscoreLijst);
        terugPaneel.add(terug);
    }
}
