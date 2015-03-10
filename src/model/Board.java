package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mick on 11/02/2015.
 */
public class Board extends JFrame {
    private List<Dot> bollenlijst;
    private List<Line> lines = new ArrayList<>();
    private ArrayList connect = new ArrayList();

    private boolean isClicked = false;

    private int firstIndicator = 0;
    private int colorIndicator = 5;
    private int firstXclick = 1000;
    private int firstYclick = 1000;
    private int firstXcoord;
    private int firstYcoord;
    private int x1;
    private int x2;
    private int y1;
    private int y2;
    private int connectcounter = 0;

    private Color color;

    private JPanel menuPanel;
    private JPanel gridPanel;
    private JLayeredPane layeredPane;
    private JLabel leftLabel;
    private JLabel rightLabel;
    private JLabel underLabel;
    private JLabel levelTitle;
    private JLabel levelNumber;
    private JLabel scoreTitle;
    private JLabel scoreNumber;
    private JLabel targetTitle;
    private JLabel targetNumber;
    private JLabel timeTitle;
    private JLabel timeNumber;
    private JLabel pauseIcon;

    private MakeLine makeLine;

    //TIMER
    Timer timer;
    int counter = 60;

    //Controleren of de pausebutton al is gebruikt variabel
    boolean clicked = false;

    //Score, target & level berekenen variabel
    int score = 0;
    int target = 200;
    int level = 1;

    //highscores berekenen variabel
    static int puntenGehaaldTotaal = 0;

    //hoofdframe
    public Board() {
        super("Dots");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        makeAttributes();
        makeListeners();
        makeLayout();
        pack();
        setSize(575, 725);
        setLocationRelativeTo(this);
        setResizable(true);
        setVisible(true);
    }

