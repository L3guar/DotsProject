package model;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Sezer on 25/02/2015.
 */
public class Tile extends JPanel{

    private int x;
    private int y;
    private Color kleur;

    public Tile(Color kleur, int x, int y) {
        this.kleur = kleur;
        this.x = x;
        this.y = y;
        setOpaque(true);
    }

    public Color getKleur() {
        return kleur;
    }

    public void setKleur(Color kleur) {
        this.kleur = kleur;
    }
}
