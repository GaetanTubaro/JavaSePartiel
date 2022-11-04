
package Models.Pieces;


import Interfaces.Mouvement;
import Models.Plateau.Couleur;
import Models.Plateau.Position;

import java.util.ArrayList;
import java.util.List;

public class Reine extends Piece implements Mouvement {

    public Reine(int x, int y, Couleur c) {
        super(x, y, c);
    }

    @Override
    public List<Position> getMouvementPossible() {
        int x = position.getX();
        int y = position.getY();
        Position temp = new Position(x, y);
        List<Position> mouvementPossible = new ArrayList<Position>();

        for (int c = 1; c < 8; c++) {
            temp.setX(x + c);
            temp.setY(y + c);
            if (temp.inBounds() && !this.bloqueAmi(temp)) {
                mouvementPossible.add(temp.clone());
                if (this.bloqueEnnemi(temp));
            }
        }
        for (int c = 1; c < 8; c++) {
            temp.setX(x - c);
            temp.setY(y + c);
            if (temp.inBounds() && !this.bloqueAmi(temp)) {
                mouvementPossible.add(temp.clone());
                if (this.bloqueEnnemi(temp));
            }
        }
        for (int c = 1; c < 8; c++) {
            temp.setX(x - c);
            temp.setY(y - c);
            if (temp.inBounds() && !this.bloqueAmi(temp)) {
                mouvementPossible.add(temp.clone());
                if (this.bloqueEnnemi(temp));
            }
        }
        for (int c = 1; c < 8; c++) {
            temp.setX(x + c);
            temp.setY(y - c);
            if (temp.inBounds() && !this.bloqueAmi(temp)) {
                mouvementPossible.add(temp.clone());
                if (this.bloqueEnnemi(temp));
            }
        }
        for (int c = 1; c < 8; c++) {
            temp.setX(x + c);
            temp.setY(y);
            if (temp.inBounds() && !this.bloqueAmi(temp)) {
                mouvementPossible.add(temp.clone());
                if (this.bloqueEnnemi(temp));
            };
        }
        for (int c = 1; c < 8; c++) {
            temp.setX(x - c);
            temp.setY(y);
            if (temp.inBounds() && !this.bloqueAmi(temp)) {
                mouvementPossible.add(temp.clone());
                if (this.bloqueEnnemi(temp));
            }
        }
        for (int c = 1; c < 8; c++) {
            temp.setX(x);
            temp.setY(y + c);
            if (temp.inBounds() && !this.bloqueAmi(temp)) {
                mouvementPossible.add(temp.clone());
                if (this.bloqueEnnemi(temp));
            }
        }
        for (int c = 1; c < 8; c++) {
            temp.setX(x);
            temp.setY(y - c);
            if (temp.inBounds() && !this.bloqueAmi(temp)) {
                mouvementPossible.add(temp.clone());
                if (this.bloqueEnnemi(temp));
            }
        }
        return mouvementPossible;
    }
    @Override
    public String toString() {

        return "[Reine " + super.position.getX() + "," + super.position.getY() + "]";
    }
}
