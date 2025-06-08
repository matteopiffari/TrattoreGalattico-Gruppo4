package entities;

import java.util.ArrayList;
import java.util.List;

import ship.Nave;
import ship.components.Componente;

public class Tabellone {
    private Nave[] navi;
    private boolean[] bloccaPesca;
    private final String resetColore = "\u001B[0m"; 

    public Tabellone(Nave[] navi) {
        this.navi = navi;
        bloccaPesca = new boolean[navi.length]; // l'array di bool è settato di default a false
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
    public ArrayList<Giocatore> inizializza(Mazzo<Componente> mazzoComponenti, Giocatore[] giocatori) {
        long startTime = System.currentTimeMillis(); // prende il timestamp di adesso
        int pesca = 0;
        boolean continuaAPescare = true;
        ArrayList<Giocatore> giocatoriOrdinati = new ArrayList<Giocatore>();

        while (continuaAPescare) {
            for (int i = 0; i < navi.length; i++) {
                System.out.println("Pesca il giocatore " + giocatori[i].getColore() + giocatori[i].getNome()+ resetColore);
                pesca = navi[i].costruisciNave(mazzoComponenti);
                if (pesca == 1) {
                    bloccaPesca[i] = true;
                    giocatoriOrdinati.add(giocatori[i]);
                }
                    
                

            }
            for (int i = 0; i < navi.length; i++) {
                if (bloccaPesca[i])
                    continuaAPescare = false;
            }
        }

        while (System.currentTimeMillis() - startTime < 110000 && giocatoriOrdinati.size()!=giocatori.length) {
            for (int i = 0; i < navi.length; i++) {
                if (!bloccaPesca[i]){
                    System.out.println("Pesca il giocatore " + giocatori[i].getColore() + giocatori[i].getNome()+ resetColore);
                    pesca=navi[i].costruisciNave(mazzoComponenti);
                    if (pesca==1){
                        bloccaPesca[i]=true;
                        giocatoriOrdinati.add(giocatori[i]);
                    }  
                }
            }
        }
        if (giocatoriOrdinati.size()!=giocatori.length)
        {
            for (int i=0; i<giocatori.length; i++)
            {
                if (!giocatoriOrdinati.contains(giocatori[i]))
                    giocatoriOrdinati.add(giocatori[i]);
            }
        }
        System.out.println("Tempo esaurito!");
        for (int i=0; i<giocatoriOrdinati.size(); i++) {
        	navi[i]=giocatoriOrdinati.get(i).getNave();
        }
        return giocatoriOrdinati;
    }
    
}
