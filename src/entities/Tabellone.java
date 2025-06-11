package entities;

import java.util.ArrayList;
import java.util.Comparator;
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

    public void controllaIntegritaNave(ArrayList<Giocatore> giocatori) {
        for (int i = 0; i < giocatori.size(); i++) {
            Nave nave = giocatori.get(i).getNave();

            if (!isNaveIntegra(nave)) {
                System.out.println("La nave del giocatore " + i + " non e' integra!");
                // Qui puoi aggiungere la logica per gestire una nave non integra
                // ad esempio: giocatori.get(i).setNaveIntegra(false);
            } else {
                System.out.println("La nave del giocatore " + i + "  e' integra!");
            }
        }
    }

    private boolean isNaveIntegra(Nave nave) {
        // Assumendo che la nave sia una matrice di componenti
        Componente[][] matriceNave = nave.getNave();
        int righe = matriceNave.length;
        int colonne = matriceNave[0].length;

        // Matrice per tenere traccia dei componenti visitati
        boolean[][] visitati = new boolean[righe][colonne];

        // Verifica che la cabina centrale esista in posizione (6,6)
        if (matriceNave[6][6] == null) {
            return false; // Non c'è cabina centrale
        }

        // Avvia DFS dalla cabina centrale
        dfs(matriceNave, visitati, 6, 6, righe, colonne);

        // Controlla se tutti i componenti esistenti sono stati visitati
        for (int i = 0; i < righe; i++) {
            for (int j = 0; j < colonne; j++) {
                if (matriceNave[i][j] != null && !visitati[i][j]) {
                    return false; // Componente non collegato trovato
                }
            }
        }

        return true; // Tutti i componenti sono collegati
    }

    private void dfs(Componente[][] matrice, boolean[][] visitati, int riga, int col, int righe, int colonne) {
        // Controlla i limiti e se la cella è già visitata o vuota
        if (riga < 0 || riga >= righe || col < 0 || col >= colonne || visitati[riga][col]
                || matrice[riga][col] == null) {
            return;
        }

        // Marca come visitato
        visitati[riga][col] = true;

        // Visita tutti i 4 vicini (su, giù, sinistra, destra)
        dfs(matrice, visitati, riga - 1, col, righe, colonne); // su
        dfs(matrice, visitati, riga + 1, col, righe, colonne); // giù
        dfs(matrice, visitati, riga, col - 1, righe, colonne); // sinistra
        dfs(matrice, visitati, riga, col + 1, righe, colonne); // destra
    }

    public void fineGioco(ArrayList<Giocatore> giocatori) {

        giocatori.sort(Comparator.comparingInt((Giocatore g) -> g.getNave().getCrediti()).reversed()); // prende l'array
                                                                                                       // di giocatori e
                                                                                                       // li compara in
                                                                                                       // base al numero
                                                                                                       // di crediti
                                                                                                       // decrescente,
                                                                                                       // riordinandoli
                                                                                                       // alla fine
        if (giocatori.get(0).getNave().getCrediti() > giocatori.get(1).getNave().getCrediti())
            System.out.println("Vince " + giocatori.get(0).getColore() + giocatori.get(0).getNome() + resetColore);
        else {
            ArrayList<Giocatore> giocatoriInParita = new ArrayList<Giocatore>();
            System.out.println("Giocatori in parita'");
            for (int i = 0; i < giocatori.size() - 1; i++) {
                if (giocatori.get(i).getNave().getCrediti() == giocatori.get(i + 1).getNave().getCrediti()) {
                    if (!giocatoriInParita.contains(giocatori.get(i))) {
                        giocatoriInParita.add(giocatori.get(i));
                    }
                    if (!giocatoriInParita.contains(giocatori.get(i + 1))) {
                        giocatoriInParita.add(giocatori.get(i + 1));
                    }
                }
            }
            for (int i = 0; i < giocatoriInParita.size(); i++) {
                System.out.println(
                        giocatoriInParita.get(i).getColore() + giocatoriInParita.get(i).getNome() + resetColore);
            }
        }
    }

}
