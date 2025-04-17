package entities;

import java.util.ArrayList;

import ship.components.Componente;


public abstract class Mazzo<T> {
    public static Mazzo<Componente> mazzoComponenti = new MazzoComponenti();
    protected ArrayList<T> mazzo = new ArrayList<T>();
    
    // Costruttore della classe
    public Mazzo() {
       
    }

    public T pesca() {
        if (mazzo.isEmpty()) {
            return null; // o lancia un'eccezione, a seconda della logica del tuo gioco
        }
        return mazzo.remove(mazzo.size() - 1); // Pesca l'ultimo elemento
    }
}