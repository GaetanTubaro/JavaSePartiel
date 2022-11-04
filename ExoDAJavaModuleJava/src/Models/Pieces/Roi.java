package Models.Pieces;

import Interfaces.Mouvement;
import Models.Plateau.Couleur;
import Models.Plateau.Position;

import java.util.ArrayList;
import java.util.List;

public class Roi extends Piece implements Mouvement {
    private static List<Roi> tabRoi = new ArrayList<Roi>();
    private boolean echec = false;
    private boolean echecEtMat = false;

    private boolean pat = false;

    private boolean premierTour = true;

    public Roi(int x, int y, Couleur c) {
        super(x, y, c);
        tabRoi.add(this);
        // TODO Auto-generated constructor stub
    }

    public boolean isEchec() {
        return echec;
    }


    public boolean isEchecEtMat() {
        return echecEtMat;
    }


    public List<Position> getMouvementPossible() {
        // TODO Auto-generated method stub
        int x = position.getX();
        int y = position.getY();
        List<Position> mouvementPossible = new ArrayList<Position>();

        for (int c = -1; c <= 1; c++)    //colonne
        {
            for (int l = -1; l <= 1; l++)    //ligne
            {
                Position temp = new Position(x + l, y + c);
                if ((l != 0 || c != 0) && temp.inBounds() && !this.bloqueAmi(temp)) {

                    mouvementPossible.add(temp.clone());

                }
            }
        }
        mouvementPossible = MouvementAutorises(mouvementPossible);

        return mouvementPossible;
    }

    public List<Position> MouvementAutorises(List<Position> mouvement) {
        List<Position> posPrises;
        List<Position> posEnlevees = new ArrayList<Position>();
        if (couleur.equals(Couleur.WHITE))
            posPrises = posPrisesNoir;
        else
            posPrises = posPrisesBlanc;

        for (Position pos : mouvement) {
            for (Position pos2 : posPrises) {
                if (pos.equals(pos2)) {
                    posEnlevees.add(pos);
                    break;
                }

            }
        }
        mouvement.removeAll(posEnlevees);

        return mouvement;
    }

    public String update() {
        String result = "";
        List<Position> tempTab;
        if (couleur.equals(Couleur.BLACK))
            tempTab = posPrisesBlanc;
        else
            tempTab = posPrisesNoir;
        if (echec)
            echec = false;

        for (Position pos : tempTab) {
            if (pos.equals(position)) {
                echec = true;
                result = "echec";
            }

        }

        if (!echec && this.mouvementExecutable.isEmpty() &&
                Piece.getColoredPiece(couleur).size() == 1) {

            pat = true;
            result = "echec et pat";
        }


        if (echec && this.mouvementExecutable.isEmpty()) {
            result = "echec et mat";
            echecEtMat = true;
        }
        return result;
    }

    public static Roi getRoiCouleur(Couleur c) {
        for (Roi r : Roi.tabRoi) {
            if (r.couleur.equals(c))
                return r;
        }
        return null;
    }

    public static List<Roi> getTabRoi() {
        return tabRoi;
    }

    public static void setTabRoi(List<Roi> tabRoi) {
        Roi.tabRoi = tabRoi;
    }

    public void setEchec(boolean echec) {
        this.echec = echec;
    }

    public void setEchecEtMat(boolean echecEtMat) {
        this.echecEtMat = echecEtMat;
    }


    public boolean isPremierTour() {
        return premierTour;
    }

    public void setPremierTour(boolean premierTour) {
        this.premierTour = premierTour;
    }

    public boolean isPat() {
        return pat;
    }

    public void setPat(boolean pat) {
        this.pat = pat;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "[Roi " + super.position.getX() + "," + super.position.getY() + "]";
    }

}
