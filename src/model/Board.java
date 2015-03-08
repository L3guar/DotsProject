package model;

import javax.swing.*;
import java.awt.*;
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
    private int firstIndicator = 0;
    private int firstXclick = 1000;
    private int firstYclick = 1000;
    private int firstXcoord;
    private int firstYcoord;
    private int x1;
    private int x2;
    private int y1;
    private int y2;
    private Color color;
    private List<Line> lines = new ArrayList<>();
    int connectcounter = 0;
    Point connectPoint;
    ArrayList connect = new ArrayList();
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

    public Board() {
        super("Dots");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        makeAttributes();
        makeListeners();
        makeLayout();
        pack();
        setSize(575, 725);
        setLocationRelativeTo(this);
        setResizable(false);
        setVisible(true);
    }

    //attributen maken en titels opmaken
    public void makeAttributes() {
        Font font = new Font("Impact", Font.PLAIN, 30);
        levelTitle = new JLabel("level");
        levelTitle.setFont(font);
        levelTitle.setForeground(Color.RED);
        levelNumber = new JLabel("1");
        levelNumber.setFont(font);
        levelNumber.setForeground(Color.RED);
        Color dankgreen = new Color(3, 173, 26);
        scoreTitle = new JLabel("score");
        scoreTitle.setFont(font);
        scoreTitle.setForeground(dankgreen);
        scoreNumber = new JLabel("2");
        scoreNumber.setFont(font);
        scoreNumber.setForeground(dankgreen);
        targetTitle = new JLabel("target");
        targetTitle.setFont(font);
        targetTitle.setForeground(Color.BLUE);
        targetNumber = new JLabel("3");
        targetNumber.setFont(font);
        targetNumber.setForeground(Color.BLUE);
        timeTitle = new JLabel("time");
        timeTitle.setFont(font);
        timeTitle.setForeground(Color.ORANGE);
        timeNumber = new JLabel("4");
        timeNumber.setFont(font);
        timeNumber.setForeground(Color.ORANGE);
        //pauze afbeelding en resizen
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
        leftLabel.setPreferredSize(new Dimension(0, 0));
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
                // random kleur van dot krijgen om te vergelijken
                final int colorIndicator = dot.getColorIndicator();
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
                    public void mouseClicked(MouseEvent e) {
                        // kijk of de volgende bol dezelfde kleur heeft
                        if (firstIndicator == colorIndicator) {
                            // kijk of de bol in dezelfde omstreek ligt.
                            if (firstXclick == dotX - 1 && firstYclick == dotY - 1 || firstXclick == dotX && firstYclick == dotY - 1
                                    || firstXclick == dotX + 1 && firstYclick == dotY - 1 || firstXclick == dotX + 1 && firstYclick == dotY
                                    || firstXclick == dotX - 1 && firstYclick == dotY || firstXclick == dotX - 1 && firstYclick == dotY + 1
                                    || firstXclick == dotX && firstYclick == dotY + 1 || firstXclick == dotX + 1 && firstYclick == dotY + 1) {
                                System.out.println("yay");
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
                                // TE VERWIJDEREN ->

                                for (int i = 0; i < connect.size(); i++) {
                                    System.out.println(connect.get(i));
                                }
                                //  <- TE VERWIJDEREN
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
                                    for (int i = 0; i < connect.size(); i+=2) {

                                        int connectY = (int) connect.get(i+1);
                                        int connectX = (int) connect.get(i);
                                        bollenlijst.get(7 * connectY + connectX).setIcon(bollenlijst.get((7 * connectY + connectX)-7).getIcon());
                                    }
                                }
                            } else {
                                System.out.println("te ver!");
                            }
                        } else {
                            connectcounter = 0;
                            firstIndicator = colorIndicator;
                            firstXclick = dotX;
                            firstYclick = dotY;
                            firstXcoord = dot.getX();
                            firstYcoord = dot.getY();
                            connectPoint = dot.getLocation();
                            connect.add(firstXclick);
                            connect.add(firstYclick);
                            connectcounter += 1;
                            color = dot.getColor();
                            lines.clear();
                            makeLine.repaint();

                            if (connectcounter > 1) {
                                int dotCounter;
                                dotCounter = 7 * firstYcoord + (firstXcoord + 1);
                                dot.setIcon(new ImageIcon("image/transparent.png"));
                                gridPanel.add(dot, dotCounter);
                                connectcounter = 0;
                            }
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
}