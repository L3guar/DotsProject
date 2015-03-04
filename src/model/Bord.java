package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

/**
 * Created by Mick on 11/02/2015.
 */
public class Bord extends JFrame{

    private List<Bol> bollenlijst;

    private int eersteIndicator = 0;

    private JPanel menuPanel;
    private JPanel gridPanel;

    private JLabel leftLabel;
    private JLabel rightLabel;
    private JLabel underLabel;

    private JLabel levelTitel;
    private JLabel levelNummer;

    private JLabel scoreTitel;
    private JLabel scoreNummer;

    private JLabel targetTitel;
    private JLabel targetNummer;

    private JLabel timeTitel;
    private JLabel timeNummer;

    private JLabel pauzeIcoon;

    public Bord() {
        super("Dots");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        maakAttributen();
        maakListeners();
        maakLayout();
        pack();

        setSize(575, 725);
        setLocationRelativeTo(this);
        setResizable(false);
        setVisible(true);

    }

    //attributen maken en titels opmaken
    public void maakAttributen() {
        Font font = new Font("Impact", Font.PLAIN, 30);

        levelTitel = new JLabel("level");
        levelTitel.setFont(font);
        levelTitel.setForeground(Color.RED);
        levelNummer = new JLabel("1");
        levelNummer.setFont(font);
        levelNummer.setForeground(Color.RED);

        Color dankgreen = new Color(3,173,26);
        scoreTitel = new JLabel("score");
        scoreTitel.setFont(font);
        scoreTitel.setForeground(dankgreen);
        scoreNummer = new JLabel("2");
        scoreNummer.setFont(font);
        scoreNummer.setForeground(dankgreen);

        targetTitel = new JLabel("target");
        targetTitel.setFont(font);
        targetTitel.setForeground(Color.BLUE);
        targetNummer = new JLabel("3");
        targetNummer.setFont(font);
        targetNummer.setForeground(Color.BLUE);

        timeTitel = new JLabel("time");
        timeTitel.setFont(font);
        timeTitel.setForeground(Color.ORANGE);
        timeNummer = new JLabel("4");
        timeNummer.setFont(font);
        timeNummer.setForeground(Color.ORANGE);

        //pauze afbeelding en resizen
        ImageIcon icon = new ImageIcon("image/PauseButton.png");
        Image img = icon.getImage();
        BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.createGraphics();
        g.drawImage(img, 0, 0, 82, 81, null);
        pauzeIcoon = new JLabel(new ImageIcon(bi));
        pauzeIcoon.setVerticalAlignment(JLabel.CENTER);

        bollenlijst = new ArrayList<>();

    }


    //listeners maken
    public void maakListeners() {

    }

    //layout maken
    public void maakLayout() {

        menuPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gridPanel = new JPanel(new GridLayout(7, 7, 1, 1));
        leftLabel = new JLabel("");
        leftLabel.setPreferredSize(new Dimension(0, 0));
        rightLabel = new JLabel("");
        rightLabel.setPreferredSize(new Dimension(25, 0));
        underLabel = new JLabel("");
        underLabel.setPreferredSize(new Dimension(0, 50));

        //add(linePanel, BorderLayout.PAGE_START);
        add(menuPanel, BorderLayout.PAGE_START);
        add(leftLabel, BorderLayout.WEST);
        add(gridPanel, BorderLayout.CENTER);
        add(rightLabel, BorderLayout.EAST);
        add(underLabel, BorderLayout.PAGE_END);

        //menu attributen aan menu toevoegen en ordenen volgens het GridBagLayout principe
        gbc.insets = new Insets(15, 15, 15, 15);

        gbc.gridx = 1;
        gbc.gridy = 0;
        menuPanel.add(levelTitel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        menuPanel.add(scoreTitel, gbc);
        gbc.gridx = 3;
        gbc.gridy = 0;
        menuPanel.add(targetTitel, gbc);
        gbc.gridx = 5;
        gbc.gridy = 0;
        menuPanel.add(timeTitel, gbc);

        gbc.gridx = 6;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.weighty = 3;
        menuPanel.add(pauzeIcoon, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        menuPanel.add(levelNummer, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        menuPanel.add(scoreNummer, gbc);
        gbc.gridx = 3;
        gbc.gridy = 1;
        menuPanel.add(targetNummer, gbc);
        gbc.gridx = 5;
        gbc.gridy = 1;
        menuPanel.add(timeNummer, gbc);


        //grid met dots toevoegen
        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 7; y++) {
                Bol bol = new Bol();
                final int bolX = bol.getX();
                int bolY = bol.getY();
                final int kleurIndicator = bol.getKleurIndicator();
                bol.setX(x);
                bol.setY(y);
                gridPanel.add(bol);
                bollenlijst.add(bol);
                bol.setOpaque(false);
                bol.setFocusPainted(false);
                bol.setBorderPainted(false);
                bol.setContentAreaFilled(false);
                bol.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
                bol.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (eersteIndicator == kleurIndicator) {
                            //if komt hier
                        } else {
                            eersteIndicator = kleurIndicator;
                        }
                    }
                });
            }
        }


    }

}
