package model;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Created by Sezer on 4/02/2015.
 */
public class Bol extends JButton{

    private int x;
    private int y;
    private int kleurGetal;
    Random rand = new Random();
    CoordinatenTest model = new CoordinatenTest();

    public Bol(){
        kleurGetal = rand.nextInt(4)+1;

        switch (kleurGetal) {
            case 1:
                setIcon(new ImageIcon("image/geel.png"));
                addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Bol.this.model.doeZet(x, y);
                    }
                });
                break;
            case 2:
                setIcon(new ImageIcon("image/blauw.png"));
                addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Bol.this.model.doeZet(x, y);
                    }
                });
                break;
            case 3:
                setIcon(new ImageIcon("image/rood.png"));
                addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Bol.this.model.doeZet(x, y);
                    }
                });
                break;
            case 4:
                setIcon(new ImageIcon("image/dankgreen.png"));
                addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Bol.this.model.doeZet(x, y);
                    }
                });
                break;
            default:break;
        }
    }

    public void setX(int x) {
        if (x >= 0) this.x = x;
    }

    public void setY(int y) {
        if (y >= 0) this.y = y;
    }


}

