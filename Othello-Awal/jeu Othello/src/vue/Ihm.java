package vue;



import java.util.Scanner;

import modele.Plateau;


public class Ihm {
    private Plateau plateau;


    public Ihm(Plateau plateau) {

        this.plateau = plateau;
    }

   /* public Plateau getPlateau() {

        return plateau;
    }

    public void setPlateau(Plateau plateau) {

        this.plateau= plateau;
    }*/

    public String saisirPseudo(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Veuillez saisir un pseudo : ");
        String pseudo = scan.nextLine();
        return pseudo;
    }

    public boolean veutRejouer1(String j1, String j2, int p1, int p2){
        Scanner scan = new Scanner(System.in);
        System.out.println("Voulez-vous rejouer?(oui ou non ? )");
        String rep1 = scan.nextLine();

        if (rep1.equalsIgnoreCase("oui")){
            return true;
        }
        else {
                if(p1> p2){
                    System.out.println("Le vainqueur est : "+j1);
                } else if (p1< p2) {
                    System.out.println("Le vainqueur est : "+j2);
                }
                else {
                    System.out.println("Egalité");
                }
        }
        System.out.println(j1+" a gagné "+p1+" partie(s)");
        System.out.println(j2+" a gagné "+p2+" partie(s)");
        return false;
    }
    public boolean veutRejouer2(String j1 , String j2, int p1, int p2){
        Scanner scan = new Scanner(System.in);
        System.out.println("Voulez-vous rejouer?(oui ou non ? )");
        String rep = scan.nextLine();
        if (rep.equalsIgnoreCase("oui")){
            return true;
        }
        else{
            if(p1> p2){
                System.out.println("Le vainqueur est : "+j1);
            } else if (p1< p2) {
                System.out.println("Le vainqueur est : "+j2);
            }
            else {
                System.out.println("Egalité");
            }
        }
        System.out.println();
        System.out.println(j1+" a gagné "+p1+" partie(s)");
        System.out.println(j2+" a gagné "+p2+" partie(s)");
        return false;

    }

    public String demanderEmplacement(String nom) {
        Scanner sc = new Scanner(System.in);
        String emplacement;
        boolean valide=false;
        System.out.println(nom + " : saisissez une ligne (compris entre 1 & 8) et une colonne(compris entre A & H) pour votre pion " +
                "ou bien saisissez P pour passer votre tour");
        do {
            emplacement= sc.nextLine();
            if(emplacement.length()<1){
                System.out.println("Les coordonnées que vous avez saisies sont incorrectes, veuillez en saisir de nouvelles");
            }
            else{
                if(emplacement.equalsIgnoreCase("P")){
                    valide=true;
                    return emplacement;
                }
                int ligne=Character.getNumericValue(emplacement.charAt(0));
                String colonne =String.valueOf(emplacement.charAt(1));
                if(ligne>= 1 && ligne<=8){
                    if(colonne.matches("[a-hA-H]")){
                        valide=true;
                    }
                    else{
                        System.out.println("Les coordonnées que vous avez saisies sont incorrectes, veuillez en saisir de nouvelles");
                    }
                }
                else{
                    System.out.println("Les coordonnées que vous avez saisies sont incorrectes, veuillez en saisir de nouvelles");
                }
            }
        }while(valide==false);
        return emplacement;
    }
    public int GetLigne(String s) {
        int ligne=Character.getNumericValue(s.charAt(0));
        return ligne;
    }
    public int GetColonne(String s) {
        int colonne=0;
        if (s.charAt(1)=='A'|| s.charAt(1)=='a'){
            colonne=1;
        }
        if (s.charAt(1)=='B'|| s.charAt(1)=='b'){
            colonne=2;
        }
        if (s.charAt(1)=='C'|| s.charAt(1)=='c'){
            colonne=3;
        }
        if (s.charAt(1)=='D'|| s.charAt(1)=='d'){
            colonne=4;
        }
        if (s.charAt(1)=='E'|| s.charAt(1)=='e'){
            colonne=5;
        }
        if (s.charAt(1)=='F'|| s.charAt(1)=='f'){
            colonne=6;
        }
        if (s.charAt(1)=='G'|| s.charAt(1)=='g'){
            colonne=7;
        }
        if (s.charAt(1)=='H'|| s.charAt(1)=='h'){
            colonne=8;
        }
        return colonne;
    }
    public void afficherGagnant (String j1 , String j2, int p1 , int p2){
        if (p1 > p2) {
            System.out.println(j1+ " : " + p1+" gagne");
        }
        else if (p2 < p1) {
            System.out.println(j2+ " : " +p2+" gagne");
        }
        else{
            System.out.println("La partie est terminée avec égalité : " +
                    j1+ " : " + p1 +" partie(s) gagnée(s)"+" "+
                    j2+ " : " + p2+" partie(s) gagnée(s)");
        }

    }

