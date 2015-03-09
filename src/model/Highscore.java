package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Sezer on 5/02/2015.
 */
public class Highscore extends JFrame {

    private String name = Menu.getNaam();
    private int score = Board.getPuntenGehaaldTotaal();

    private JPanel titelPaneel;
    private JPanel textPaneel;
    private JPanel terugPaneel;

    private JLabel titelLabel;
    private JLabel highscoreLijst;
    private JLabel terug;

    private Color dankRed = new Color(178, 8, 8);

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

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

        titelLabel = new JLabel("<html><u>Highscores</u></html>");
        titelLabel.setFont(font);
        titelLabel.setForeground(Color.WHITE);

        //formatting werkt door html
        highscoreLijst = new JLabel("<html><center>Sezer   25<br>Mick     24<br>Yannick  22<br>Sezer   22<br>Ismail  19<br>Ilias   14<br>Mick    9</center></html>");
        highscoreLijst.setFont(font);
        highscoreLijst.setForeground(Color.WHITE);

        terug = new JLabel("Terug");
        terug.setFont(font);
        terug.setForeground(Color.WHITE);

    }

    private void maakListeners() {
        //sluit highscore venster en opent het menu venster terug
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

    public void writeFile() {
        try {
            FileWriter write = new FileWriter("highscores.txt", true);
            PrintWriter text = new PrintWriter(write);
            text.println( name + " " + score);
            text.flush();
            write.close();

        } catch (IOException ioe)
        // writes name from textfield(username) and score from board class
        // into text document text.txt
        {
            ioe.printStackTrace();
        }
    }

}
