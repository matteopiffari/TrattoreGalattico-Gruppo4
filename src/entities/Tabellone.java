package entities;

import ship.Nave;
import ship.components.Componente;

public class Tabellone {
    private Nave[] navi;
    private boolean[] bloccaPesca;

    public Tabellone(int numNavi) {
        navi = new Nave[numNavi];
        bloccaPesca = new boolean[numNavi]; // l'array di bool è settato di default a false
    }

    public Nave minorPotenzaFuoco() {
        Nave naveMin = null;
        for (Nave nave : navi) { // per ogni elemento dell'array di navi controlla quale ha il minimo di potenza
                                 // di fuoco
            if (naveMin == null || nave.getPotenzaFuoco() < naveMin.getPotenzaFuoco()) {
                naveMin = nave;
            }
        }
        return naveMin;
    }

    public Nave minorPotenzaMotrice() {
        Nave naveMin = null;
        for (Nave nave : navi) {
            if (naveMin == null || nave.getPotenzaMotrice() < naveMin.getPotenzaMotrice()) {
                naveMin = nave;
            }
        }
        return naveMin;
    }

    public Nave minorEquipaggio() {
        Nave naveMin = null;
        for (Nave nave : navi) {
            if (naveMin == null || nave.getEquipaggio() < naveMin.getEquipaggio()) {
                naveMin = nave;
            }
        }
        return naveMin;
    }

    public void setPosizione(Nave nave, int posizione) {
        // Implementazione per impostare la posizione della nave
    }

    // funzione per lo svolgimento dei turni
    public void inizializza(Mazzo<Componente> mazzoComponenti) {
        long startTime = System.currentTimeMillis(); // prende il timestamp di adesso
        int pesca = 0;
        boolean continuaAPescare = true;

        while (continuaAPescare) {
            for (int i = 0; i < navi.length; i++) {
                pesca = navi[i].costruisciNave(mazzoComponenti);
                if (pesca == 1)
                    bloccaPesca[i] = true;
            }
            for (int i = 0; i < navi.length; i++) {
                if (bloccaPesca[i])
                    continuaAPescare = false;
            }
        }

        while (System.currentTimeMillis() - startTime < 110000) {
            for (int i = 0; i < navi.length; i++) {
                if (!bloccaPesca[i])
                    navi[i].costruisciNave(mazzoComponenti);
            }
        }
        System.out.println("Tempo esaurito!");
    }

}
