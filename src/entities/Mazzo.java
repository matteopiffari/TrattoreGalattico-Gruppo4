package entities;

import java.util.*;

public class Mazzo<T> {
    private List<T> carte;

    public Mazzo() {
        carte = new ArrayList<>();
    }

    public void aggiungiCarta(T carta) {
        carte.add(carta);
    }

    public void mescola() {
        Collections.shuffle(carte);
    }

    public T pescaCarta() throws Exception {
        if (carte.isEmpty()) {
            throw new Exception("Il mazzo è vuoto.");
        }
        return carte.remove(carte.size() - 1);
    }
}