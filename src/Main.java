
import ship.Nave;
import ship.components.*;
import cards.*;
import entities.*;

public class Main {

    public static void main(String[] args) {
        // mazzoComponenti.inizializzaMazzo();
        Nave nave = new Nave();

        /*
         * 
         * --- Esempio di utilizzo del mazzo con tipo generico ---
         * 
         * 
         * Mazzo<Carta> mazzoCarte = new Mazzo<Carta>();
         * Mazzo<Componente> mazzoComponenti = new Mazzo<Componente>();
         * 
         * mazzoCarte.aggiungiCarta(new Pirati());
         * mazzoCarte.aggiungiCarta(new PioggiaMeteoriti());
         * 
         * mazzoComponenti.aggiungiCarta(new Motore());
         * mazzoComponenti.aggiungiCarta(new Cannone());
         * 
         * 
         * Carta cartaPesca = mazzoCarte.pescaCarta();
         * if (cartaPesca != null) {
         * System.out.println("Carta pescata: " +
         * cartaPesca.getClass().getSimpleName());
         * } else {
         * System.out.println("Il mazzo è vuoto.");
         * }
         */

        System.out.println(nave.toString());
    }

}
