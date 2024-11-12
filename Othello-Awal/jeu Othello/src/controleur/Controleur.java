package controleur;

import modele.Joueur;
import modele.Plateau;
import modele.Robot;
import vue.Ihm;
import modele.PlateauAwale;

public class Controleur {


    private Plateau plateau;

    private PlateauAwale plateau_awale;
    private Ihm vue;

    private Robot IA;

    Joueur joueur1 = new Joueur("Sans Nom 1", "\u26AB", 0, 2);
    Joueur joueur2 = new Joueur("Sans Nom 2", "\u26AA", 0, 2);

    Joueur robot= new Joueur("AI player","\u26AA",0,2) ;

    int scorej1=0;
    int scorej2=0;

    String coupj1;
    String coupj2;

    int difficulte;
    public Controleur(Plateau plateau, Ihm vue, Robot IA, PlateauAwale plateau_awale) {
        this.plateau = plateau;
        this.vue = vue;
        this.IA=IA;
        this.plateau_awale=plateau_awale;
        // Initialiser le contrôleur
    }


    public void jouer() {
        boolean a=true;
        while (a){
            int jeu=vue.demanderJeu();
            if(jeu==2){
                int nbjoueur= vue.demanderNbJoueur();
                if(nbjoueur==1){
                    difficulte=vue.ChoixDifficulteIA();
                    jouer1joueur();
                    a=false;
                }
                else if (nbjoueur==2) {
                    jouer2joueurs();
                    a=false;
                }
                else {
                    vue.afficherErreurNbJoueur();
                }
            }
            if (jeu==1){
                jouerAwale();
                a=false;
            }
        }
    }

    public void jouer2joueurs() {
        if (joueur1.getPseudo().equals("Sans Nom 1")) {
            joueur1.setPseudo(vue.saisirPseudo());
        }
        if (joueur2.getPseudo().equals("Sans Nom 2")) {
            joueur2.setPseudo(vue.saisirPseudo());
        }
        plateau.display();
        //mise en place du jeu
        //premier coup
        while (plateau.unCoupPossible(joueur1) || plateau.unCoupPossible(joueur2)) {
            // joueur 1 joue
            //Demande emplacement
            String emplacement = vue.demanderEmplacement(joueur1.getPseudo());
            if ((emplacement.equalsIgnoreCase("P") && plateau.unCoupPossible(joueur1) == true)) {
                do {
                    vue.AfficherMessageCoupPossible();
                    emplacement = vue.demanderEmplacement(joueur1.getPseudo());
                } while (emplacement.equalsIgnoreCase("P") && plateau.unCoupPossible(joueur1) == true);
            }
            if (((!emplacement.equalsIgnoreCase("P")) && plateau.unCoupPossible(joueur1) == false)) {
                do {
                    vue.AfficherMessageCoupImpossible();
                    emplacement = vue.demanderEmplacement(joueur1.getPseudo());
                } while ((!emplacement.equalsIgnoreCase("P")) && plateau.unCoupPossible(joueur1) == false);
            }

            if (plateau.unCoupPossible(joueur1) == true) {
                int a = vue.GetLigne(emplacement);
                int b = vue.GetColonne(emplacement);
                if (plateau.isLegit(a, b, joueur1) == false) {
                    boolean valide = false;
                    while (valide == false) {
                        String nvemplacement = vue.demanderEmplacement(joueur1.getPseudo());
                        int nvligne = vue.GetLigne(nvemplacement);
                        int nvcolonne = vue.GetColonne(nvemplacement);
                        if (plateau.isLegit(nvligne, nvcolonne, joueur1) == true) {
                            plateau.appliquerCoup(nvligne, nvcolonne, joueur1, joueur2);
                            plateau.display();
                            valide = true;
                        }
                    }
                } else {
                    plateau.appliquerCoup(a, b, joueur1, joueur2);
                    plateau.display();
                }

            }


            // joueur 2 joue
            //Demande emplacement
            if (plateau.unCoupPossible(joueur1) || plateau.unCoupPossible(joueur2)) {
                String emplacement1 = vue.demanderEmplacement(joueur2.getPseudo());
                if ((emplacement1.equalsIgnoreCase("P") && plateau.unCoupPossible(joueur2) == true)) {
                    do {
                        vue.AfficherMessageCoupPossible();
                        emplacement1 = vue.demanderEmplacement(joueur2.getPseudo());
                    } while (emplacement1.equalsIgnoreCase("P") && plateau.unCoupPossible(joueur2) == true);
                }
                if (((!emplacement1.equalsIgnoreCase("P")) && plateau.unCoupPossible(joueur2) == false)) {
                    do {
                        vue.AfficherMessageCoupImpossible();
                        emplacement1 = vue.demanderEmplacement(joueur2.getPseudo());
                    } while ((!emplacement1.equalsIgnoreCase("P")) && plateau.unCoupPossible(joueur2) == false);
                }
                if (plateau.unCoupPossible(joueur2) == true) {
                    int a1 = vue.GetLigne(emplacement1);
                    int b1 = vue.GetColonne(emplacement1);
                    if (plateau.isLegit(a1, b1, joueur2) == false) {
                        boolean valide = false;
                        while (valide == false) {
                            String nvemplacement1 = vue.demanderEmplacement(joueur2.getPseudo());
                            int nvligne1 = vue.GetLigne(nvemplacement1);
                            int nvcolonne1 = vue.GetColonne(nvemplacement1);
                            if (plateau.isLegit(nvligne1, nvcolonne1, joueur2) == true) {
                                plateau.appliquerCoup(nvligne1, nvcolonne1, joueur2, joueur1);
                                plateau.display();
                                valide = true;
                            }
                        }
                    } else {
                        plateau.appliquerCoup(a1, b1, joueur2, joueur1);
                        plateau.display();
                    }
                }
            }

        }
        int v1=vue.Vainqueur(joueur1.getPseudo(), joueur2.getPseudo(), plateau.compterPion(joueur1), plateau.compterPion(joueur2) );
        plateau.nbPartieGagne(joueur1,joueur2,v1);
        if (vue.veutRejouer2(joueur1.getPseudo(), joueur2.getPseudo(), joueur1.getPartieGagnees(), joueur2.getPartieGagnees()) == true) {
            plateau.resetPlateau(plateau.getBoard());
            jouer2joueurs();
        } else {
            vue.Remerciement();
        }


    }


