package logica;

import java.util.ArrayList;

import entities.Giocatore;
import entities.Mazzo;
import ship.components.*;
import cards.*;
import entities.Tabellone;
import ship.Nave;

public class Start {
    private int lvl = 1;

    public Giocatore[] start() {
        java.util.Scanner scanner = new java.util.Scanner(System.in); // il comando di chiusura dello scanner e'
                                                                      // universale per il codice!!
        int numGiocatori = 0;
        int colore = 0;
        Giocatore giocatori[];
        ArrayList<Integer> coloriUsati;
        // sezione input dati utente
        System.out.println("Benvenuto in Space Game!");
        System.out.println("Scegli la difficoltà: \n1=facile \n2=medio \n3=difficile");
        do {
            lvl = scanner.nextInt();
        } while (lvl < 1 || lvl > 3); // accetta soltanto un numero giusto di lvl'

        System.out.println("Quanti giocatori siete?");

        do {
            if (scanner.hasNextInt())
                numGiocatori = scanner.nextInt();
            else
                scanner.next();
        } while (numGiocatori < 2 || numGiocatori > 4); // accetta soltanto un numero giusto di player

        giocatori = new Giocatore[numGiocatori];
        coloriUsati = new ArrayList<Integer>();

        for (int i = 0; i < numGiocatori; i++) {
            do {
                do {
                    System.out.println(
                            "Inserire un colore compreso tra 1 e 4 \n1 =\u001B[31m rosso \u001B[0m \n2 =\u001B[34m blu \u001B[0m \n3 =\u001B[33m giallo \u001B[0m \n4 =\u001B[32m verde \u001B[0m");
                    if (scanner.hasNextInt())
                        colore = scanner.nextInt();
                    else
                        scanner.next();
                } while (colore < 1 || colore > 4);
            } while (coloriUsati.contains(colore));

            System.out.println("Inserisci un nome");
            scanner.nextLine(); // Consuma il newline rimasto
            String nome = scanner.nextLine();
            if (colore == 1) {
                coloriUsati.add(1); // logica per non avere colori uguali tra i giocatori
                giocatori[i] = new Giocatore(nome, "\u001B[31m", lvl);
            } else if (colore == 2) {
                coloriUsati.add(2);
                giocatori[i] = new Giocatore(nome, "\u001B[34m", lvl);
            } else if (colore == 3) {
                coloriUsati.add(3);
                giocatori[i] = new Giocatore(nome, "\u001B[33m", lvl);
            } else if (colore == 4) {
                coloriUsati.add(4);
                giocatori[i] = new Giocatore(nome, "\u001B[32m", lvl);
            }

        }

        return giocatori;
    }

    private boolean allNullConnettori(Connettori[] conn) {
        for (Connettori c : conn) {
            if (c != null) {
                return false;
            }
        }
        return true;
    }

