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
        super("Dots | Spel handleiding");
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

        titelLabel = new JLabel("Spel handleiding");
        titelLabel.setFont(font);
        titelLabel.setForeground(Color.WHITE);
        titelLabel.setBackground(dankRed);
        titelLabel.setHorizontalAlignment(JLabel.CENTER);
        titelLabel.setOpaque(true);

        infoTekst = new JLabel("HIER KOMT INFORMATIE OVER SPEL");
        infoTekst.setFont(font);
        infoTekst.setForeground(Color.WHITE);
        infoTekst.setBackground(dankRed);
        infoTekst.setHorizontalAlignment(JLabel.CENTER);
        infoTekst.setOpaque(true);

        terug = new JLabel("Terug");
        terug.setFont(font);
        terug.setForeground(Color.WHITE);
        terug.setBackground(dankRed);
        terug.setHorizontalAlignment(JLabel.CENTER);
        terug.setOpaque(true);
    }

    private void maakListeners() {
        terug.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Menu menu = new Menu();
                dispose();
            }
        });

    }

    private void maakLayout() {
        add(titelLabel, BorderLayout.PAGE_START);
        add(infoTekst, BorderLayout.CENTER);
        add(terug, BorderLayout.PAGE_END);

    }
}
