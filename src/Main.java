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

        InizioGioco inizioGioco = new InizioGioco();
        inizioGioco.inizia(giocatoriOrdinati, tabellone, mazzoCarte);
    }

}
