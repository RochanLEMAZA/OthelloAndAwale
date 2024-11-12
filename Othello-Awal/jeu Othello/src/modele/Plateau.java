package modele;

import java.util.HashMap;
import java.util.Map;

public class Plateau {
    private String[][] board;
    private int l;
    private int c;

    private Map<String, Integer> pointsParCase;
    private String W = "\u26AA";
    private String B = "\u26AB";
    private String V = "\uD83D\uDFE9";


    public Plateau() {
        board = new String[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i == 3 && j == 3) || (i == 4  && j == 4)) {
                    board[i][j] = W;
                } else if ((i == 3 && j == 4) || (i == 4 && j == 3)) {
                    board[i][j] = B;
                } else {
                    board[i][j] =V;
                }
            }
        }
    }

    public void setBoard(String[][] board) {
        this.board = board;
    }
    public String[][] getBoard() {

        return board;
    }

    public void display() {
        System.out.println("   A  B  C  D   E  F  G  H");
        for (int i = 0; i < 8; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int compterPion(Joueur joueur) {
        int p = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == joueur.getPion()) {
                    p++;
                }

            }
        }
        joueur.setNbPion(p);
        return p ;

    }

    public boolean verifDiagonalBasDroit(int l,int c,Joueur joueur){
        this.l=l;
        this.c=c;
        l=l-1;
        c=c-1;
        if((l>=0 && l<6)&&(c>=0 && c<6)) {
            if ((board[l + 1][c + 1] != joueur.getPion()) && (board[l + 1][c + 1] != V)) {
                for (int i = l + 2; i < 8;i++ ) {
                    if (((board[i][c+2]!=(joueur.getPion())) && ((board[i][c+2]!=V)) )){
                        c=c+1;
                        if (c+2>7|| i>7){

                            return false;
                        }

                    }
                    else if ((board[i][c+2]==V)) {

                        return false;
                    }
                    else {

                        return true;

                    }

                }
            }
            else {


                return false;
            }
        }

        return false;
    }

    public boolean verifDiagonalBasGauche(int l,int c,Joueur joueur){
        this.l=l;
        this.c=c;
        l=l-1;
        c=c-1;
        if((l>=0 && l<6)&&(c>=2 && c<8)) {
            if ((board[l + 1][c - 1] != joueur.getPion()) && (board[l + 1][c - 1] != V)) {
                for (int i = l + 2; i < 8;i++ ) {
                    if (((board[i][c-2]!=(joueur.getPion())) && ((board[i][c-2]!=V)))){
                        c=c-1;
                        if (c-2<0||i>7){
                            return false;
                        }

                    }
                    else if ((board[i][c-2]==V)) {
                        return false;
                    }
                    else {
                        return true;

                    }

                }
            }
            else {
                return false;
            }
        }
        return false;
    }

    public boolean verifDiagonalHautDroit(int l,int c,Joueur joueur){
        this.l=l;
        this.c=c;
        l=l-1;
        c=c-1;
        if((l>=2 && l<8)&&(c>=0 && c<6)) {
            if ((board[l - 1][c + 1] != joueur.getPion()) && (board[l - 1][c + 1] != V)) {
                for (int i = l - 2; i >-1;i-- ) {
                    if (((board[i][c+2]!=(joueur.getPion())) && ((board[i][c+2]!=V)))){
                        c++;
                        if (c+2>7||i<0){
                            return false;
                        }

                    }
                    else if ((board[i][c+2]==V)) {
                        return false;
                    }
                    else {
                        return true;

                    }

                }
            }
            else {
                return false;
            }
        }
        return false;
    }

    public boolean verifDiagonalHautGauche(int l,int c,Joueur joueur){
        this.l=l;
        this.c=c;
        l=l-1;
        c=c-1;
        if((l>=2 && l<8)&&(c>=2 && c<8)) {
            if ((board[l - 1][c - 1] != joueur.getPion()) && (board[l - 1][c - 1] != V)) {
                for (int i = l - 2; i>-1;i-- ) {
                    if (((board[i][c-2]!=(joueur.getPion())) && ((board[i][c-2]!=V)))){
                        c--;
                        if (c-2<0||i<0){
                            return false;
                        }

                    }
                    else if ((board[i][c-2]==V)) {
                        return false;
                    }
                    else {
                        return true;

                    }

                }
            }
            else {

                return false;
            }
        }
        return false;
    }

    public boolean verifHorizontalDroit(int l,int c,Joueur joueur){
        this.l=l;
        this.c=c;
        l=l-1;
        c=c-1;
        if((c>=0 && c<6)) {
            if ((board[l][c + 1] != joueur.getPion()) && (board[l][c + 1] != V)) {
                for (int i = c + 2; i<8;i++ ) {
                    if (((board[l][i]!=(joueur.getPion())) && ((board[l][i]!=V)))){;
                        continue;
                    }
                    else if ((board[l][i]==V)) {
                        return false;
                    }
                    else {
                        return true;

                    }

                }
            }
            else {
                return false;
            }
        }
        return false;
    }

    public boolean verifHorizontalGauche(int l,int c,Joueur joueur){
        this.l=l;
        this.c=c;
        l=l-1;
        c=c-1;
        if((c>=2 && c<=7)) {
            if ((board[l][c -1] != joueur.getPion()) && (board[l][c - 1] != V)) {
                for (int i = c - 2; i >-1;i-- ) {
                    if (((board[l][i]!=(joueur.getPion())) && ((board[l][i]!=V)))){
                        continue;
                    }
                    else if ((board[l][i]==V)) {
                        return false;
                    }
                    else {;
                        return true;

                    }

                }
            }
            else {
                return false;
            }
        }
        return false;
    }

    public boolean verifVerticalHaut(int l,int c,Joueur joueur){
        this.l=l;
        this.c=c;
        l=l-1;
        c=c-1;
        if((l>2 && l<=8)) {
            if ((board[l-1][c] != joueur.getPion()) && (board[l-1][c] != V)) {
                for (int i = l - 2; i >-1;i-- ) {
                    if (((board[i][c]!=(joueur.getPion())) && ((board[i][c]!=V)))){
                        continue;
                    }
                    else if ((board[i][c]==V)) {
                        return false;
                    }
                    else {
                        return true;

                    }

                }
            }
            else {
                return false;
            }
        }
        return false;
    }

    public boolean verifVerticalBas(int l,int c,Joueur joueur){
        this.l=l;
        this.c=c;
        l=l-1;
        c=c-1;
        if((l>=0 && l<6)) {
            if ((board[l+1][c] != joueur.getPion()) && (board[l+1][c] != V)) {
                for (int i = l + 2; i<8;i++ ) {
                    if (((board[i][c]!=(joueur.getPion())) && ((board[i][c]!=V)))){
                        continue;
                    }
                    else if ((board[i][c]==V)) {
                        return false;
                    }
                    else {
                        return true;

                    }

                }
            }
            else {
                return false;
            }
        }
        return false;
    }

    public boolean isLegit(int l , int c , Joueur joueur){
        //prend la valeur de l'entree(respecte pas les bornes->l-1||c-1)
        if ((board[l - 1][c - 1] == V) && ((verifVerticalHaut(l, c, joueur) == true || verifVerticalBas(l, c, joueur) == true
                || verifHorizontalDroit(l, c, joueur) == true || verifHorizontalGauche(l, c, joueur) == true || verifDiagonalBasDroit(l, c, joueur) == true || verifDiagonalBasGauche(l, c, joueur) == true
                || verifDiagonalHautDroit(l, c, joueur) == true || verifDiagonalHautGauche(l, c, joueur) == true))) {
            //System.out.println("oui");
            return true;
        }
        //System.out.println("non");
        return false;
    }

    public void appliquerCoup(int row, int col, Joueur j1, Joueur j2) {
        //pose et inverse les pions
        row--;
        col--;
        board[row][col] =j1.getPion();

        // Vérifie les 8 directions où l'on doit retourner les pieces de l'adversaire
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }

                int r = row + i;
                int c = col + j;
                boolean PieceEntre = false; //si il y a des pieces adverses entre les pions du joueur
                int numPion = 0; //nombre de pion à renverser

                while (r >= 0 && r < 8 && c >= 0 && c < 8) {
                    if (board[r][c] == V) {
                        break;
                    } else if (board[r][c] == j2.getPion()) {
                        PieceEntre = true;
                        numPion++;
                    } else if (board[r][c] == j1.getPion() && PieceEntre) {
                        //Renverse les pions qui se trouvent entre les pions du joueur qui joue
                        for (int k = 1; k <= numPion; k++) {
                            board[row + k*i][col + k*j] = j1.getPion();
                        }
                        break;
                    } else {
                        break;
                    }

                    r += i;
                    c += j;
                }
            }
        }
    }
    public void enleverCoup(int l, int c){
        int ligne=l-1;
        int colonne=c-1;
        board[ligne][colonne]=V;
    }
    public void resetPlateau(String[][] board) {
        for (int i=0; i<8;i++) {
            for (int j=0; j<8;j++) {
                if ((i==3 && j==3) || (i==4 && j==4)) {
                    board[i][j]=W;
                } else if ((i==3 && j==4) || (i==4 && j==3)) {
                    board[i][j]=B;
                } else {
                    board[i][j]=V;
                }
            }
        }
    }

    public boolean unCoupPossible(Joueur joueur) {
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                if (isLegit(i, j, joueur)) {
                    return true;
                }
            }
        }
        return false;
    }
    public void nbPartieGagne(Joueur j1,Joueur j2, int x){
        if(x==1){
            j1.setPartieGagnees(j1.getPartieGagnees()+1);
        } else if (x==2) {
            j2.setPartieGagnees(j2.getPartieGagnees()+1);
        }
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


}