    // #region logica generazione mazzo componenti
    public Mazzo<Componente> generaMazzoComponenti() {
        Mazzo<Componente> mazzoComponenti = new Mazzo<Componente>();
        for (int i = 0; i < 8; i++) {
            Connettori conn[] = new Connettori[4];
            for (int j = 0; j < conn.length; j++) {
                conn[j] = Connettori.values()[(int) (Math.random() * 4)];
            }

            // check if there is at least one connector that is not null
            while (allNullConnettori(conn)) {
                for (int j = 0; j < conn.length; j++) {
                    conn[j] = Connettori.values()[(int) (Math.random() * 4)];
                }
            }

            Tubi carta = new Tubi(conn);
            carta.setOrientazione(Orientazione.values()[(int) (Math.random() * 4)]);
            mazzoComponenti.aggiungiCarta(carta);
        }
        for (int i = 0; i < 25; i++) {
            Connettori conn[] = new Connettori[4];
            Orientazione o = Orientazione.values()[(int) (Math.random() * 4)];

            for (int j = 0; j < conn.length; j++) {
                conn[j] = Connettori.values()[(int) (Math.random() * 4)];
            }

            // check if there is at least one connector that is not null
            while (allNullConnettori(conn)) {
                for (int j = 0; j < conn.length; j++) {
                    conn[j] = Connettori.values()[(int) (Math.random() * 4)];
                }
            }

            if (o == Orientazione.NORD)
                conn[0] = Connettori.NIENTE;
            else if (o == Orientazione.EST)
                conn[1] = Connettori.NIENTE;
            else if (o == Orientazione.SUD)
                conn[2] = Connettori.NIENTE;
            else if (o == Orientazione.OVEST)
                conn[3] = Connettori.NIENTE;

            Cannone carta = new Cannone(conn, o == Orientazione.NORD ? 1 : 0.5);
            carta.setOrientazione(o);
            mazzoComponenti.aggiungiCarta(carta);
        }
        for (int i = 0; i < 11; i++) {
            Connettori conn[] = new Connettori[4];
            Orientazione o = Orientazione.values()[(int) (Math.random() * 4)];

            for (int j = 0; j < conn.length; j++) {
                conn[j] = Connettori.values()[(int) (Math.random() * 4)];
            }

            // check if there is at least one connector that is not null
            while (allNullConnettori(conn)) {
                for (int j = 0; j < conn.length; j++) {
                    conn[j] = Connettori.values()[(int) (Math.random() * 4)];
                }
            }

            if (o == Orientazione.NORD)
                conn[0] = Connettori.NIENTE;
            else if (o == Orientazione.EST)
                conn[1] = Connettori.NIENTE;
            else if (o == Orientazione.SUD)
                conn[2] = Connettori.NIENTE;
            else if (o == Orientazione.OVEST)
                conn[3] = Connettori.NIENTE;

            CannoneDoppio carta = new CannoneDoppio(conn, o == Orientazione.NORD ? 2 : 1);
            carta.setOrientazione(o);
            mazzoComponenti.aggiungiCarta(carta);
        }
        for (int i = 0; i < 21; i++) {
            Connettori conn[] = new Connettori[4];
            conn[0] = Connettori.values()[(int) (Math.random() * 4)];
            conn[1] = Connettori.values()[(int) (Math.random() * 4)];
            conn[3] = Connettori.values()[(int) (Math.random() * 4)];

            // check if there is at least one connector that is not null
            boolean hasNull = true;
            while (hasNull) {
                for (Connettori c : conn) {
                    if (c != null) {
                        hasNull = false;
                        break;
                    }
                }
                if (hasNull) {
                    conn[0] = Connettori.values()[(int) (Math.random() * 4)];
                    conn[1] = Connettori.values()[(int) (Math.random() * 4)];
                    conn[3] = Connettori.values()[(int) (Math.random() * 4)];
                }
            }

            Motore carta = new Motore(conn);
            carta.setOrientazione(Orientazione.SUD);
            mazzoComponenti.aggiungiCarta(carta);
        }
        for (int i = 0; i < 9; i++) {
            Connettori conn[] = new Connettori[4];
            conn[0] = Connettori.values()[(int) (Math.random() * 4)];
            conn[1] = Connettori.values()[(int) (Math.random() * 4)];
            conn[3] = Connettori.values()[(int) (Math.random() * 4)];

            // check if there is at least one connector that is not null
            boolean hasNull = true;
            while (hasNull) {
                for (Connettori c : conn) {
                    if (c != null) {
                        hasNull = false;
                        break;
                    }
                }
                if (hasNull) {
                    conn[0] = Connettori.values()[(int) (Math.random() * 4)];
                    conn[1] = Connettori.values()[(int) (Math.random() * 4)];
                    conn[3] = Connettori.values()[(int) (Math.random() * 4)];
                }
            }

            MotoreDoppio carta = new MotoreDoppio(conn);
            carta.setOrientazione(Orientazione.SUD);
            mazzoComponenti.aggiungiCarta(carta);
        }
        for (int i = 0; i < 17; i++) {
            Connettori conn[] = new Connettori[4];
            for (int j = 0; j < conn.length; j++) {
                conn[j] = Connettori.values()[(int) (Math.random() * 4)];
            }

            // check if there is at least one connector that is not null
            while (allNullConnettori(conn)) {
                for (int j = 0; j < conn.length; j++) {
                    conn[j] = Connettori.values()[(int) (Math.random() * 4)];
                }
            }

            Cabina carta = new Cabina(conn);
            carta.setOrientazione(Orientazione.values()[(int) (Math.random() * 4)]);
            mazzoComponenti.aggiungiCarta(carta);
        }
        for (int i = 0; i < 15; i++) {
            Connettori conn[] = new Connettori[4];
            for (int j = 0; j < conn.length; j++) {
                conn[j] = Connettori.values()[(int) (Math.random() * 4)];
            }

            // check if there is at least one connector that is not null
            while (allNullConnettori(conn)) {
                for (int j = 0; j < conn.length; j++) {
                    conn[j] = Connettori.values()[(int) (Math.random() * 4)];
                }
            }

            Stiva carta = new Stiva(conn);
            carta.setOrientazione(Orientazione.values()[(int) (Math.random() * 4)]);
            mazzoComponenti.aggiungiCarta(carta);
        }
        for (int i = 0; i < 9; i++) {
            Connettori conn[] = new Connettori[4];
            for (int j = 0; j < conn.length; j++) {
                conn[j] = Connettori.values()[(int) (Math.random() * 4)];
            }

            // check if there is at least one connector that is not null
            while (allNullConnettori(conn)) {
                for (int j = 0; j < conn.length; j++) {
                    conn[j] = Connettori.values()[(int) (Math.random() * 4)];
                }
            }

            StivaSpeciale carta = new StivaSpeciale(conn);
            carta.setOrientazione(Orientazione.values()[(int) (Math.random() * 4)]);
            mazzoComponenti.aggiungiCarta(carta);
        }
        for (int i = 0; i < 6; i++) {
            Connettori conn[] = new Connettori[4];
            for (int j = 0; j < conn.length; j++) {
                conn[j] = Connettori.values()[(int) (Math.random() * 4)];
            }

            // check if there is at least one connector that is not null
            while (allNullConnettori(conn)) {
                for (int j = 0; j < conn.length; j++) {
                    conn[j] = Connettori.values()[(int) (Math.random() * 4)];
                }
            }

            SupportoVitale carta = new SupportoVitale(conn, SupportoVitale.TipoSupporto.CANNONE);
            carta.setOrientazione(Orientazione.values()[(int) (Math.random() * 4)]);
            mazzoComponenti.aggiungiCarta(carta);
        }
        for (int i = 0; i < 6; i++) {
            Connettori conn[] = new Connettori[4];
            for (int j = 0; j < conn.length; j++) {
                conn[j] = Connettori.values()[(int) (Math.random() * 4)];
            }

            // check if there is at least one connector that is not null
            while (allNullConnettori(conn)) {
                for (int j = 0; j < conn.length; j++) {
                    conn[j] = Connettori.values()[(int) (Math.random() * 4)];
                }
            }

            SupportoVitale carta = new SupportoVitale(conn, SupportoVitale.TipoSupporto.MOTORE);
            carta.setOrientazione(Orientazione.values()[(int) (Math.random() * 4)]);
            mazzoComponenti.aggiungiCarta(carta);
        }
        for (int i = 0; i < 6; i++) {
            Connettori conn[] = new Connettori[4];
            for (int j = 0; j < conn.length; j++) {
                conn[j] = Connettori.values()[(int) (Math.random() * 4)];
            }

            // check if there is at least one connector that is not null
            while (allNullConnettori(conn)) {
                for (int j = 0; j < conn.length; j++) {
                    conn[j] = Connettori.values()[(int) (Math.random() * 4)];
                }
            }

            BatteriaTripla carta = new BatteriaTripla(conn, 3);
            carta.setOrientazione(Orientazione.values()[(int) (Math.random() * 4)]);
            mazzoComponenti.aggiungiCarta(carta);
        }
        for (int i = 0; i < 11; i++) {
            Connettori conn[] = new Connettori[4];
            for (int j = 0; j < conn.length; j++) {
                conn[j] = Connettori.values()[(int) (Math.random() * 4)];
            }

            // check if there is at least one connector that is not null
            while (allNullConnettori(conn)) {
                for (int j = 0; j < conn.length; j++) {
                    conn[j] = Connettori.values()[(int) (Math.random() * 4)];
                }
            }

            Batteria carta = new Batteria(conn, 2);
            carta.setOrientazione(Orientazione.values()[(int) (Math.random() * 4)]);
            mazzoComponenti.aggiungiCarta(carta);
        }
        for (int i = 0; i < 8; i++) {
            Connettori conn[] = new Connettori[4];
            for (int j = 0; j < conn.length; j++) {
                conn[j] = Connettori.values()[(int) (Math.random() * 4)];
            }

            // check if there is at least one connector that is not null
            while (allNullConnettori(conn)) {
                for (int j = 0; j < conn.length; j++) {
                    conn[j] = Connettori.values()[(int) (Math.random() * 4)];
                }
            }

            Scudo carta = new Scudo(conn);
            carta.setOrientazione(Orientazione.values()[(int) (Math.random() * 4)]);
            mazzoComponenti.aggiungiCarta(carta);
        }
        // #endregion
        mazzoComponenti.mescola();
        return mazzoComponenti;
    }

