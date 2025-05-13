package ship;

import entities.Mazzo;
import entities.PallaCannone;
import ship.components.Componente;

public class Nave {

    private int umani = 2; // numero iniziale di equipaggio per il core della nave
    private int alieni = 0;
    private int livello;
    // #region schemi per ogni nave, serve per controllare se la posizione scelta
    // del componente e' accettabile oppure no
    boolean[][] schemaLVL1 = {
            { false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, true, false, false, false, false, false },
            { false, false, false, false, false, true, true, true, false, false, false, false },
            { false, false, false, false, true, true, true, true, true, false, false, false },
            { false, false, false, false, true, true, true, true, true, false, false, false },
            { false, false, false, false, false, true, true, false, true, true, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false }
    };
    boolean[][] schemaLVL2 = {
            { false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, true, false, true, false, false, false, false },
            { false, false, false, false, true, true, true, true, true, false, false, false },
            { false, false, false, true, true, true, true, true, true, true, false, false },
            { false, false, false, true, true, true, true, true, true, true, false, false },
            { false, false, false, true, true, true, false, true, true, true, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false }
    };
    boolean[][] schemaLVL3 = {
            { false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, true, false, false, false, false, false },
            { false, false, false, false, false, true, true, true, false, false, false, false },
            { false, false, false, false, true, true, true, true, true, false, false, false },
            { false, false, true, true, true, true, true, true, true, true, true, false },
            { false, false, true, true, true, true, true, true, true, true, true, false },
            { false, false, true, true, false, true, true, true, false, true, true, false },
            { false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false },
            { false, false, false, false, false, false, false, false, false, false, false, false }
    };

    boolean[][][] schemi = { schemaLVL1, schemaLVL2, schemaLVL3 };
    // #endregion

    private Componente[][] nave;

    public Nave(int livello) {
        this.livello = livello;
        this.nave = new Componente[12][12]; // Dimensione dimostrativa, cambiare in base alle necessità
    }

    // fase costruzione nave in gioco
    public int costruisciNave(Mazzo<Componente> mazzoComponenti) {
        int risposta;
        Componente c;

        try { // Try per gestire l'eccezione di mazzo vuoto
            c = mazzoComponenti.pescaCarta(); // Pesca un componente dal mazzo
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }

        java.util.Scanner scanner = new java.util.Scanner(System.in);

        do {
            System.out.println("hai pescato: " + c.toString() +
                    ", vuoi tenerlo?\n 1) si\n2) no\n3) termina fase di pesca");
            risposta = scanner.nextInt();
        } while (risposta < 1 || risposta > 3); // Assicurati che la risposta sia valida

        if (risposta == 1) {
            int riga;
            int colonna;
            do {
                System.out.println("Dove vuoi posizionarlo? Inserisci numero riga: ");
                riga = scanner.nextInt();

                System.out.println("Dove vuoi posizionarlo? Inserisci numero colonna: ");
                colonna = scanner.nextInt();

                scanner.nextLine();
            } while (schemi[livello - 1][riga - 1][colonna - 1] == false || nave[riga - 1][colonna - 1] != null);
            nave[riga - 1][colonna - 1] = c; // Posiziona il componente nella matrice
            System.out.println("Componente posizionato in [" + riga + "][" + colonna + "].");
            scanner.close();
            return 0;
        } else if (risposta == 2) {
            mazzoComponenti.aggiungiCarta(c);
            mazzoComponenti.mescola();
            return 0;
        } else if (risposta == 3) {
            return 1;
        }
    return -1;
        
    }

    /*
     * esempio di codice per logica dei turni dei giocatori
     * public class PescaCarte {
     * 
     * public static void main(String[] args) {
     * // Simulazione mazzo di carte
     * Queue<String> mazzo = new LinkedList<>(List.of("Carta1", "Carta2", "Carta3",
     * "Carta4", "Carta5", "Carta6", "Carta7", "Carta8"));
     * 
     * // Quattro giocatori con mano vuota
     * List<List<String>> maniGiocatori = new ArrayList<>();
     * for (int i = 0; i < 4; i++) {
     * maniGiocatori.add(new ArrayList<>());
     * }
     * 
     * // Turni: ogni giocatore pesca a turno finché il mazzo ha carte
     * int turno = 0;
     * while (!mazzo.isEmpty()) {
     * String cartaPescata = mazzo.poll();
     * maniGiocatori.get(turno).add(cartaPescata);
     * System.out.println("Giocatore " + (turno + 1) + " pesca " + cartaPescata);
     * 
     * turno = (turno + 1) % 4; // Passa al prossimo giocatore (ciclo tra 0 e 3)
     * }
     * 
     * // Stampa le mani finali
     * for (int i = 0; i < 4; i++) {
     * System.out.println("Mano del Giocatore " + (i + 1) + ": " +
     * maniGiocatori.get(i));
     * }
     * }
     * }
     */

    public void perdiMerci(int merci) {
        // Logica per perdere merci
    }

    public void perdiEquipaggio(int equipaggio) {
        // Logica per perdere equipaggio
    }

    public void prendiCannonate(PallaCannone[] cannonate) {
        // Logica per prendere cannonate
    }

    public int getPosizione() {
        return 0; // Restituisce la posizione della nave
    }

    public Componente[][] getNave() {
        return nave; // Restituisce la matrice della nave
    }

    public void distruggiComponente(int x, int y) {
        if (nave[x][y] != null) {
            nave[x][y] = null; // Distruggi il componente
        }
    }

    public boolean isEmpty(int x, int y) {
        return nave[x][y] == null; // Controlla se la posizione è vuota
    }

    public int contaConnettori() {
        return 0; // Logica per contare i connettori
    }

    @Override
    public String toString() {
        String s = "La tua nave:\n";
        s += "Equipaggio: " + umani + "\nAlieni: " + alieni + "\n\n";
        for (int i = 0; i < nave.length; i++) {
            for (int j = 0; j < nave[i].length; j++) {
                if (nave[i][j] != null) {
                    s += nave[i][j].toString() + "\t";
                } else {
                    s += "Vuoto\t";
                }
            }
            s += "\n";
        }
        return s;
    }

    public Componente getComponente(int x, int i) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getComponente'");
    }

}
