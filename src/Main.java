import logica.*;

import ship.components.*;

import java.util.ArrayList;

import cards.*;
import entities.*;

public class Main {

    public static void main(String[] args) {
        Start inizio = new Start();

        Mazzo<Componente> mazzoComponenti = inizio.generaMazzoComponenti();
        Mazzo<Carta> mazzoCarte = inizio.generaMazzoCarte();

        Giocatore giocatori[] = inizio.start();
        Tabellone tabellone = inizio.inizializzaTabellone(giocatori);
        ArrayList<Giocatore> giocatoriOrdinati=tabellone.inizializza(mazzoComponenti, giocatori);
        for (int i=0; i<giocatoriOrdinati.size(); i++){
            System.out.println(giocatoriOrdinati.get(i).getNome());
        }

        InizioGioco inizioGioco = new InizioGioco();
        inizioGioco.inizia(giocatori, tabellone, mazzoCarte);
    }

}
