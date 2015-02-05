package model;

import java.awt.*;
import java.util.Random;

/**
 * Created by Sezer on 4/02/2015.
 */
public class RandomKleur  {
    private Color kleur;
    private int kleurGetal;

    Random rand = new Random();

    public Color getKleur() {
        kleurGetal = rand.nextInt(3)+1;

        switch (kleurGetal) {
            case 1:
                kleur = Color.YELLOW;
                break;
            case 2:
                kleur = Color.BLUE;
                break;
            case 3:
                kleur = Color.RED;
                break;
            case 4:
                kleur = Color.GREEN;
                break;
            default:break;
        }
        return kleur;
    }
}
