package model;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Created by Sezer on 4/02/2015.
 */
public class Dot extends JButton{

    private int x;
    private int y;
    private int kleurIndicator;
    private Color color;
    private int kleurGetal;
    Random rand = new Random();
    CoordinatenTest model = new CoordinatenTest();

    public Dot(){
        kleurGetal = rand.nextInt(4)+1;

        switch (kleurGetal) {
            case 1:
                kleurIndicator = 1;
                color = Color.orange;
                setIcon(new ImageIcon("image/geel.png"));
                addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Dot.this.model.doeZet(x, y);
                    }
                });
                break;
            case 2:
                kleurIndicator = 2;
                color = Color.blue;
                setIcon(new ImageIcon("image/blauw.png"));
                addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Dot.this.model.doeZet(x, y);
                    }
                });
                break;
            case 3:
                kleurIndicator = 3;
                color = color.red;
                setIcon(new ImageIcon("image/rood.png"));
                addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Dot.this.model.doeZet(x, y);
                    }
                });
                break;
            case 4:
                kleurIndicator = 4;
                color = color.green;
                setIcon(new ImageIcon("image/dankgreen.png"));
                addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Dot.this.model.doeZet(x, y);
                    }
                });
                break;
            default:break;
        }
    }

    public int getColorIndicator() {
        return kleurIndicator;
    }

    public void setX(int x) {
        if (x >= 0) this.x = x;
    }

    public void setY(int y) {
        if (y >= 0) this.y = y;
    }

    public int gogetX() {
        return x;
    }

    public int gogetY() {
        return y;
    }

    public Color getColor() {
        return color;
    }
}