package modele;

import java.util.Random;

public class Robot  {
   private Plateau plateau;

   public Robot(Plateau plateau) {
      this.plateau = plateau;
   }

   public String ChoixCoupNaif(Joueur robot) {
      int nbcoupvalide =calculerNbCoupValide(robot);
      String[] coupsValides = new String[nbcoupvalide];
      coupsValides= listCoupValide(robot);
      Random random = new Random();
      int randomIndex = random.nextInt(nbcoupvalide);
      String coup = coupsValides[randomIndex];;
      return coup;
   }

   public int calculerNbCoupValide(Joueur joueur){
      int nb = 0;
      for (int i = 1; i <= 8; i++) {
         for (int j = 1; j <= 8; j++) {
            if (plateau.isLegit(i, j, joueur)) {
               nb++;
            }
         }
      }
      return nb;
   }
   public  String[] listCoupValide(Joueur joueur){
      int nbcoupvalide = calculerNbCoupValide(joueur);
      String[] coupValide = new String[nbcoupvalide];
      for(int k=0; k<coupValide.length;k++){
         for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
               if (plateau.isLegit(i, j, joueur)) {
                  String j1="";
                  if(j==1) {
                     j1 = "A";
                  }
                  else if(j==2) {
                     j1 = "B";
                  }
                  else if(j==3) {
                     j1 = "C";
                  }
                  else if(j==4) {
                     j1 = "D";
                  }
                  else if(j==5) {
                     j1 = "E";
                  }
                  else if(j==6) {
                     j1 = "F";
                  }
                  else if(j==7) {
                     j1 = "G";
                  }
                  else if(j==8) {
                     j1 = "H";
                  }

                  String a = Integer.toString(i)+j1;
                  coupValide[k]=a;

               }
            }}}
      return coupValide;
   }
   private int calculerScore(String s) {
      int somme=0;
      for (int l = 0; l < 8; l++) {
         for (int c = 0; c < 8; c++) {
            if(plateau.getBoard()[l][c]==s){
               if (((l == 0) || (l == 7)) && ((c == 0) || (c == 7))  ) {
                  somme+=11;
               } else if (((l == 0 || l == 7) && (c > 0 && c < 7)) || ((c == 0 || c == 7) && (l > 0 && l < 7)) ) {
                  somme+=6;

               } else {
                  somme+=1;
               }
            }
         }
      }
      return somme;
   }
   public boolean noeudTerminal(Joueur joueur, Joueur robot) {
      if (listCoupValide(joueur).length != 0 || listCoupValide(robot).length != 0 ){
         return false;
      }
      return true;
   }
   public int minimax(int profondeur, boolean max,Joueur robot, Joueur joueur) {
      if (profondeur == 0 || noeudTerminal(joueur,robot)) {
         if (!max){
            return calculerScore(robot.getPion());
         }
         else
            return calculerScore(joueur.getPion());
      }

      if (max) {
         int meilleur_score = -1000;
         for (String coup : listCoupValide(robot)) {
            int ligne= plateau.GetLigne(coup);
            int colonne= plateau.GetColonne(coup);
            plateau.appliquerCoup(ligne,colonne,robot,joueur);
            int score = minimax(profondeur- 1, false,robot,joueur);
            plateau.enleverCoup(ligne,colonne);
            meilleur_score = Math.max(meilleur_score, score);
         }
         return meilleur_score;
      } else {
         int meilleur_score = 1000;
         for (String coup : listCoupValide(joueur)) {
            int ligne2= plateau.GetLigne(coup);
            int colonne2= plateau.GetColonne(coup);
            plateau.appliquerCoup(ligne2,colonne2,joueur,robot);
            int score = minimax(profondeur - 1, true,robot,joueur);
            plateau.enleverCoup(ligne2,colonne2);
            meilleur_score = Math.min(meilleur_score, score);
         }
         return meilleur_score;
      }
   }
   public String choixMeilleurCoup(Joueur joueur, Joueur robot) {
      String [] coups_possibles = listCoupValide(robot);
      int meilleur_score =-1000;
      String meilleur_coup = coups_possibles[0];
      for (String coup : listCoupValide(robot)) {
         int ligne= plateau.GetLigne(coup);
         int colonne= plateau.GetColonne(coup);
         plateau.appliquerCoup(ligne,colonne,robot,joueur);
         int score = minimax(0, false,robot,joueur);
         plateau.enleverCoup(ligne,colonne);;
         if (score > meilleur_score) {
            meilleur_score = score;
            meilleur_coup = coup;
         }
      }

      return meilleur_coup;
   }


}

