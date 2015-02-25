package model;

import java.awt.*;

/**
 * Created by Sezer on 25/02/2015.
 */
public class Tile {

    private Color kleur;

    public Tile(Color kleur) {
        this.kleur = kleur;
    }

    public Color getKleur() {
        return kleur;
    }

    public void setKleur(Color kleur) {
        this.kleur = kleur;
    }
}
