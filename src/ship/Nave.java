package ship;

import java.util.ArrayList;

import entities.Dimensione;
import entities.Direzione;
import entities.Mazzo;
import entities.PallaCannone;
import ship.components.*;

public class Nave {
    java.util.Scanner scanner = new java.util.Scanner(System.in);

    private int umani = 2; // numero iniziale di equipaggio per il core della nave
    private int alieni = 0;
    private int equipaggio = alieni + umani;
    private int livello;
    private int posizione;
    private int crediti;
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
            { false, false, false, false, true, true, false, true, true, false, false, false },
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

            if (risposta == 16)
                break; // test nave

        } while (risposta < 1 || risposta > 4); // Assicurati che la risposta sia valida

        if (risposta == 1) {
            int riga = -1;
            int colonna = -1;
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

                System.out.println("Dove vuoi posizionarlo? Inserisci numero riga: (0 per annullare)");
                do {
                    if (scanner.hasNextInt()) {
                        riga = scanner.nextInt();
                        if (riga == 0) {
                            System.out.println("Posizionamento annullato.");
                            mazzoComponenti.aggiungiCarta(c);
                            mazzoComponenti.mescola();
                            return 0;
                        }
                    } else {
                        scanner.next(); // consuma l'input non valido
                    }
                } while (riga < 1 || riga > 12);

                System.out.println("Dove vuoi posizionarlo? Inserisci numero colonna: ");
                do {
                    if (scanner.hasNextInt())
                        colonna = scanner.nextInt();
                    else
                        scanner.next(); // consuma l'input non valido
                } while (colonna < 1 || colonna > 12);
                scanner.nextLine();

            } while (schemi[livello - 1][riga - 1][colonna - 1] == false
                    || nave[riga - 1][colonna - 1] != null || !c.posizionabile(this, colonna - 1, riga - 1));
            nave[riga - 1][colonna - 1] = c; // Posiziona il componente nella matrice
            System.out.println("Componente posizionato in [" + riga + "][" + colonna + "].");

            if (c instanceof Cabina) {
                if (c instanceof SupportoVitale)
                    alieni++;
                else
                    umani += 2;
            }

            return 0;
        } else if (risposta == 2) {
            mazzoComponenti.aggiungiCarta(c);
            mazzoComponenti.mescola();
            return 0;
        } else if (risposta == 3) {
            return 1;
        } else if (risposta == 16) {
            Connettori connettori[] = { Connettori.UNIVERSALE, Connettori.UNIVERSALE,
                    Connettori.UNIVERSALE, Connettori.UNIVERSALE };

            Componente cab1 = new Cabina(connettori);
            Componente cab2 = new Cabina(connettori);
            nave[6][7] = cab1;
            nave[7][8] = cab2;
            this.setEquipaggio(this.getEquipaggio() + 4);

            Componente motore1 = new Motore(connettori);
            Componente motore2 = new Motore(connettori);
            Componente motore3 = new Motore(connettori);
            nave[7][6] = motore1;
            nave[8][8] = motore2;
            nave[8][4] = motore3;

            Componente cannone1 = new Cannone(connettori, 1);
            Componente cannone2 = new Cannone(connettori, 1);
            Componente cannone3 = new Cannone(connettori, 1);
            nave[5][6] = cannone1;
            nave[6][5] = cannone2;
            nave[6][8] = cannone3;

            Componente scudo1 = new Scudo(connettori);
            Componente scudo2 = new Scudo(connettori);
            nave[6][4] = scudo1;
            nave[8][7] = scudo2;

            Componente stiva1 = new Stiva(connettori);
            Componente stiva2 = new Stiva(connettori);
            nave[8][5] = stiva1;
            nave[7][7] = stiva2;

            Componente stivaSpeciale1 = new StivaSpeciale(connettori);
            Componente stivaSpeciale2 = new StivaSpeciale(connettori);
            nave[7][5] = stivaSpeciale1;
            nave[7][4] = stivaSpeciale2;

            return 1;
        }
        return -1;

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

    public int contaConnettori() { // Logica per contare i connettori
        int conta = 0;
        for (int i = 3; i < 9; i++) {
            for (int j = 2; j < 11; j++) {
                if (nave[i][j] != null && nave[i - 1][j] == null
                        && nave[i][j].getConnettore(Orientazione.NORD) != Connettori.NIENTE)
                    conta++;
                if (nave[i][j] != null && nave[i][j + 1] == null
                        && nave[i][j].getConnettore(Orientazione.EST) != Connettori.NIENTE)
                    conta++;
                if (nave[i][j] != null && nave[i + 1][j] == null
                        && nave[i][j].getConnettore(Orientazione.SUD) != Connettori.NIENTE)
                    conta++;
                if (nave[i][j] != null && nave[i][j - 1] == null
                        && nave[i][j].getConnettore(Orientazione.OVEST) != Connettori.NIENTE)
                    conta++;
            }
        }
        return conta;
    }

    public String toStringNoLegenda() {
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

                    if (schemi[livello - 1][i][j] == true)
                        c += "o\t";
                    else
                        c += "*\t";

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

                    if (schemi[livello - 1][i][j] == true)
                        c += "o\t";
                    else
                        c += "*\t";

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
        return equipaggio;
    }

    public void setEquipaggio(int equipaggio) {
        this.equipaggio = equipaggio;
    }

    // come parametro passo un tipo classe che ha l'obbligo di estendere componente
    public ArrayList<Componente> getComponentiSpecifici(Class<? extends Componente> componente) {
        ArrayList<Componente> componenti = new ArrayList<Componente>();
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                if (componente.isInstance(nave[i][j])) { // controllo se la classe passata è un'istanza di nave[i][j]
                    componenti.add(nave[i][j]);
                }
            }
        }
        return componenti;
    }

    public int getCrediti() {
        return crediti;
    }

    public void setCrediti(int crediti) {
        this.crediti = crediti;
    }

    public boolean presenzaScudo(Orientazione orientazione) {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                if (nave[i][j] instanceof Scudo)
                    if (nave[i][j].getOrientazione() == orientazione
                            || (nave[i][j].getOrientazione().ordinal() + 1) % 4 == orientazione.ordinal())
                        return true;
            }
        }
        return false;
    }

    public void guadagnaMerci(int[] merciGuadagnate) {
        ArrayList<Componente> componenti = this.getComponentiSpecifici(Stiva.class);
        for (int t = 0; t < merciGuadagnate.length; t++) {
            if (merciGuadagnate[t] == 4) {
                for (int s = 0; s < componenti.size(); s++) {
                    if (componenti.get(s) instanceof StivaSpeciale) {
                        // controlla se in stive speciali c'è posto per mettere merci di livello 4
                        boolean controllo = ((StivaSpeciale) componenti.get(s))
                                .setMerci(merciGuadagnate[t]);
                        if (controllo == true) {
                            merciGuadagnate[t] = -1;
                            break;
                        }
                    }
                }
            } else {
                for (int s = 0; s < componenti.size(); s++) {
                    if (!(componenti.get(s) instanceof StivaSpeciale)) {
                        // controlla se in stive normali c'è posto per mettere merci
                        boolean controllo = ((Stiva) componenti.get(s)).setMerci(merciGuadagnate[t]);
                        if (controllo == true) {
                            merciGuadagnate[t] = -1;
                            break;
                        }
                    }
                }
            }
        }
        for (int t = 0; t < merciGuadagnate.length; t++) {
            // se merci non sono state messe in stive normali controllo se c'è posto in
            // stive speciali
            if (merciGuadagnate[t] != -1) {
                for (int s = 0; s < componenti.size(); s++) {
                    if (componenti.get(s) instanceof StivaSpeciale) {
                        boolean controllo = ((StivaSpeciale) componenti.get(s))
                                .setMerci(merciGuadagnate[t]);
                        if (controllo == true) {
                            merciGuadagnate[t] = -1;
                            break;
                        }
                    }
                }
            }
        }
    }

    public void perdiMerci(int nMerci) {
        ArrayList<Componente> componenti = this.getComponentiSpecifici(Stiva.class);
        int merciRestanti = nMerci;

        for (int t = 0; t < nMerci; t++) {
            for (int s = 0; s < componenti.size(); s++) {
                if (componenti.get(s) instanceof StivaSpeciale) {
                    boolean controllo = ((StivaSpeciale) componenti.get(s)).perdiMerci();
                    if (controllo == true) {
                        merciRestanti--;
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < merciRestanti; i++) {
            for (int s = 0; s < componenti.size(); s++) {
                if (!(componenti.get(s) instanceof StivaSpeciale)) {
                    boolean controllo = ((Stiva) componenti.get(s)).perdiMerci();
                    if (controllo == true)
                        break;
                }
            }
        }
    }

    public void prendiCannonate(PallaCannone[] cannonate) {
        for (int j = 0; j < cannonate.length; j++) {
            Direzione dir = cannonate[j].getDirezione();
            Dimensione dim = cannonate[j].getGrandezza();
            int posizione = (int) (Math.random() * 10) + 2;

            System.out.println("La nave in posizione " + this.getPosizione() + " prende una cannonata di tipo "
                    + (dim == Dimensione.PALLA_CANNONE_PICCOLA ? "piccola" : "grande") + " in direzione " + dir
                    + " in posizione " + (posizione + 1));

            if (dir == Direzione.SOPRA) {
                for (int t = 0; t < 12; t++) {
                    if (this.getComponente(t, posizione) != null) {
                        if (dim == Dimensione.PALLA_CANNONE_GRANDE) {
                            this.distruggiComponente(t, posizione);
                            break; // Esce dal ciclo dopo aver distrutto il primo componente
                        } else if (dim == Dimensione.PALLA_CANNONE_PICCOLA) {
                            if (this.presenzaScudo(Orientazione.NORD) == false) {
                                this.distruggiComponente(t, posizione);
                                System.out.println("la palla di cannone distrugge il componente in [" + (t + 1) + "]["
                                        + (posizione + 1) + "] della nave in posizione " + this.getPosizione());
                                break; // Esce dal ciclo dopo aver distrutto il primo componente
                            } else {
                                System.out.println("la palla di cannone rimbalza sullo scudo della nave in posizione "
                                        + this.getPosizione());
                                break;
                            }
                        }
                    }
                }

            } else if (dir == Direzione.DESTRA) {
                for (int t = 11; t >= 0; t--) {
                    if (this.getComponente(posizione, t) != null) {
                        if (dim == Dimensione.PALLA_CANNONE_GRANDE) {
                            this.distruggiComponente(posizione, t);
                            break; // Esce dal ciclo dopo aver distrutto il primo componente
                        } else if (dim == Dimensione.PALLA_CANNONE_PICCOLA) {
                            if (this.presenzaScudo(Orientazione.EST) == false) {
                                this.distruggiComponente(posizione, t);
                                System.out.println("la palla di cannone distrugge il componente in [" + (t + 1) + "]["
                                        + (posizione + 1) + "] della nave in posizione " + this.getPosizione());
                                break; // Esce dal ciclo dopo aver distrutto il primo componente
                            } else {
                                System.out.println("la palla di cannone rimbalza sullo scudo della nave in posizione "
                                        + this.getPosizione());
                                break;
                            }
                        }
                    }
                }
            } else if (dir == Direzione.SOTTO) {
                for (int t = 11; t >= 0; t--) {
                    if (this.getComponente(t, posizione) != null) {
                        if (dim == Dimensione.PALLA_CANNONE_GRANDE) {
                            this.distruggiComponente(t, posizione);
                            break; // Esce dal ciclo dopo aver distrutto il primo componente
                        } else if (dim == Dimensione.PALLA_CANNONE_PICCOLA) {
                            if (this.presenzaScudo(Orientazione.SUD) == false) {
                                this.distruggiComponente(t, posizione);
                                System.out.println("la palla di cannone distrugge il componente in [" + (t + 1) + "]["
                                        + (posizione + 1) + "] della nave in posizione " + this.getPosizione());
                                break; // Esce dal ciclo dopo aver distrutto il primo componente
                            } else {
                                System.out.println("la palla di cannone rimbalza sullo scudo della nave in posizione "
                                        + this.getPosizione());
                                break;
                            }
                        }
                    }
                }
            } else if (dir == Direzione.SINISTRA) {
                for (int t = 0; t < 12; t++) {
                    if (this.getComponente(posizione, t) != null) {
                        if (dim == Dimensione.PALLA_CANNONE_GRANDE) {
                            this.distruggiComponente(posizione, t);
                            break; // Esce dal ciclo dopo aver distrutto il primo componente
                        } else if (dim == Dimensione.PALLA_CANNONE_PICCOLA) {
                            if (this.presenzaScudo(Orientazione.OVEST) == false) {
                                this.distruggiComponente(posizione, t);
                                System.out.println("la palla di cannone distrugge il componente in [" + (t + 1) + "]["
                                        + (posizione + 1) + "] della nave in posizione " + this.getPosizione());
                                break; // Esce dal ciclo dopo aver distrutto il primo componente
                            } else {
                                System.out.println("la palla di cannone rimbalza sullo scudo della nave in posizione "
                                        + this.getPosizione());
                                break;
                            }
                        }
                    }
                }

            }
        }
    }

}
