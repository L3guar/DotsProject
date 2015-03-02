package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

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

    private JLabel pauzeIcoon;

    public Bord() {
        super("Dots");
        //setLocationRelativeTo(this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        maakAttributen();
        maakListeners();
        maakLayout();
        pack();

        setSize(650, 775);
        setResizable(false);
        setVisible(true);

        getContentPane();
        repaint();

        //TODO automatic size

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

        scoreTitel = new JLabel("score");
        scoreTitel.setFont(font);
        scoreTitel.setForeground(Color.GREEN);
        scoreNummer = new JLabel("2");
        scoreNummer.setFont(font);
        scoreNummer.setForeground(Color.GREEN);

        targetTitel = new JLabel("target");
        targetTitel.setFont(font);
        targetTitel.setForeground(Color.BLUE);
        targetNummer = new JLabel("3");
        targetNummer.setFont(font);
        targetNummer.setForeground(Color.BLUE);

        timeTitel = new JLabel("time");
        timeTitel.setFont(font);
        timeTitel.setForeground(Color.YELLOW);
        timeNummer = new JLabel("4");
        timeNummer.setFont(font);
        timeNummer.setForeground(Color.YELLOW);

        //pauze afbeelding en resizen
        ImageIcon icon = new ImageIcon("image/PauseButton.png");
        Image img = icon.getImage();
        BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.createGraphics();
        g.drawImage(img, 0, 0, 82, 81, null);
        pauzeIcoon = new JLabel(new ImageIcon(bi));
        pauzeIcoon.setVerticalAlignment(JLabel.CENTER);
    }



    //listeners maken
    public void maakListeners() {

        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                gridPanel.add(new MyLine(e.getX(), e.getY()));
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                gridPanel.add(new MyLine(e.getX(), e.getY()));
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                gridPanel.add(new MyLine(e.getX(), e.getY()));
                repaint();
            }
        });
    }



    //layout maken
    public void maakLayout() {

        menuPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gridPanel = new JPanel(new GridLayout(0, 7, 5, 5));


        add(menuPanel, BorderLayout.PAGE_START);
        add(gridPanel, BorderLayout.CENTER);

        //menu attributen aan menu toevoegen en ordenen volgens het GridBagLayout principe
        gbc.insets = new Insets(20, 20, 20, 20);

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
                RandomKleur kleur = new RandomKleur();
                tile[x][y] = new Tile(kleur.getKleur());
                gridPanel.add(new MyDots(tile[x][y]));
            }
        }
    }

    //dots laten zien en kleuren
    private class MyDots extends JPanel {

        private Tile tile;

        public MyDots(Tile tile) {
            this.tile = tile;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int h = getHeight();
            int w = getWidth();
            g.setColor(tile.getKleur());
            g.fillOval(h / 2, w / 2, 33, 33);
        }
    }

    private class MyLine extends JPanel{

        int x;
        int y;

        public MyLine(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int h = getHeight();
            int w = getWidth();
            g.drawLine(h, w, x, y);

        }
    }
}
