package model;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Sezer on 5/02/2015.
 */
public class Slang extends JPanel{
    private Color kleur = Color.BLACK;
    int x1;
    int y1;
    int x2;
    int y2;

    public Slang(int eersteXCoord, int eersteYCoord, int x, int y) {
        this.x1 = eersteXCoord;
        this.y1 = eersteYCoord;
        this.x2 = x;
        this.y2 = y;
    }


    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }
}
