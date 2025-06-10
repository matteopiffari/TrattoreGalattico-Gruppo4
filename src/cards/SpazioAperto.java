package cards;

import entities.Tabellone;
import ship.Nave;

public class SpazioAperto extends Carta {
	public SpazioAperto(){
		super("Spazio Aperto","Guadagni giorni in base alla potenza motrice");
	}
	
	public void esegui(Nave nave, Tabellone tabellone, int potenza){
		int posizioneAttuale = nave.getPosizione();
		tabellone.setPosizione(nave, posizioneAttuale + potenza);
		System.out.println("La nave avanza di " + potenza + " caselle.");
	}
}

		