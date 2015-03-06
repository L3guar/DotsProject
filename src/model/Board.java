
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
public class Board extends JFrame{

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
    int connectcounter = 0;
    Point connectPoint;
    ArrayList connect = new ArrayList();

    private JPanel menuPanel;
    private JPanel gridPanel;

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

        Color dankgreen = new Color(3,173,26);
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

        menuPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gridPanel = new JPanel(new GridLayout(7, 7, 1, 1));
        gridPanel.setOpaque(true);
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
                final Dot dot = new Dot();
                final int colorIndicator = dot.getColorIndicator();
                dot.setX(x);
                dot.setY(y);
                final int bolX = dot.gogetX();
                final int bolY = dot.gogetY();
                gridPanel.add(dot);
                bollenlijst.add(dot);
                dot.setOpaque(true);
                dot.setFocusPainted(false);
                dot.setBorderPainted(false);
                dot.setContentAreaFilled(false);
                dot.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
                dot.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (firstIndicator == colorIndicator) {

                            if (firstXclick == bolX - 1 && firstYclick == bolY - 1 || firstXclick == bolX && firstYclick == bolY - 1
                                    || firstXclick == bolX + 1 && firstYclick == bolY - 1 || firstXclick == bolX + 1 && firstYclick == bolY
                                    || firstXclick == bolX - 1 && firstYclick == bolY || firstXclick == bolX - 1 && firstYclick == bolY + 1
                                    || firstXclick == bolX && firstYclick == bolY + 1 || firstXclick == bolX + 1 && firstYclick == bolY + 1) {

                                System.out.println("yay");
                                firstXclick = bolX;
                                firstYclick = bolY;
                                Slang connectLine = new Slang(firstXcoord, firstYcoord, dot.getX(), dot.getY());

                                x1 = connectLine.getX1();
                                x2 = connectLine.getX2();
                                y1 = connectLine.getY1();
                                y2 = connectLine.getY2();

                                connect.add(firstXclick);
                                connectcounter += 1;
                                connect.add(firstYclick);
                                connectcounter += 1;

                                for (int i = 0; i < connect.size(); i++) {
                                    System.out.println(connect.get(i));
                                }

                                makeLine makeLine = new makeLine();
                                add(makeLine, BorderLayout.CENTER);
                                makeLine.repaint();

                            } else if (firstXclick == bolX && firstYclick == bolY) {

                        } else {
                            firstXclick = bolX;
                            firstYclick = bolY;
                            System.out.println("te ver!");
                        }}
                        else {
                            connectcounter = 0;
                            firstIndicator = colorIndicator;
                            firstXclick = bolX;
                            firstYclick = bolY;
                            firstXcoord = dot.getX();
                            firstYcoord = dot.getY();
                            connectPoint = dot.getLocation();
                            connect.add(firstXclick);
                            connectcounter += 1;
                            connect.add(firstYclick);
                            connectcounter += 1;



                    }

                    }
                });


            }
            }


    }

     private class makeLine extends JPanel{
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            //Dikte van lijnen instellen
            g2d.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));

            //Teken lijn(en)
            g2d.setColor(Color.black);
            g2d.drawLine(50,50,300,50);

        }
    }
}

