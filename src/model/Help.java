package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Sezer on 4/03/2015.
 */
public class Help extends JFrame {

    private JLabel infoTekst;
    private JLabel titelLabel;
    private JLabel terug;

    private Color dankRed = new Color(178, 8, 8);

    public Help() throws HeadlessException {
        super("Dots | Game manual");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        maakAttributen();
        maakListeners();
        maakLayout();
        pack();

        setSize(900, 600);
        setLocationRelativeTo(this);
        setResizable(false);
        setVisible(true);

        setBackground(dankRed);
    }

    private void maakAttributen() {
        Font font = new Font("Impact", Font.PLAIN, 30);

        //titel help venster
        titelLabel = new JLabel("<html><u>Game manual</u></html>");
        titelLabel.setFont(font);
        titelLabel.setForeground(Color.WHITE);
        titelLabel.setBackground(dankRed);
        titelLabel.setHorizontalAlignment(JLabel.CENTER);
        titelLabel.setOpaque(true);

        //uitleg en afbeelding help venster
        ImageIcon helpImage = new ImageIcon("image/helpImage.png");
        infoTekst = new JLabel("<html>" +
                                "Dots is a game where you have to connect the dots with the same color. <br>" +
                                "After you've connected the dots, new ones will appear. <br>" +
                                "Make sure you keep an eye on the time, your score and the target score.<br>" +
                                "You can pause the game by pressing the pause button.<br>" +
                                "After you've completed a game, your name will appear in the highscores if you have enough points." +
                                "<br>", helpImage, JLabel.CENTER);
        infoTekst.setFont(font);
        infoTekst.setForeground(Color.WHITE);
        infoTekst.setBackground(dankRed);
        infoTekst.setHorizontalAlignment(JLabel.CENTER);
        infoTekst.setOpaque(true);

        //terug knop help venster
        terug = new JLabel("Back");
        terug.setFont(font);
        terug.setForeground(Color.WHITE);
        terug.setBackground(dankRed);
        terug.setHorizontalAlignment(JLabel.CENTER);
        terug.setOpaque(true);
    }

    private void maakListeners() {
        //sluit Help venster en opent menu venster
        terug.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Menu menu = new Menu();
                dispose();
            }
        });

    }

    //maak layout
    private void maakLayout() {
        add(titelLabel, BorderLayout.PAGE_START);
        add(infoTekst, BorderLayout.CENTER);
        add(terug, BorderLayout.PAGE_END);

    }
}
