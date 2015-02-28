package model;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Mick on 11/02/2015.
 */
public class Bord extends JFrame{

    Slang slang = new Slang();
    Tile[][] tile = new Tile[7][7];

    private JPanel menuPanel;
    private JPanel gridPanel;

    private JLabel levelTitel;
    private JLabel levelNummer;

    private JLabel scoreTitel;
    private JLabel scoreNummer;

    private JLabel targetTitel;
    private JLabel targetNummer;

    private JLabel timeTitel;
    private JLabel timeNummer;

    private JLabel pauzeKnop;

    public Bord() {
        super("Dots");
        //setLocationRelativeTo(this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        maakAttributen();
        maakListeners();
        maakLayout();
        pack();

        setSize(650, 750);

        setVisible(true);

        getContentPane();
        repaint();

        //TODO automatic size

    }

    public void maakAttributen() {


        levelTitel = new JLabel("level");
        levelNummer = new JLabel("1");

        scoreTitel = new JLabel("score");
        scoreNummer = new JLabel("2");

        targetTitel = new JLabel("target");
        targetNummer = new JLabel("3");

        timeTitel = new JLabel("time");
        timeNummer = new JLabel("4");

        //TODO image in knop zetten
        pauzeKnop = new JLabel("PAUZE");

    }

    public void maakListeners() {

    }

    public void maakLayout() {
        JPanel menuPanel = new JPanel(new GridLayout(0, 5, 5, 5));
        JPanel gridPanel = new JPanel(new GridLayout(0, 7, 5, 5));

        add(menuPanel, BorderLayout.PAGE_START);
        add(gridPanel, BorderLayout.CENTER);

        //menu attributen aan menu toevoegen
        menuPanel.add(levelTitel);
        menuPanel.add(scoreTitel);
        menuPanel.add(targetTitel);
        menuPanel.add(timeTitel);
        menuPanel.add(pauzeKnop);

        menuPanel.add(levelNummer);
        menuPanel.add(scoreNummer);
        menuPanel.add(targetNummer);
        menuPanel.add(timeNummer);

        //grid met dots toevoegen

        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 7; y++) {
                RandomKleur kleur = new RandomKleur();
                tile[x][y] = new Tile(kleur.getKleur());
                gridPanel.add(new myDots(tile[x][y]));
            }
        }
    }

    private class myDots extends JPanel {

        private Tile tile;

        public myDots(Tile tile) {
            this.tile = tile;
        }

        @Override
        protected void paintComponent(Graphics g) {
            int h = getHeight();
            int w = getWidth();
            g.setColor(tile.getKleur());
            g.fillOval(h / 2, w / 2, 33, 33);
        }
    }
}
