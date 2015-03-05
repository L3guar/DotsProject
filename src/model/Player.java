package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.ByteOrder;

/**
 * Created by Mick on 11/02/2015.
 */
public class Player extends JFrame {

    private JPanel titelPaneel;
    private JPanel naamveldPaneel;

    private JLabel titelLabel;
    private JTextField naamVeld;
    private JButton doorgaan;

    private Color dankRed = new Color(178, 8, 8);

    public Player() throws HeadlessException {
        super("Dots | Naam opgeven");
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

        titelPaneel = new JPanel(new FlowLayout());
        titelPaneel.setBackground(dankRed);

        naamveldPaneel = new JPanel();
        naamveldPaneel.setBackground(dankRed);

        titelLabel = new JLabel("Geef uw naam in:");
        titelLabel.setFont(font);
        titelLabel.setForeground(Color.WHITE);

        naamVeld = new JTextField();
        naamVeld.setFont(font);
        naamVeld.setForeground(Color.WHITE);
        naamVeld.setBackground(dankRed);
        naamVeld.setHorizontalAlignment(JLabel.CENTER);

        doorgaan = new JButton("Doorgaan");
        doorgaan.setFont(font);
        doorgaan.setForeground(Color.WHITE);
        doorgaan.setBackground(dankRed);

    }

    private void maakListeners() {
        //sluit Player venster en opent het menu venster
        doorgaan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Menu menu = new Menu();
                dispose();
            }
        });

    }

    private void maakLayout() {
        add(titelPaneel, BorderLayout.PAGE_START);
        add(naamveldPaneel, BorderLayout.CENTER);

        titelPaneel.add(titelLabel);

        add(naamVeld, BorderLayout.CENTER);

        add(doorgaan, BorderLayout.PAGE_END);

    }


}
