import logica.*;

import ship.components.*;

import cards.*;
import entities.*;

public class Main {

    public static void main(String[] args) {
        Start inizio = new Start();

        Mazzo<Componente> mazzoComponenti = inizio.generaMazzoComponenti();
        Mazzo<Carta> mazzoCarte = inizio.generaMazzoCarte();

        Giocatore giocatori[] = inizio.start();
        Tabellone tabellone = inizio.inizializzaTabellone(giocatori);
        tabellone.inizializza(mazzoComponenti);
    }

}
