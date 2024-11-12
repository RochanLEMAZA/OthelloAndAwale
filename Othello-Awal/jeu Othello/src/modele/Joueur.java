package modele;

public class Joueur {
    String pseudo ;
    String pion ;


    int partieGagnees = 0 ;
    int nbPion = 2;

    public Joueur(String pseudo, String pion, int partieGagnees, int nbPion) {
        this.pseudo = pseudo;
        this.pion = pion;
        this.partieGagnees = partieGagnees;
        this.nbPion = nbPion;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPion() {
        return pion;
    }

    public void setPion(String pion) {
        this.pion = pion;
    }

    public int getPartieGagnees() {
        return partieGagnees;
    }


    public void setPartieGagnees(int partieGagnees) {
        this.partieGagnees = partieGagnees;
    }

    public int getNbPion() {
        return nbPion;
    }

    public void setNbPion(int nbPion) {
        this.nbPion = nbPion;
    }





}

