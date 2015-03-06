package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Sezer on 3/03/2015.
 */
public class Menu extends JFrame{

    private JPanel knopPanel;

    private JButton play;
    private JButton highscores;
    private JButton help;
    private JButton exit;

    private JLabel dotsTitel;
    private JLabel welkomTekst;

    private Color dankRed = new Color(178, 8, 8);
    private static String naam;

    public Menu() throws HeadlessException {
        super("Dots | Menu");
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

    //naam verkrijgen voor welkom + *NAAM*
    public static String getNaam() {
        return naam;
    }

    //naam zetten voor welkom + *NAAM*
    public static void setNaam(String naam) {
        Menu.naam = naam;
    }

    private void maakAttributen() {
        Font font = new Font("Impact", Font.PLAIN, 30);

        //PLAY KNOP
        play = new JButton();
        play.setIcon(new ImageIcon("image/play.png"));
        play.setOpaque(false);
        play.setFocusPainted(false);
        play.setBorderPainted(false);
        play.setContentAreaFilled(false);
        play.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        //HIGHSCORES KNOP
        highscores = new JButton();
        highscores.setIcon(new ImageIcon("image/highscores.png"));
        highscores.setOpaque(false);
        highscores.setFocusPainted(false);
        highscores.setBorderPainted(false);
        highscores.setContentAreaFilled(false);
        highscores.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        //HELP KNOP
        help = new JButton();
        help.setIcon(new ImageIcon("image/help.png"));
        help.setOpaque(false);
        help.setFocusPainted(false);
        help.setBorderPainted(false);
        help.setContentAreaFilled(false);
        help.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        //EXIT KNOP
        exit = new JButton();
        exit.setIcon(new ImageIcon("image/exit.png"));
        exit.setOpaque(false);
        exit.setFocusPainted(false);
        exit.setBorderPainted(false);
        exit.setContentAreaFilled(false);
        exit.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        //TITEL
        dotsTitel = new JLabel("Dots the Game");
        dotsTitel.setFont(font);
        dotsTitel.setForeground(Color.WHITE);
        dotsTitel.setBackground(dankRed);
        dotsTitel.setOpaque(true);
        dotsTitel.setHorizontalAlignment(JLabel.CENTER);

        //WELKOM & NAAM VAN SPELER opvragen
        welkomTekst = new JLabel("Welcome " + Menu.getNaam());
        welkomTekst.setFont(font);
        welkomTekst.setForeground(Color.WHITE);
        welkomTekst.setBackground(dankRed);
        welkomTekst.setOpaque(true);
        welkomTekst.setHorizontalAlignment(JLabel.CENTER);
    }

    private void maakListeners() {
        //PLAY KNOP
        play.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Board board = new Board();
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                play.setIcon(new ImageIcon("image/playHover.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                play.setIcon(new ImageIcon("image/play.png"));
            }
        });

        //HELP KNOP
        help.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Help help = new Help();
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                help.setIcon(new ImageIcon("image/helpHover.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                help.setIcon(new ImageIcon("image/help.png"));
            }
        });

        //HIGHSCORES KNOP
        highscores.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Highscore highscore = new Highscore();
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                highscores.setIcon(new ImageIcon("image/highscoresHover.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                highscores.setIcon(new ImageIcon("image/highscores.png"));
            }
        });

        //EXIT KNOP
        exit.addMouseListener(new MouseAdapter() {
            //sluit het venster
            @Override
            public void mouseReleased(MouseEvent e) {
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                exit.setIcon(new ImageIcon("image/exitHover.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exit.setIcon(new ImageIcon("image/exit.png"));
            }
        });
    }

    private void maakLayout() {
        knopPanel = new JPanel(new GridLayout(0, 2, 10, 10)) ;

        add(knopPanel, BorderLayout.CENTER);

        add(dotsTitel, BorderLayout.PAGE_START);
        add(welkomTekst, BorderLayout.PAGE_END);

        knopPanel.setBackground(dankRed);

        knopPanel.add(play);
        knopPanel.add(help);
        knopPanel.add(highscores);
        knopPanel.add(exit);



    }




}
