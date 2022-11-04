package Models;

import Models.Pieces.*;
import Models.Plateau.Case;
import Models.Plateau.Couleur;
import Models.Plateau.Echiquier;
import Models.Plateau.Position;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private Joueur joueurActuel;
    private Data data = new Data();
    private int numTour = 1;
    private ArrayList<Joueur> listeJoueurs = new ArrayList<Joueur>();
    private Echiquier echiquier;
    private Scanner entree = new Scanner(System.in);

    boolean over;

    public Game() {

        System.out.println("Veuillez entrez le nom du premier joueur");
        String n1 = entree.nextLine();
        System.out.println("Veuillez entrez le nom du deuxième joueur");
        String n2 = entree.nextLine();
        if(n1 == "") {
            n1 = "Joueur1";
        }
        if(n2 == "") {
            n2 = "Joueur2";
        }
        Joueur j1 = new Joueur(n1, Couleur.WHITE);
        Joueur j2 = new Joueur(n2, Couleur.BLACK);

        listeJoueurs.add(j1);
        listeJoueurs.add(j2);

        for(Joueur j: listeJoueurs){
            data.getListJoueurEnregistrer().add(j.getNom());
        }
        initialisation();

        j1.updateTab();
        j2.updateTab();
        over = false;
        Piece.updateAll();
    }

    private void initialisation() {

        String w;
        do {
            System.out.println("Lancer en mode normal");
            System.out.println(data);
            w = entree.nextLine();
        }
        while (!w.equals("normal"));

        init_pion();

        echiquier = new Echiquier();
        echiquier.initialisation();
    }

    public Joueur getJoueurActuel() {
        return joueurActuel;
    }

    public int getNumTour() {
        return numTour;
    }

    public ArrayList<Joueur> getListeJoueurs() {
        return listeJoueurs;
    }

    public Echiquier getEchiquier() {
        return echiquier;
    }


    public Scanner getEntree() {
        return entree;
    }


    public boolean isFini() {
        return over;
    }
    private void init_pion() {
        int y = 2;
        for (int x = 1; x <= 8; x++) {
            Pion p = new Pion(x, y, Couleur.WHITE);
        }
        y = 7;
        for (int x = 1; x <= 8; x++) {
            Pion p = new Pion(x, y, Couleur.BLACK);
        }

        Tour t1 = new Tour(1, 8, Couleur.BLACK);
        Tour t2 = new Tour(8, 8, Couleur.BLACK);
        Tour t3 = new Tour(1, 1, Couleur.WHITE);
        Tour t4 = new Tour(8, 1, Couleur.WHITE);

        Cavalier C1 = new Cavalier(2, 8, Couleur.BLACK);
        Cavalier c2 = new Cavalier(7, 8, Couleur.BLACK);
        Cavalier c3 = new Cavalier(2, 1, Couleur.WHITE);
        Cavalier c4 = new Cavalier(7, 1, Couleur.WHITE);

        Fou F1 = new Fou(3, 8, Couleur.BLACK);
        Fou f2 = new Fou(6, 8, Couleur.BLACK);
        Fou f3 = new Fou(3, 1, Couleur.WHITE);
        Fou f4 = new Fou(6, 1, Couleur.WHITE);

        Reine r4=new Reine (5,1,Couleur.WHITE);
        Reine r2=new Reine (5,8,Couleur.BLACK);
        Roi r1 = new Roi(4, 8, Couleur.BLACK);
        Roi r3 = new Roi(4, 1, Couleur.WHITE);
    }

    public void lancer() {
        while (!over)
            debutTour();
    }

    public void finDuTour() {

        numTour++;
    }

    public void finDePartie() {
        System.out.println("Fini!");

        System.out.println("Voulez vous rejouez une partie ? (oui ou non)");
        String x;
        do {
            x = entree.nextLine();
        }
        while (x.equals(".") || !(x.equals("oui") || x.equals("non")));
        if (x.equals("oui")) {
            recommencerPartie();
        }
    }

    public void recommencerPartie() {

        Piece.tabPiece.clear();
        Roi.getTabRoi().clear();
        Joueur j = this.listeJoueurs.get(0);
        listeJoueurs.set(0, listeJoueurs.get(1));
        listeJoueurs.set(1, j);
        initialisation();
        over = false;
        Piece.updateAll();
    }

    public void debutTour() {
        this.update();
        if (!over) {

            if (numTour % 2 == 1)
                joueurActuel = this.listeJoueurs.get(0);
            else
                joueurActuel = this.listeJoueurs.get(1);


            System.out.println("C'est à " + joueurActuel.getNom() + " de jouer");
            jouer(joueurActuel);
        } else {
            this.finDePartie();
        }
    }

    private void jouer(Joueur j) {

        Position depart = null;
        Position arrivee = null;
        Piece selectionnee = null;
        Couleur jc = this.joueurActuel.getCouleur();

        boolean temp2 = false;
        while (!temp2) {
            boolean temp = false;
            while (!temp) {
                if (!Roi.getRoiCouleur(jc).isEchec()) {
                    System.out.println(j.getTab());
                    depart = getEntree(1);
                    selectionnee = joueurActuel.bougerT1(depart);
                } else {
                    System.out.println(Roi.getRoiCouleur(jc));
                    depart = getEntree(1);
                    selectionnee = joueurActuel.bougerRoiT1(depart);

                }
                if (selectionnee != null)
                    temp = true;
            }
            arrivee = getEntree(2);
            temp2 = joueurActuel.bougerT2(selectionnee, arrivee);

        }
        Case caseTemp = echiquier.getCase(depart.getX(), depart.getY());
        caseTemp.vider();
        System.out.println(caseTemp.getPosition());

        finDuTour();

    }

    private Position getEntree(int n) {
        String temp = null;
        if (n == 1)
            temp = "Entrez la position de depart (x puis y)";
        if (n == 2)
            temp = "Entrez la position d'arriv�e (x puis y)";


        System.out.println(temp);
        int x, y;
        do {
            x = entree.nextInt();
            y = entree.nextInt();
        }
        while (x == 0 && y == 0);

        System.out.println(x + "  " + y);
        return new Position(x, y);
    }

    private void update() {

        this.listeJoueurs.get(0).updateTab();
        this.listeJoueurs.get(1).updateTab();

        Piece.updateAll();
        echiquier.update();

        for (Roi count : Roi.getTabRoi()) {
            String t;
            t = count.update();


            Couleur coul = count.getCouleur();
            if (count.isEchec() && !count.isEchecEtMat())
                System.out.println("Le roi " + count.getCouleur() + " est en echec");
            else if (count.isEchec() && count.isEchecEtMat() || count.isPat()) {
                over = true;
                System.out.println("Le roi " + count.getCouleur() + " est en " + t + "\n");
                if (coul.equals(Couleur.WHITE))
                    System.out.print("Le joueur " + this.listeJoueurs.get(0));
                else
                    System.out.print("Le joueur " + this.listeJoueurs.get(1));
                System.out.println(" a perdu");

            }

        }

    }
}
