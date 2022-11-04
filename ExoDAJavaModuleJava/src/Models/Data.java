package Models;


import java.util.ArrayList;
import java.util.List;


public class Data {

    public List<String> ListJoueurEnregistrer = new ArrayList<String>();

    public List<String> getListJoueurEnregistrer() {
        return ListJoueurEnregistrer;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("\n\nJoueurs ayant déjà jouer aux echecs:\n");
        for(int i = 0; i < ListJoueurEnregistrer.size(); i++ ){
            s.append(i+1).append(": ").append(ListJoueurEnregistrer.get(i)).append("\n");
        }
        return s.toString();
    }
}
