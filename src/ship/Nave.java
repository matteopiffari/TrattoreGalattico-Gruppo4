package ship;

import entities.Mazzo;
import entities.PallaCannone;
import ship.components.*;
import logica.Legenda;

public class Nave {
    java.util.Scanner scanner = new java.util.Scanner(System.in);

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
        System.out.print(this.toString());

        try { // Try per gestire l'eccezione di mazzo vuoto
            c = mazzoComponenti.pescaCarta(); // Pesca un componente dal mazzo
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
        
       
        do {
            System.out.println("hai pescato: " + c.toString() +
                    ", vuoi tenerlo?\n1) si\n2) no\n3) termina fase di pesca");

            if (scanner.hasNextInt()) {
                risposta = scanner.nextInt();
            } else {
                scanner.next(); // consuma l'input non valido
                risposta = -1;
            }

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
            } while (schemi[livello - 1][riga - 1][colonna - 1] == false
                    || nave[riga - 1][colonna - 1] != null && c.posizionabile(this, colonna, riga));
            nave[riga - 1][colonna - 1] = c; // Posiziona il componente nella matrice
            System.out.println("Componente posizionato in [" + riga + "][" + colonna + "].");
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
    	System.out.println(
				"LEGENDA: \n" +"BS = BATTERIA SINGOLA \n" +"BT = BATTERIA TRIPLA \n" 
				+ "CA = CABINA \n"+"CC = CABINA CENTRALE \n" +"CN = CANNONE \n" +"CD = CANNONE DOPPIO \n" 
				+"MO = MOTORE \n" + "MD = MOTORE DOPPIO \n" + "SC = SCUDO \n" + "ST = STIVA \n"
				+"SS = STIVA SPECIALE \n" + "SV = SUPPORTO VITALE ALIENO \n" + "TB = TUBI \n"
				+"- = CONNETTORE SINGOLO \n" + "= = CONNETTORE DOPPIO \n" + " # = CONNETTORE UNIVERSALE \n"
				+ "< = DIREZIONE OVEST \n" + "> = DIREZIONE EST \n" + "^ = DIREZIONE NORD \n" + "v = DIREZIONE SUD");
        String s = "La tua nave:\n";
        s += "Equipaggio: " + umani + "\nAlieni: " + alieni + "\n\n";
        for (int i = 3; i < 9; i++) {
        	String cn="";
            String cs="";
            String c="";
            for (int j = 2; j < 11; j++) {		//stampa grafica nave
                if (nave[i][j] != null) {
                	cn += " " + nave[i][j].getConnettoreToString(Orientazione.NORD) + nave[i][j].getOrientazioneToString()+"\t";
                    c += nave[i][j].getConnettoreToString(Orientazione.OVEST )+nave[i][j].toStringAbbreviato() + nave[i][j].getConnettoreToString(Orientazione.EST)+ "\t";
                    cs += " " +nave[i][j].getConnettoreToString(Orientazione.SUD) + "\t";
                } else {
                	cn += "\t";
                    c += "o\t";
                    cs += "\t";
                }
            }
            s += cn + "\n" + c + "\n" + cs + "\n";
            s += "\n";
        }
        return s;
    }


	public Componente getComponente(int y, int x) {
        return nave[y][x];
    }
	
	public void setComponente(int y, int x, Componente componente) {
		nave[y][x]=componente;
	}

    public double getPotenzaFuoco() {
        double potenzaF = 0;
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                if (nave[i][j] instanceof Cannone)
                    potenzaF += ((Cannone) nave[i][j]).getPotenza();
            }
        }
        return potenzaF;
    }

    public int getPotenzaMotrice() {
        int potenzaM = 0;
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                if (nave[i][j] instanceof MotoreDoppio)
                    potenzaM += 2;
                else if (nave[i][j] instanceof Motore)
                    potenzaM += 1;
            }
        }
        return potenzaM;
    }

    public int getEquipaggio() {
        return alieni + umani;
    }

}