    //attributen maken en titels opmaken
    public void makeAttributes() {
        Font font = new Font("Impact", Font.PLAIN, 30);
        Color dankgreen = new Color(3, 173, 26);

        //LEVEL
        levelTitle = new JLabel("level");
        levelTitle.setFont(font);
        levelTitle.setForeground(Color.RED);

        levelNumber = new JLabel("1");
        levelNumber.setFont(font);
        levelNumber.setForeground(Color.RED);

        //SCORE
        scoreTitle = new JLabel("score");
        scoreTitle.setFont(font);
        scoreTitle.setForeground(dankgreen);

        scoreNumber = new JLabel("0");
        scoreNumber.setFont(font);
        scoreNumber.setForeground(dankgreen);

        //TARGET
        targetTitle = new JLabel("target");
        targetTitle.setFont(font);
        targetTitle.setForeground(Color.BLUE);

        targetNumber = new JLabel("200");
        targetNumber.setFont(font);
        targetNumber.setForeground(Color.BLUE);

        //TIME
        timeTitle = new JLabel("time");
        timeTitle.setFont(font);
        timeTitle.setForeground(Color.ORANGE);

        timeNumber = new JLabel("60");
        timeNumber.setFont(font);
        timeNumber.setForeground(Color.ORANGE);
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeNumber.setText(String.valueOf(counter));
                counter--;
                if (counter < 0) {
                    timer.stop();
                    puntenGehaaldTotaal += score;
                    //als je de target score niet hebt behaald reset hij alles en kan je opnieuw beginnen.
                    if (score < target) {
                        JOptionPane.showMessageDialog(null, "You haven't reached the target. Let's see if you've done well!");
                        Highscore highscore = new Highscore();
                        highscore.writeFile();
                        highscore.dispose();
                        Highscore highscores = new Highscore();
                        dispose();

                        puntenGehaaldTotaal = 0;
                        level = 1;
                        score = 0;
                        target = 200;
                        scoreNumber.setText(String.valueOf(score));
                        levelNumber.setText(String.valueOf(level));
                        targetNumber.setText(String.valueOf(target));
                        counter = 60;
                        timer.start();
                    }
                }
            }
        });
        timer.start();


        //PAUSEBUTTON + RESIZE
        ImageIcon icon = new ImageIcon("image/PauseButton.png");
        Image img = icon.getImage();
        BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.createGraphics();
        g.drawImage(img, 0, 0, 82, 81, null);
        pauseIcon = new JLabel(new ImageIcon(bi));
        pauseIcon.setVerticalAlignment(JLabel.CENTER);

        bollenlijst = new ArrayList<>();
    }

    //listeners maken
    public void makeListeners() {
        //pauseknop drukken om te pauzeren en herstarten
        pauseIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (clicked == false) {
                    timer.stop();
                    clicked = true;
                    for (int i = 0; i < bollenlijst.size(); i++) {
                        bollenlijst.get(i).setEnabled(false);
                        pauseIcon.setIcon(new ImageIcon("image/PlayButton.png"));
                    }

                } else {
                    timer.start();
                    clicked = false;
                    for (int i = 0; i < bollenlijst.size(); i++) {
                        bollenlijst.get(i).setEnabled(true);
                        pauseIcon.setIcon(new ImageIcon("image/PauseButton.png"));
                    }
                }
            }
        });
    }

    //layout maken
    public void makeLayout() {
        Rectangle rectangle = new Rectangle(500, 500);
        menuPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        layeredPane = new JLayeredPane();
        layeredPane.setOpaque(false);
        layeredPane.setBounds(rectangle);

        gridPanel = new JPanel(new GridLayout(7, 7, 0, 0));
        gridPanel.setOpaque(false);
        gridPanel.setBackground(new Color(0, 0, 0, 0));
        gridPanel.setBounds(rectangle);

        layeredPane.add(gridPanel, 5);

        makeLine = new MakeLine();
        makeLine.setOpaque(false);
        makeLine.setBounds(rectangle);
        layeredPane.add(makeLine, 2);

        leftLabel = new JLabel("");
        leftLabel.setPreferredSize(new Dimension(25, 0));
        rightLabel = new JLabel("");
        rightLabel.setPreferredSize(new Dimension(25, 0));
        underLabel = new JLabel("");
        underLabel.setPreferredSize(new Dimension(0, 50));

        //add(linePanel, BorderLayout.PAGE_START);
        add(menuPanel, BorderLayout.PAGE_START);
        add(leftLabel, BorderLayout.WEST);
        add(layeredPane, BorderLayout.CENTER);
        add(rightLabel, BorderLayout.EAST);
        add(underLabel, BorderLayout.PAGE_END);

        //menu attributen aan menu toevoegen en ordenen volgens het GridBagLayout principe
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        menuPanel.add(levelTitle, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        menuPanel.add(scoreTitle, gbc);
        gbc.gridx = 3;
        gbc.gridy = 0;
        menuPanel.add(targetTitle, gbc);
        gbc.gridx = 5;
        gbc.gridy = 0;
        menuPanel.add(timeTitle, gbc);
        gbc.gridx = 6;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.weighty = 3;
        menuPanel.add(pauseIcon, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        menuPanel.add(levelNumber, gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        menuPanel.add(scoreNumber, gbc);
        gbc.gridx = 3;
        gbc.gridy = 1;
        menuPanel.add(targetNumber, gbc);
        gbc.gridx = 5;
        gbc.gridy = 1;
        menuPanel.add(timeNumber, gbc);

        //grid met dots toevoegen
        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 7; y++) {
                // nieuwe dot toevoegen
                final Dot dot = new Dot();
                // coordinaten in de grid geven aan dot.
                dot.setX(y);
                dot.setY(x);
                // coordinaten terugvragen
                final int dotX = dot.gogetX();
                final int dotY = dot.gogetY();
                gridPanel.add(dot);
                // een arraylist met alle bollen maken.
                bollenlijst.add(dot);
                dot.setOpaque(true);
                dot.setFocusPainted(false);
                dot.setBorderPainted(false);
                dot.setContentAreaFilled(false);
                dot.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
                dot.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        colorIndicator = dot.getColorIndicator();
                        switch (colorIndicator){
                            case 1: dot.setIcon(new ImageIcon("image/geelPressed.png")); break;
                            case 2: dot.setIcon(new ImageIcon("image/blauwPressed.png")); break;
                            case 3: dot.setIcon(new ImageIcon("image/roodPressed.png")); break;
                            case 4: dot.setIcon(new ImageIcon("image/groenPressed.png")); break;
                            default: break;
                        }

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        colorIndicator = dot.getColorIndicator();
                        switch (colorIndicator){
                            case 1: dot.setIcon(new ImageIcon("image/geel.png")); break;
                            case 2: dot.setIcon(new ImageIcon("image/blauw.png")); break;
                            case 3: dot.setIcon(new ImageIcon("image/rood.png")); break;
                            case 4: dot.setIcon(new ImageIcon("image/dankgreen.png")); break;
                            default: break;
                        }
                    }

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        colorIndicator = dot.getColorIndicator();
                        // kijk of de volgende bol dezelfde kleur heeft
                        if (firstIndicator == colorIndicator) {
                            isClicked = dot.isClicked();
                            if (!isClicked || firstXclick == dotX && firstYclick == dotY) {
                                // kijk of de bol in dezelfde omstreek ligt.
                                if (firstXclick == dotX - 1 && firstYclick == dotY - 1 || firstXclick == dotX && firstYclick == dotY - 1
                                        || firstXclick == dotX + 1 && firstYclick == dotY - 1 || firstXclick == dotX + 1 && firstYclick == dotY
                                        || firstXclick == dotX - 1 && firstYclick == dotY || firstXclick == dotX - 1 && firstYclick == dotY + 1
                                        || firstXclick == dotX && firstYclick == dotY + 1 || firstXclick == dotX + 1 && firstYclick == dotY + 1) {

                                    // verander de tweede bol in de eerste bol
                                    firstXclick = dotX;
                                    firstYclick = dotY;
                                    // maak een array met lijnen tussen laatste twee geklikte dots
                                    Slang connectLine = new Slang(firstXcoord, firstYcoord, dot.getX(), dot.getY());
                                    x1 = connectLine.getX1();
                                    x2 = connectLine.getX2();
                                    y1 = connectLine.getY1();
                                    y2 = connectLine.getY2();
                                    // voeg de lijn toe aan array
                                    connect.add(firstXclick);
                                    connect.add(firstYclick);
                                    connectcounter += 1;
                                    dot.setClicked(true);

                                    makeLine.addLine(new Line(x1, x2, y1, y2, color));
                                    makeLine.repaint();
                                    gridPanel.repaint();
                                    firstXcoord = x2;
                                    firstYcoord = y2;
                                } else if (firstXclick == dotX && firstYclick == dotY) {
                                    // verwijder de lijnen door op hetzelfde dot te klikken
                                    lines.clear();
                                    makeLine.repaint();
                                    // als het aantal connected dots groter of gelijk is aan 2
                                    if (connectcounter > 1) {
                                        // verdwijder de dots die connected waren
                                        for (int i = 0; i < connect.size(); i += 2) {

                                            int connectY = (int) connect.get(i + 1);
                                            int connectX = (int) connect.get(i);

                                            bollenlijst.get(7 * connectY + connectX).setKleurGetal();
                                            colorIndicator = bollenlijst.get(7 * connectY + connectX).getColorIndicator();
                                            bollenlijst.get(7 * connectY + connectX).setClicked(false);
                                        }
                                        // zet connectcounter terug op 0 zodat we nieuwe serie kunnen beginnen
                                        connectcounter = 0;
                                        // zet de kleur terug op niet bestaand zodat er geen problemen veroorzaakt worden
                                        firstIndicator = 5;
                                        // lengte connect opslagen voordat je het leeg maakt
                                        score += ((connect.size() / 2) * 10 + (connect.size()/2)*0.2*10);
                                        scoreNumber.setText(String.valueOf(score));

                                        //target en levels berekenen
                                        if (score >= target) {
                                            puntenGehaaldTotaal += score;
                                            score = 0;
                                            scoreNumber.setText(String.valueOf(score));
                                            System.out.println("puntenGehaaldTotaal = " + puntenGehaaldTotaal);
                                            level += 1;
                                            levelNumber.setText(String.valueOf(level));
                                            target += (level - 1) * 30;
                                            targetNumber.setText(String.valueOf(target));
                                            //timer stoppen en opnieuw laten tellen
                                            timer.stop();
                                            counter = 60;
                                            timer = new Timer(1000, new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    timeNumber.setText(String.valueOf(counter));
                                                    counter--;
                                                    //als je de target score niet hebt behaald reset hij alles en kan je opnieuw beginnen. -> dit is voor levels hoger als 1
                                                    if (counter < 0) {
                                                        timer.stop();
                                                        if (score < target) {
                                                            JOptionPane.showMessageDialog(null, "You haven't reached the target. Let's see if you've done well!");
                                                            Highscore highscore = new Highscore();
                                                            highscore.writeFile();
                                                            highscore.dispose();
                                                            Highscore highscores = new Highscore();
                                                            dispose();


                                                            puntenGehaaldTotaal = 0;
                                                            level = 1;
                                                            score = 0;
                                                            target = 200;
                                                            scoreNumber.setText(String.valueOf(score));
                                                            levelNumber.setText(String.valueOf(level));
                                                            targetNumber.setText(String.valueOf(target));
                                                            counter = 60;
                                                            timer.start();
                                                        }
                                                    }
                                                }
                                            });
                                            timer.start();

                                        }

                                        // maak de coordinaten leeg om een  nieuwe serie te beginnen
                                        connect.clear();


                                    }

                                } else {
                                    System.out.println("te ver!");
                                }
                            } else{
                                System.out.println("Al geklikt!");
                            }
                        } else {

                            dot.setClicked(true);
                            for (int i = 0; i < connect.size(); i += 2) {
                                bollenlijst.get(7 * (int) connect.get(i + 1) + (int) connect.get(i)).setClicked(false);
                            }
                            // kleur veranderen => nieuwe serie
                            connectcounter = 0;
                            // huidige kleur aanpassen
                            firstIndicator = colorIndicator;
                            // coordinaten overnemen van nieuwe click
                            firstXclick = dotX;
                            firstYclick = dotY;
                            // effectieve coordinaten opslagen
                            firstXcoord = dot.getX();
                            firstYcoord = dot.getY();
                            // voeg de eerste click toe aan een nieuwe array.
                            connect.clear();
                            connect.add(firstXclick);
                            connect.add(firstYclick);
                            // we hebben een connectie dus +1
                            connectcounter += 1;
                            // set kleur voor lijn te kleuren
                            color = dot.getColor();
                            // verwijder lijnen
                            lines.clear();
                            makeLine.repaint();

                        }
                    }

                });
            }
        }
    }


    private class MakeLine extends JPanel {


        public void addLine(Line line) {
            lines.add(line);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            //Dikte van lijnen instellen
            g2d.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));

            //Teken lijn(en)
            for (Line line : lines) {
                g2d.setColor(line.getColor());
                g2d.drawLine(line.getX1() + 35, line.getY1() + 35, line.getX2() + 35, line.getY2() + 35);

            }
        }
    }


    private class Line {
        private int x1, x2, y1, y2;
        private Color color;

        public Line(int x1, int x2, int y1, int y2, Color color) {
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
            this.color = color;
        }

        public int getX1() {
            return x1;
        }

        public int getX2() {
            return x2;
        }

        public int getY1() {
            return y1;
        }

        public int getY2() {
            return y2;
        }

        public Color getColor() {
            return color;
        }
    }

    public static int getPuntenGehaaldTotaal() {
        return puntenGehaaldTotaal;
    }
}