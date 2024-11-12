package main;

import controleur.Controleur;
import modele.Joueur;
import modele.Plateau;
import modele.PlateauAwale;
import modele.Robot;
import vue.Ihm;

public class Main {
    public static void main(String[] args) {

        Plateau plateau = new Plateau();
        PlateauAwale plateau_awale = new PlateauAwale();
        Ihm ihm = new Ihm(plateau);
        Robot IA= new Robot(plateau);
        Controleur controleur = new Controleur(plateau, ihm, IA,plateau_awale);
        controleur.jouer();

    }
}