    public Mazzo<Carta> generaMazzoCarte() {
        Mazzo<Carta> mazzoCarte = new Mazzo<Carta>();
        if (lvl >= 1) {
            for (int i = 0; i < 4; i++) {
                mazzoCarte.aggiungiCarta(new SpazioAperto());
            }
            mazzoCarte.aggiungiCarta(new ZonaGuerra());
            mazzoCarte.aggiungiCarta(new Schiavisti(lvl));
            mazzoCarte.aggiungiCarta(new Pirati(lvl));
            mazzoCarte.aggiungiCarta(new Contrabbandieri(lvl));
            mazzoCarte.aggiungiCarta(new PolvereStellare());
            for (int i = 0; i < 3; i++) {
                mazzoCarte.aggiungiCarta(new PioggiaMeteoriti(lvl));
            }
            for (int i = 0; i < 2; i++) {
                mazzoCarte.aggiungiCarta(new NaveAbbandonata(lvl));
            }
            for (int i = 0; i < 4; i++) {
                mazzoCarte.aggiungiCarta(new Pianeti(lvl));
            }
            for (int i = 0; i < 2; i++) {
                mazzoCarte.aggiungiCarta(new StazioneAbbandonata(lvl));
            }
        }
        if (lvl >= 2) {
            for (int i = 0; i < 3; i++) {
                mazzoCarte.aggiungiCarta(new SpazioAperto());
            }
            mazzoCarte.aggiungiCarta(new ZonaGuerra());
            mazzoCarte.aggiungiCarta(new Schiavisti(lvl));
            mazzoCarte.aggiungiCarta(new Pirati(lvl));
            mazzoCarte.aggiungiCarta(new Contrabbandieri(lvl));
            mazzoCarte.aggiungiCarta(new PolvereStellare());
            mazzoCarte.aggiungiCarta(new Epidemia());
            for (int i = 0; i < 3; i++) {
                mazzoCarte.aggiungiCarta(new PioggiaMeteoriti(lvl));
            }
            for (int i = 0; i < 2; i++) {
                mazzoCarte.aggiungiCarta(new NaveAbbandonata(lvl));
            }
            for (int i = 0; i < 4; i++) {
                mazzoCarte.aggiungiCarta(new Pianeti(lvl));
            }
            for (int i = 0; i < 2; i++) {
                mazzoCarte.aggiungiCarta(new StazioneAbbandonata(lvl));
            }
        }
        if (lvl == 3) {
            for (int i = 0; i < 3; i++) {
                mazzoCarte.aggiungiCarta(new SpazioAperto());
            }
            mazzoCarte.aggiungiCarta(new ZonaGuerra());
            mazzoCarte.aggiungiCarta(new Schiavisti(lvl));
            mazzoCarte.aggiungiCarta(new Pirati(lvl));
            mazzoCarte.aggiungiCarta(new Contrabbandieri(lvl));
            mazzoCarte.aggiungiCarta(new Sabotaggio());
            mazzoCarte.aggiungiCarta(new Epidemia());
            for (int i = 0; i < 3; i++) {
                mazzoCarte.aggiungiCarta(new PioggiaMeteoriti(lvl));
            }
            for (int i = 0; i < 2; i++) {
                mazzoCarte.aggiungiCarta(new NaveAbbandonata(lvl));
            }
            for (int i = 0; i < 4; i++) {
                mazzoCarte.aggiungiCarta(new Pianeti(lvl));
            }
            for (int i = 0; i < 2; i++) {
                mazzoCarte.aggiungiCarta(new StazioneAbbandonata(lvl));
            }
        }
        mazzoCarte.mescola();
        return mazzoCarte;
    }

    public Tabellone inizializzaTabellone(Giocatore[] giocatori) {
        Nave navi[] = new Nave[giocatori.length];
        for (int i = 0; i < giocatori.length; i++) { // crea un array di navi e assegna a ciascun giocatore una nave
            navi[i] = giocatori[i].getNave();
        }

        return new Tabellone(navi);
    }
}