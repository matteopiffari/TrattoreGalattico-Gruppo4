package entities;

import java.util.ArrayList;
import java.util.List;

import ship.Nave;
import ship.components.Componente;

public class Tabellone {
    private Nave[] navi;
    private ArrayList<Giocatore> players;
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
        nave.setPosizione(posizione);
    }

    public Giocatore trovaGiocatore(Nave nave) {
        for (Giocatore giocatore : players) {
            if (giocatore.getNave().equals(nave)) {
                return giocatore;
            }
        }
        return null; // se non trova il giocatore, ritorna null
    }

    public void riordinaGiocatori(ArrayList<Giocatore> giocatori) {
        giocatori.sort((g1, g2) -> Integer.compare(g2.getNave().getPosizione(), g1.getNave().getPosizione()));
        players = giocatori;
    }

    // funzione per lo svolgimento dei turni
    public ArrayList<Giocatore> inizializza(Mazzo<Componente> mazzoComponenti, Giocatore[] giocatori) {
        int pesca = 0;
        boolean continuaAPescare = true;
        ArrayList<Giocatore> giocatoriOrdinati = new ArrayList<Giocatore>();

        while (continuaAPescare) {
            for (int i = 0; i < navi.length; i++) {
                System.out.println(
                        "Pesca il giocatore " + giocatori[i].getColore() + giocatori[i].getNome() + resetColore);
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

        long startTime = System.currentTimeMillis(); // prende il timestamp di adesso

        while (System.currentTimeMillis() - startTime < 110000 && giocatoriOrdinati.size() != giocatori.length) {
            for (int i = 0; i < navi.length; i++) {
                if (!bloccaPesca[i]) {
                    System.out.println(
                            "Pesca il giocatore " + giocatori[i].getColore() + giocatori[i].getNome() + resetColore);
                    pesca = navi[i].costruisciNave(mazzoComponenti);
                    if (pesca == 1) {
                        bloccaPesca[i] = true;
                        giocatoriOrdinati.add(giocatori[i]);
                    }
                }
            }
        }
        if (giocatoriOrdinati.size() != giocatori.length) {
            for (int i = 0; i < giocatori.length; i++) {
                if (!giocatoriOrdinati.contains(giocatori[i]))
                    giocatoriOrdinati.add(giocatori[i]);
            }
        }
        System.out.println("Tempo esaurito!");
        for (int i = 0; i < giocatoriOrdinati.size(); i++) {
            navi[i] = giocatoriOrdinati.get(i).getNave();
        }
        players = giocatoriOrdinati;
        for (int i = 0; i < giocatoriOrdinati.size(); i++) {
            if (i == 0)
                giocatoriOrdinati.get(i).getNave().setPosizione(5);
            else if (i == 1)
                giocatoriOrdinati.get(i).getNave().setPosizione(3);
            else if (i == 2)
                giocatoriOrdinati.get(i).getNave().setPosizione(2);
            else if (i == 3)
                giocatoriOrdinati.get(i).getNave().setPosizione(1);
        }
        return giocatoriOrdinati;
    }

    @Override
    public String toString() {
        String s = "";

        s += "Tabellone:\n\n";
        for (int i = 0; i < players.size(); i++) {
            s += "Posizione " + (i + 1) + ": " + players.get(i).getColore() + players.get(i).getNome() + resetColore
                    + "\n";
        }

        for (int i = 0; i < players.size(); i++) {
            s += "Info di " + players.get(i).getColore() + players.get(i).getNome() + resetColore + "\n - Posizione: " +
                    +players.get(i).getNave().getPosizione() + "\n" + " - Equipaggio: "
                    + players.get(i).getNave().getEquipaggio() + "\n" + players.get(i).getNave().toStringNoLegenda()
                    + "\n\n\n";
        }

        return s;
    }

    public void controllaSconfitte(ArrayList<Giocatore> giocatori) {
        for (int i = 0; i < giocatori.size(); i++) {
            if (giocatori.get(i).getNave().getEquipaggio() <= 0
                    || giocatori.get(i).getNave().getComponente(6, 6) == null) {
                System.out.println(giocatori.get(i).getColore() + giocatori.get(i).getNome() + resetColore
                        + " è stato sconfitto!");
                giocatori.remove(i);
                i--; // decrementa l'indice per compensare la rimozione
            }
        }
    }

    public boolean controllaVittorie(ArrayList<Giocatore> giocatori) {
        if (giocatori.size() == 1) {
            System.out.println(giocatori.get(0).getColore() + giocatori.get(0).getNome() + resetColore
                    + " ha vinto la partita!");
            return true; // un solo giocatore rimasto, partita vinta
        } else if (giocatori.size() == 0) {
            System.out.println("Tutti i giocatori sono stati sconfitti! Nessun vincitore.");
            return true; // tutti i giocatori sono stati sconfitti, partita finita senza vincitore
        }
        return false; // partita non ancora finita
    }

}
