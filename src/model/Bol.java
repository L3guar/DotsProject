package model;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by Sezer on 4/02/2015.
 */
public class Bol extends JButton{

    private int x;
    private int y;
    private int kleurGetal;
    Random rand = new Random();

    public void setX(int x) {
        if (x >= 0) this.x = x;
    }

    public void setY(int y) {
        if (y >= 0) this.y = y;
    }

    public Bol(){
        kleurGetal = rand.nextInt(4)+1;

        switch (kleurGetal) {
            case 1:
                setIcon(new ImageIcon(getClass().getResource("/images/geel.png")));
                break;
            case 2:
                setIcon(new ImageIcon(getClass().getResource("/images/blauw.png")));
                break;
            case 3:
                setIcon(new ImageIcon(getClass().getResource("/images/rood.png")));
                break;
            case 4:
                setIcon(new ImageIcon(getClass().getResource("/images/dankgreen.png")));
                break;
            default:break;
        }
    }



}

