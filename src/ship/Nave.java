package ship;

import java.util.ArrayList;

import entities.Mazzo;
import entities.PallaCannone;
import ship.components.*;

public class Nave {
    java.util.Scanner scanner = new java.util.Scanner(System.in);

    private int umani = 2; // numero iniziale di equipaggio per il core della nave
    private int alieni = 0;
    private int livello;
    private int posizione;
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
            String cn = "";
            String cs = "";
            String co = "";
            cn += " " + c.getConnettoreToString(Orientazione.NORD) + c.getOrientazioneToString();
            co += c.getConnettoreToString(Orientazione.OVEST) + c.toStringAbbreviato()
                    + c.getConnettoreToString(Orientazione.EST);
            cs += " " + c.getConnettoreToString(Orientazione.SUD);

            System.out.println("hai pescato: " + c.toString() + "\n" + cn + "\n" + co + "\n" + cs + "\n" +
                    "vuoi tenerlo?\n1) si\n2) no\n3) termina fase di pesca");

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
            String r;
            while (c instanceof Ruotabile) {
                System.out.println("Vuoi ruotarlo? (s/n)");
                scanner.nextLine();

                r = scanner.nextLine();
                if (r.equals("s")) {
                    ((Ruotabile) c).rotate();
                } else
                    break;
                String cn = "";
                String cs = "";
                String co = "";
                cn += " " + c.getConnettoreToString(Orientazione.NORD) + c.getOrientazioneToString();
                co += c.getConnettoreToString(Orientazione.OVEST) + c.toStringAbbreviato()
                        + c.getConnettoreToString(Orientazione.EST);
                cs += " " + c.getConnettoreToString(Orientazione.SUD);
                System.out.println(cn + "\n" + co + "\n" + cs + "\n");
            }
            do {

                System.out.println("Dove vuoi posizionarlo? Inserisci numero riga: ");
                riga = scanner.nextInt();

                System.out.println("Dove vuoi posizionarlo? Inserisci numero colonna: ");
                colonna = scanner.nextInt();

                scanner.nextLine();

            } while (schemi[livello - 1][riga - 1][colonna - 1] == false
                    || nave[riga - 1][colonna - 1] != null || !c.posizionabile(this, colonna - 1, riga - 1));
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
        return posizione; // Restituisce la posizione della nave
    }
    
    public void setPosizione(int posizione) {
		this.posizione = posizione;
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
                "LEGENDA: \n" + "BS = BATTERIA SINGOLA \n" + "BT = BATTERIA TRIPLA \n"
                        + "CA = CABINA \n" + "CC = CABINA CENTRALE \n" + "CN = CANNONE \n" + "CD = CANNONE DOPPIO \n"
                        + "MO = MOTORE \n" + "MD = MOTORE DOPPIO \n" + "SC = SCUDO \n" + "ST = STIVA \n"
                        + "SS = STIVA SPECIALE \n" + "SV = SUPPORTO VITALE ALIENO \n" + "TB = TUBI \n"
                        + "- = CONNETTORE SINGOLO \n" + "= = CONNETTORE DOPPIO \n" + " # = CONNETTORE UNIVERSALE \n"
                        + "< = DIREZIONE OVEST \n" + "> = DIREZIONE EST \n" + "^ = DIREZIONE NORD \n"
                        + "v = DIREZIONE SUD");
        String s = "La tua nave:\n";
        s += "Equipaggio: " + umani + "\nAlieni: " + alieni + "\n\n";
        for (int i = 3; i < 9; i++) {
            String cn = "";
            String cs = "";
            String c = i + 1 + ":\t";
            for (int j = 2; j < 11; j++) { // stampa grafica nave
                if (nave[i][j] != null) {
                    cn += "\t " + nave[i][j].getConnettoreToString(Orientazione.NORD)
                            + nave[i][j].getOrientazioneToString();
                    c += nave[i][j].getConnettoreToString(Orientazione.OVEST) + nave[i][j].toStringAbbreviato()
                            + nave[i][j].getConnettoreToString(Orientazione.EST) + "\t";
                    cs += "\t " + nave[i][j].getConnettoreToString(Orientazione.SUD);
                } else {
                    if (i == 3 && j == 2)
                        cn += "\t";
                    if (i == 3)
                        cn += +(j + 1);
                    cn += "\t";
                    c += "o\t";
                    cs += "\t";
                }
            }
            if (i == 3)
                cn += "\n";
            s += cn + "\n" + c + "\n" + cs + "\n";
            s += "\n";
        }
        return s;
    }

    public Componente getComponente(int riga, int colonna) {
        return nave[riga][colonna];
    }

    public void setComponente(int riga, int colonna, Componente componente) {
        nave[riga][colonna] = componente;
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
    
    //come parametro passo un tipo classe che ha l'obbligo di estendere componente
    public ArrayList<Componente> getComponentiSpecifici(Class<? extends Componente> componente) {
    	ArrayList<Componente> componenti= new ArrayList<Componente>();
    	 for (int i = 0; i < 12; i++) {
             for (int j = 0; j < 12; j++) {
            	 if(componente.isInstance(nave[i][j])) { //controllo se la classe passata è un'istanza di nave[i][j]
            		 componenti.add(nave[i][j]);
            	 }
             }
    	 }
    	 return componenti;
    }

	

}
