
import ship.Nave;
import ship.components.*;

import java.net.ConnectException;

import cards.*;
import entities.*;

public class Main {

    public static void main(String[] args) {
        // mazzoComponenti.inizializzaMazzo();
        Nave nave = new Nave();

        /*
         * 
         * --- Esempio di utilizzo del mazzo con tipo generico ---
         * 
         * 
         * Mazzo<Carta> mazzoCarte = new Mazzo<Carta>();
         * Mazzo<Componente> mazzoComponenti = new Mazzo<Componente>();
         * 
         * mazzoCarte.aggiungiCarta(new Pirati());
         * mazzoCarte.aggiungiCarta(new PioggiaMeteoriti());
         * 
         * mazzoComponenti.aggiungiCarta(new Motore());
         * mazzoComponenti.aggiungiCarta(new Cannone());
         * 
         * 
         * Carta cartaPesca = mazzoCarte.pescaCarta();
         * if (cartaPesca != null) {
         * System.out.println("Carta pescata: " +
         * cartaPesca.getClass().getSimpleName());
         * } else {
         * System.out.println("Il mazzo è vuoto.");
         * }
         */

        System.out.println(nave.toString());


        Mazzo<Componente> mazzoComponenti = new Mazzo<Componente>();
        for (int i=0; i<8; i++){
            Connettori conn[] = new Connettori[4];
            conn[0] = Connettori.values()[(int) (Math.random() * 4)];
            conn[1] = Connettori.values()[(int) (Math.random() * 4)];
            conn[2] = Connettori.values()[(int) (Math.random() * 4)];
            conn[3] = Connettori.values()[(int) (Math.random() * 4)];
            Tubi carta = new Tubi(conn);
            carta.setOrientazione(Orientazione.values()[(int) (Math.random() * 4)]);
            mazzoComponenti.aggiungiCarta(carta);
        }
        for (int i=0; i<25; i++){
            Connettori conn[] = new Connettori[4];
            conn[0] = Connettori.values()[(int) (Math.random() * 4)];
            conn[1] = Connettori.values()[(int) (Math.random() * 4)];
            conn[2] = Connettori.values()[(int) (Math.random() * 4)];
            conn[3] = Connettori.values()[(int) (Math.random() * 4)];

            Orientazione o = Orientazione.values()[(int) (Math.random() * 4)];
            Cannone carta = new Cannone(conn, o == Orientazione.NORD ? 1 : 0.5);
            carta.setOrientazione(o);
            mazzoComponenti.aggiungiCarta(carta);
        }
        for (int i=0; i<11; i++){
            Connettori conn[] = new Connettori[4];
            conn[0] = Connettori.values()[(int) (Math.random() * 4)];
            conn[1] = Connettori.values()[(int) (Math.random() * 4)];
            conn[2] = Connettori.values()[(int) (Math.random() * 4)];
            conn[3] = Connettori.values()[(int) (Math.random() * 4)];

            Orientazione o = Orientazione.values()[(int) (Math.random() * 4)];
            CannoneDoppio carta = new CannoneDoppio(conn, o == Orientazione.NORD ? 2 : 1);
            carta.setOrientazione(o);
            mazzoComponenti.aggiungiCarta(carta);
        }
        for(int i=0; i<21; i++){
            Connettori conn[] = new Connettori[4];
            conn[0] = Connettori.values()[(int) (Math.random() * 4)];
            conn[1] = Connettori.values()[(int) (Math.random() * 4)];
            conn[2] = Connettori.values()[(int) (Math.random() * 4)];
            conn[3] = Connettori.values()[(int) (Math.random() * 4)];
            Motore carta = new Motore(conn);
            carta.setOrientazione(Orientazione.NORD);
            mazzoComponenti.aggiungiCarta(carta);
        }
        for (int i=0; i<9; i++){
            Connettori conn[] = new Connettori[4];
            conn[0] = Connettori.values()[(int) (Math.random() * 4)];
            conn[1] = Connettori.values()[(int) (Math.random() * 4)];
            conn[2] = Connettori.values()[(int) (Math.random() * 4)];
            conn[3] = Connettori.values()[(int) (Math.random() * 4)];
            MotoreDoppio carta = new MotoreDoppio(conn);
            carta.setOrientazione(Orientazione.NORD);
            mazzoComponenti.aggiungiCarta(carta);
        }
        for (int i=0; i<17; i++) {
            Connettori conn[] = new Connettori[4];
            conn[0] = Connettori.values()[(int) (Math.random() * 4)];
            conn[1] = Connettori.values()[(int) (Math.random() * 4)];
            conn[2] = Connettori.values()[(int) (Math.random() * 4)];
            conn[3] = Connettori.values()[(int) (Math.random() * 4)];
            Cabina carta = new Cabina(conn);
            carta.setOrientazione(Orientazione.values()[(int) (Math.random() * 4)]);
            mazzoComponenti.aggiungiCarta(carta);
        }
        for (int i=0; i<15; i++){
            Connettori conn[] = new Connettori[4];
            conn[0] = Connettori.values()[(int) (Math.random() * 4)];
            conn[1] = Connettori.values()[(int) (Math.random() * 4)];
            conn[2] = Connettori.values()[(int) (Math.random() * 4)];
            conn[3] = Connettori.values()[(int) (Math.random() * 4)];
            Stiva carta = new Stiva(conn);
            carta.setOrientazione(Orientazione.values()[(int) (Math.random() * 4)]);
            mazzoComponenti.aggiungiCarta(carta);
        }
        for (int i=0; i<9; i++){
            Connettori conn[] = new Connettori[4];
            conn[0] = Connettori.values()[(int) (Math.random() * 4)];
            conn[1] = Connettori.values()[(int) (Math.random() * 4)];
            conn[2] = Connettori.values()[(int) (Math.random() * 4)];
            conn[3] = Connettori.values()[(int) (Math.random() * 4)];
            StivaSpeciale carta = new StivaSpeciale(conn);
            carta.setOrientazione(Orientazione.values()[(int) (Math.random() * 4)]);
            mazzoComponenti.aggiungiCarta(carta);
        }
        for (int i=0; i<6; i++){
            Connettori conn[] = new Connettori[4];
            conn[0] = Connettori.values()[(int) (Math.random() * 4)];
            conn[1] = Connettori.values()[(int) (Math.random() * 4)];
            conn[2] = Connettori.values()[(int) (Math.random() * 4)];
            conn[3] = Connettori.values()[(int) (Math.random() * 4)];
            SupportoVitale carta = new SupportoVitale(conn, SupportoVitale.TipoSupporto.CANNONE);
            carta.setOrientazione(Orientazione.values()[(int) (Math.random() * 4)]);
            mazzoComponenti.aggiungiCarta(carta);
        }
        for (int i=0; i<6; i++){
            Connettori conn[] = new Connettori[4];
            conn[0] = Connettori.values()[(int) (Math.random() * 4)];
            conn[1] = Connettori.values()[(int) (Math.random() * 4)];
            conn[2] = Connettori.values()[(int) (Math.random() * 4)];
            conn[3] = Connettori.values()[(int) (Math.random() * 4)];
            SupportoVitale carta = new SupportoVitale(conn, SupportoVitale.TipoSupporto.MOTORE);
            carta.setOrientazione(Orientazione.values()[(int) (Math.random() * 4)]);
            mazzoComponenti.aggiungiCarta(carta);
        }
        for (int i=0; i<6; i++){
            Connettori conn[] = new Connettori[4];
            conn[0] = Connettori.values()[(int) (Math.random() * 4)];
            conn[1] = Connettori.values()[(int) (Math.random() * 4)];
            conn[2] = Connettori.values()[(int) (Math.random() * 4)];
            conn[3] = Connettori.values()[(int) (Math.random() * 4)];
            BatteriaTripla carta = new BatteriaTripla(conn,3);
            carta.setOrientazione(Orientazione.values()[(int) (Math.random() * 4)]);
            mazzoComponenti.aggiungiCarta(carta);
        }
        for (int i=0; i<11; i++){
            Connettori conn[] = new Connettori[4];
            conn[0] = Connettori.values()[(int) (Math.random() * 4)];
            conn[1] = Connettori.values()[(int) (Math.random() * 4)];
            conn[2] = Connettori.values()[(int) (Math.random() * 4)];
            conn[3] = Connettori.values()[(int) (Math.random() * 4)];
            Batteria carta = new Batteria(conn,2);
            carta.setOrientazione(Orientazione.values()[(int) (Math.random() * 4)]);
            mazzoComponenti.aggiungiCarta(carta);
        }
        for (int i=0; i<8; i++){
            Connettori conn[] = new Connettori[4];
            conn[0] = Connettori.values()[(int) (Math.random() * 4)];
            conn[1] = Connettori.values()[(int) (Math.random() * 4)];
            conn[2] = Connettori.values()[(int) (Math.random() * 4)];
            conn[3] = Connettori.values()[(int) (Math.random() * 4)];
            Scudo carta = new Scudo(conn);
            carta.setOrientazione(Orientazione.values()[(int) (Math.random() * 4)]);
            mazzoComponenti.aggiungiCarta(carta);
        }

    }

}