    public int Vainqueur(String j1,String j2, int p1, int p2){
        if (p1> p2){//compter pion
            System.out.println(j1+" a gagné : "+p1+" pions à "
                    +p2);
            return 1;
        }
        else if (p1<p2){
            System.out.println(j2+" a gagné : "+p2+" pions à "
                    +p1);
            return 2;
        }
        else {
            System.out.println("égalité");
            return 0;
        }
    }

    public void Remerciement(){
        System.out.println("Merci d'avoir joué");
    }


    public void AfficherMessageCoupPossible(){
        System.out.println("Il vous reste des coups possibles, vous ne pouvez pas passer votre tour ");}

    public void AfficherMessageCoupImpossible(){
        System.out.println("Il ne vous reste pas de coup, veuillez saisir P pour passer votre tour ");}
    public int demanderNbJoueur(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nombre de joueur");
        int nb = sc.nextInt();
        return nb;
    }
    public void afficherErreurNbJoueur(){
        System.out.println("Entrez 1 ou 2");
    }
    public void afficherCoupRobot(String s, String pseudo){
        System.out.println(pseudo+" joue en "+s);
    }
    public int ChoixDifficulteIA(){
        Scanner sc= new Scanner(System.in);
        System.out.println("Quelle difficulté pour l'IA ? (facile/difficile)");
        String rep= sc.nextLine();
        do {
            if (rep.equalsIgnoreCase("facile")){
                return 1;
            } else if (rep.equalsIgnoreCase("difficile")) {
                return 2;}
            else {
                System.out.println("Veuillez respecter la casse");
                System.out.println("Quelle difficulté pour l'IA ? (facile/difficile)");
                rep= sc.nextLine();
            }
        }while ((!rep.equalsIgnoreCase("difficile")) && (!rep.equalsIgnoreCase("facile")));

        return 0;
    }
    public void afficherMeilleurCoup(String s){
        System.out.println("Mon meilleur coup est : "+s );
    }
    public int demanderJeu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Choisissez un jeu (Awale ou Othello)");
        String rep= sc.nextLine();
        do {
            if (rep.equalsIgnoreCase("awale")){
                return 1;
            } else if (rep.equalsIgnoreCase("othello")) {
                return 2;}
            else {
                System.out.println("Veuillez respecter la casse");
                System.out.println("Choisissez un jeu (Awale ou Othello)");
                rep= sc.nextLine();
            }
        }while ((!rep.equalsIgnoreCase("awale")) && (!rep.equalsIgnoreCase("othello")));
        return 0;
    }
    public int demanderEmplacementAwale(String s){
        Scanner sc = new Scanner(System.in);
        System.out.println(s+" choisissez un emplacement entre 1 et 6");
        int emplacement= sc.nextInt();
        if (emplacement>6 || emplacement<0)
            while (emplacement>6 || emplacement<0){
                System.out.println(s +" veuillez choisir un emplacement entre 1 et 6");
                emplacement= sc.nextInt();}
        return emplacement;
    }
    public void afficherScore(String s,int score){
        System.out.println("Score "+s+": "+ score);
    }
    public void resultat(String j1, String j2, int scorej1, int scorej2){
        if (scorej1>scorej2){
            System.out.println(j1+" a gagné");
        }
        else if (scorej1<scorej2){
            System.out.println(j2+" a gagné");
        }
        else
            System.out.println("Egalité");
    }
}

