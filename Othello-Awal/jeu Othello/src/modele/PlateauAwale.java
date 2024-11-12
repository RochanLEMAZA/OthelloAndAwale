package modele;
public class PlateauAwale {
    private int[][] plateauAwale;

    public PlateauAwale() {
        plateauAwale = new int[2][6];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 6; j++) {
                plateauAwale[i][j] = 4;
            }
        }
    }

    public void afficherPlateau() {
        System.out.println("1   2   3   4   5   6");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(plateauAwale[i][j] + " | ");
            }
            System.out.println();
        }
    }

    public int[][] getPlateauAwale() {
        return plateauAwale;
    }

    public void setPlateauAwale(int[][] plateau) {
        this.plateauAwale = plateau;
    }

    public void resetPlateauAwale(int[][] plateau) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 6; j++) {
                plateau[i][j] = 4;
            }
        }
    }

    public boolean CoupPossibleAwale(Joueur joueur, int l) {
        for (int i = 0; i < 6; i++) {
            if (plateauAwale[l][i] != 0)
                return true;
        }
        return false;
    }

    public String appliquerCoupAwale(int l, int col) {
        col--;
        int col_depart = col;
        int ligne_depart = l;
        int lignedefin;
        int colonnedefin;
        int nbgraine = plateauAwale[l][col];
        String dernieremplacement="";
        plateauAwale[l][col] = 0;
        while (nbgraine > 0) {
            lignedefin = l;
            colonnedefin = col;
            dernieremplacement = Integer.toString(lignedefin) + Integer.toString(colonnedefin);
            if (!(l == ligne_depart && col == col_depart) && plateauAwale[ligne_depart][col_depart] < 11) {
                if (l == 1 && col < 5) {
                    plateauAwale[l][col]++;
                    nbgraine--;
                    col++;
                } else if (l == 1 && col == 5) {
                    plateauAwale[l][col]++;
                    nbgraine--;
                    l--;
                } else if (l == 0 && (col <= 5 && col >= 1)) {
                    plateauAwale[l][col]++;
                    nbgraine--;
                    col--;
                } else if (l == 0 && col == 0) {
                    plateauAwale[l][col]++;
                    nbgraine--;
                    l++;
                }
            } else {
                if (l == 1 && col < 5) {
                    col++;
                } else if (l == 1 && col == 5) {
                    l--;
                } else if (l == 0 && (col <= 5 && col >= 1)) {
                    col--;
                } else if (l == 0 && col == 0) {
                    l++;
                }
            }
        }

            /*dernier emplacement servira pour capturer les graines car il nous faudra savoir
            ou est ce qu'a été déposé la dernier graine*/
        return dernieremplacement;

    }

    public boolean isLegit(int l, int c) {
        // on vérifie que la case de départ contient des graines (case Non vide)
        if (plateauAwale[l][c - 1] == 0) {
            return false;
        }
        return true;
    }

    public int capturerGraine(String dernier_emplacement, int c, int l){
        int score=0;
        int ligne_final=Character.getNumericValue(dernier_emplacement.charAt(0));
        int col_final=Character.getNumericValue(dernier_emplacement.charAt(1));
        if (l==0 && ligne_final==1){
            while(true){
                if (plateauAwale[ligne_final][col_final]==2 || plateauAwale[ligne_final][col_final]==3){
                    score+=plateauAwale[ligne_final][col_final];
                    plateauAwale[ligne_final][col_final]=0;
                    if(col_final==0){
                        break;
                    }
                    else{
                        col_final--;
                    }
                }else
                    break;
            }
        }
        if (l==1 && ligne_final==0){
            while(true){
                if (plateauAwale[ligne_final][col_final]==2 || plateauAwale[ligne_final][col_final]==3){
                    score+=plateauAwale[ligne_final][col_final];
                    plateauAwale[ligne_final][col_final]=0;
                    if(col_final==5){
                        break;
                    }
                    else{
                        col_final++;
                    }
                }else
                    break;
            }
        }
        return score;
    }

}