    public void jouer1joueur() {
        if (joueur1.getPseudo().equals("Sans Nom 1")) {
            joueur1.setPseudo(vue.saisirPseudo());
        }
        plateau.display();
        //mise en place du jeu
        //premier coup
        while (plateau.unCoupPossible(joueur1) || plateau.unCoupPossible(robot)) {
            // joueur 1 joue
            //Demande emplacement
            String emplacement = vue.demanderEmplacement(joueur1.getPseudo());
            if ((emplacement.equalsIgnoreCase("P") && plateau.unCoupPossible(joueur1) == true)) {
                do {
                    vue.AfficherMessageCoupPossible();
                    emplacement = vue.demanderEmplacement(joueur1.getPseudo());
                } while (emplacement.equalsIgnoreCase("P") && plateau.unCoupPossible(joueur1) == true);
            }
            if (((!emplacement.equalsIgnoreCase("P")) && plateau.unCoupPossible(joueur1) == false)) {
                do {
                    vue.AfficherMessageCoupImpossible();
                    emplacement = vue.demanderEmplacement(joueur1.getPseudo());
                } while ((!emplacement.equalsIgnoreCase("P")) && plateau.unCoupPossible(joueur1) == false);
            }

            if (plateau.unCoupPossible(joueur1) == true) {
                int a = vue.GetLigne(emplacement);
                int b = vue.GetColonne(emplacement);
                if (plateau.isLegit(a, b, joueur1) == false) {
                    boolean valide = false;
                    while (valide == false) {
                        String nvemplacement = vue.demanderEmplacement(joueur1.getPseudo());
                        int nvligne = vue.GetLigne(nvemplacement);
                        int nvcolonne = vue.GetColonne(nvemplacement);
                        if (plateau.isLegit(nvligne, nvcolonne, joueur1) == true) {
                            plateau.appliquerCoup(nvligne, nvcolonne, joueur1, robot);
                            plateau.display();
                            valide = true;
                        }
                    }
                } else {
                    plateau.appliquerCoup(a, b, joueur1, robot);
                    plateau.display();
                }

            }
            /*else {
                do {
                    vue.AfficherMessageCoupImpossible();
                    emplacement = vue.demanderEmplacement(joueur1);
                } while (!(emplacement.equalsIgnoreCase("P")));
            }*/


            // Robot joue

            if (plateau.unCoupPossible(robot) == true) {
                if (difficulte==1){
                    String coup=IA.ChoixCoupNaif(robot);
                    vue.afficherCoupRobot(coup, robot.getPseudo());
                    int coupLigne=vue.GetLigne(coup);
                    int coupColonne=vue.GetColonne(coup);
                    plateau.appliquerCoup(coupLigne, coupColonne, robot, joueur1);
                    plateau.display();
                } else if (difficulte==2) {
                    String coup=IA.choixMeilleurCoup(joueur1,robot);
                    int coupLigne=vue.GetLigne(coup);
                    int coupColonne=vue.GetColonne(coup);
                    plateau.appliquerCoup(coupLigne, coupColonne, robot, joueur1);
                    vue.afficherMeilleurCoup(coup);
                    vue.afficherCoupRobot(coup, robot.getPseudo());
                    plateau.display();
                }

            }
        }

        int v2=vue.Vainqueur(joueur1.getPseudo(), robot.getPseudo(), plateau.compterPion(joueur1), plateau.compterPion(robot) );
        plateau.nbPartieGagne(joueur1,robot,v2);
        if (vue.veutRejouer1(joueur1.getPseudo(), robot.getPseudo(), joueur1.getPartieGagnees(), robot.getPartieGagnees()) == true) {
            plateau.resetPlateau(plateau.getBoard());
            jouer1joueur();
        } else {
            vue.Remerciement();
        }
    }
    public void jouerAwale(){
        if (joueur1.getPseudo().equals("Sans Nom 1")) {
            joueur1.setPseudo(vue.saisirPseudo());}
        if (joueur2.getPseudo().equals("Sans Nom 2")) {
            joueur2.setPseudo(vue.saisirPseudo());}
        plateau_awale.afficherPlateau();

        while (((plateau_awale.CoupPossibleAwale(joueur1,1)==true)&&(plateau_awale.CoupPossibleAwale(joueur2,0)==true))
        && (scorej1<24 && scorej2<24)){
            int emplacement = vue.demanderEmplacementAwale(joueur1.getPseudo());
            if (plateau_awale.isLegit(1,emplacement)==true){
                coupj1=plateau_awale.appliquerCoupAwale(1,emplacement);
                scorej1+=plateau_awale.capturerGraine(coupj1,emplacement,1);
                plateau_awale.afficherPlateau();
                vue.afficherScore(joueur1.getPseudo(),scorej1);
                vue.afficherScore(joueur2.getPseudo(),scorej2);
            }

            else{
                do {
                    emplacement= vue.demanderEmplacementAwale(joueur1.getPseudo());
                }while(plateau_awale.isLegit(1,emplacement)==false);
                String coupj1=plateau_awale.appliquerCoupAwale(1,emplacement);
                scorej1+=plateau_awale.capturerGraine(coupj1,emplacement,1);
                plateau_awale.afficherPlateau();
                vue.afficherScore(joueur1.getPseudo(),scorej1);
                vue.afficherScore(joueur2.getPseudo(),scorej2);
            }
            if (((plateau_awale.CoupPossibleAwale(joueur1,1)==true)&&(plateau_awale.CoupPossibleAwale(joueur2,0)==true))
                    && (scorej1<24 && scorej2<24)){
                int emplacement2 = vue.demanderEmplacementAwale(joueur2.getPseudo());
                if (plateau_awale.isLegit(0,emplacement2)==true){
                    coupj2=plateau_awale.appliquerCoupAwale(0,emplacement2);
                    scorej2+=plateau_awale.capturerGraine(coupj2,emplacement2,0);
                    plateau_awale.afficherPlateau();
                    vue.afficherScore(joueur1.getPseudo(),scorej1);
                    vue.afficherScore(joueur2.getPseudo(),scorej2);
                }
                else{
                    do {
                        emplacement2= vue.demanderEmplacementAwale(joueur2.getPseudo());
                    }while(plateau_awale.isLegit(0,emplacement2)==false);
                    coupj2=plateau_awale.appliquerCoupAwale(0,emplacement2);
                    scorej2+=plateau_awale.capturerGraine(coupj2,emplacement2,0);
                    plateau_awale.afficherPlateau();
                    vue.afficherScore(joueur1.getPseudo(),scorej1);
                    vue.afficherScore(joueur2.getPseudo(),scorej2);
                }
            }
        }
        vue.resultat(joueur1.getPseudo(), joueur2.getPseudo(), scorej1,scorej2);
        if (scorej1>scorej2){
            joueur1.setPartieGagnees(joueur1.getPartieGagnees()+1);
        }
        else if (scorej1<scorej2){
            joueur2.setPartieGagnees(joueur2.getPartieGagnees()+1);
        }
        if (vue.veutRejouer1(joueur1.getPseudo(), joueur2.getPseudo(), joueur1.getPartieGagnees(), joueur2.getPartieGagnees()) == true) {
            plateau_awale.resetPlateauAwale(plateau_awale.getPlateauAwale());
            jouerAwale();
        } else {
            vue.Remerciement();
        }
    }
}





