package view;

import controller.Spel;
import model.*;

import model.Bol;

/**
 * Created by Sezer on 4/02/2015.
 */
public class GUIView {
    public static void main(String[] args) {
        Bol[] dots = new Bol[49];
        Slang slang = new Slang();
        Highscore highscore = new Highscore();
        Spel spel = new Spel();

        Bol bolletje = new Bol();

        System.out.println(bolletje.verschijnen());
    }



}
