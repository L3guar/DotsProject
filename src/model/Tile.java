package model;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Sezer on 25/02/2015.
 */
public class Tile extends JPanel{

    private Point positietile;
    private Color kleur;

    public Tile(Color kleur) {
        this.kleur = kleur;
    }

    public Color getKleur() {
        return kleur;
    }

    public void setPositietile(Point positietile) {
        this.positietile = positietile;
    }

    public Point getPositietile() {
        return positietile;
    }
}
