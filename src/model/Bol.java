package model;
import java.awt.*;

/**
 * Created by Sezer on 4/02/2015.
 */
public class Bol {

    private Color kleur;

    public Bol(Color kleur) {
        this.kleur = kleur;
    }

    public void verbinden() {

    }

    public void verdwijnen() {

    }

    public Color verschijnen(){
        RandomKleur kleurverschijnen = new RandomKleur();
        kleur = kleurverschijnen.getKleur();
        return kleur;
    }



}

