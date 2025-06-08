package logica;

import java.util.ArrayList;

import cards.*;
import entities.Giocatore;
import entities.Mazzo;
import entities.Tabellone;
import ship.components.*;

public class InizioGioco {
    public void inizia( ArrayList<Giocatore> giocatori, Tabellone tabellone, Mazzo<Carta> mazzoCarte) {
        // Logica per iniziare il gioco
        System.out.println("Il gioco sta iniziando...");
        // Qui si possono aggiungere ulteriori logiche di inizializzazione
        Carta carta;
        try {
        	carta=mazzoCarte.pescaCarta();
        }
        catch (Exception e) {
        	System.out.println(e.getMessage());
        	return ;   //se c'è eccezione esce dalla funzione
        }
        if (carta instanceof Contrabbandieri) {
        	ArrayList<Componente> componenti = giocatori.get(i).getNave().getComponentiSpecifici(Stiva.class);
        	for (int i=0; i<giocatori.size(); i++) {
        		//prima casta carta in contrabbandieri, ottiene potenza di fuoco e casta valore di ritorno in double
        		if (giocatori.get(i).getNave().getPotenzaFuoco()>=(double)(((Contrabbandieri)carta).getPotenzaFuoco())) { 
        			int[] merciGuadagnate=((Contrabbandieri)carta).getMerciGuadagnate();
        
        			for(int t=0; t<merciGuadagnate.length; t++) {
        				if(merciGuadagnate[t]==4) {
        					for(int s=0; s<componenti.size(); s++ ) {
        						if (componenti.get(s) instanceof StivaSpeciale) {
        							boolean controllo=((StivaSpeciale)componenti.get(s)).setMerci(merciGuadagnate[t]);
        							if (controllo==true) {
        								merciGuadagnate[t]= - 1;
        								break;
        							}	
        						}	
        					}
        				}else {
        					for(int s=0; s<componenti.size(); s++ ) {
        						if (!(componenti.get(s) instanceof StivaSpeciale)) {
        							boolean controllo=((Stiva)componenti.get(s)).setMerci(merciGuadagnate[t]);
        							if (controllo==true) {
        								merciGuadagnate[t]= - 1;
        								break;
        							}	
        						}	
        					}
        				}	
        			}
        			for (int t=0; t<merciGuadagnate.length; t++) {
        				if(merciGuadagnate[t]!= -1) {
        					for(int s=0; s<componenti.size(); s++ ) {
        						if (componenti.get(s) instanceof StivaSpeciale) {
        							boolean controllo=((StivaSpeciale)componenti.get(s)).setMerci(merciGuadagnate[t]);
        							if (controllo==true) {
        								merciGuadagnate[t]= - 1;
        								break;
        							}	
        						}	
        					}
        				}
        			}
        			
        			giocatori.get(i).getNave().setPosizione(giocatori.get(i).getNave().getPosizione()-((Contrabbandieri)carta).getGiorniPersi());
        			break;
        		}
        		else {
        			for (int t=0; t<((Contrabbandieri)carta).getMerciPerse(); t++) {
        				for(int s=0; s<componenti.size(); s++ ) {
    						if (componenti.get(s) instanceof StivaSpeciale) {
    							boolean controllo=((StivaSpeciale)componenti.get(s)).perdiMerci();
    							if(controllo==true) 
    								break;
    						} else {
    							boolean controllo=((Stiva)componenti.get(s)).perdiMerci();
    							if(controllo==true) 
    								break;
    						}
        				}	
        			}
        		}
        			
        	}
        }

    }
}
