package model;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Mick on 11/02/2015.
 */
public class Bord extends JFrame{

    Slang slang = new Slang();
    Bol[] dots = new Bol[49];

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
        setLocationRelativeTo(this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        maakAttributen();
        maakListeners();
        maakLayout();
        pack();



        setVisible(true);

        //TODO automatische size


    }

    public void maakAttributen() {


        JLabel levelTitel = new JLabel("level");
        JLabel levelNummer = new JLabel("");

        JLabel scoreTitel = new JLabel("score");
        JLabel scoreNummer = new JLabel("");

        JLabel targetTitel = new JLabel("target");
        JLabel targetNummer = new JLabel("");

        JLabel timeTitel = new JLabel("time");
        JLabel timeNummer = new JLabel("");

        //TODO image in knop zetten
        JLabel pauzeKnop = new JLabel();

    }

    public void maakListeners() {

    }

    public void maakLayout() {
        JPanel menuPanel = new JPanel(new GridLayout(0, 5, 5, 5));
        JPanel gridPanel = new JPanel();

        add(menuPanel, BorderLayout.PAGE_START);
        add(gridPanel, BorderLayout.CENTER);

        menuPanel.add(levelTitel);
        menuPanel.add(scoreTitel);
        menuPanel.add(targetTitel);
        menuPanel.add(timeTitel);
        menuPanel.add(pauzeKnop);

        menuPanel.add(levelNummer);
        menuPanel.add(scoreNummer);
        menuPanel.add(targetNummer);
        menuPanel.add(timeNummer);


    }

}
