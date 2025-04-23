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

    public T pescaCarta() {
        if (carte.isEmpty()) {
            return null;
        }
        return carte.remove(carte.size() - 1);
    }
}