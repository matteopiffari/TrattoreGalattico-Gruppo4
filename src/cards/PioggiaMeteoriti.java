package cards;

import entities.Meteorite;

public class PioggiaMeteoriti extends Carta {

	private int numeroMeteoriti;
	private Meteorite meteoriti[];

	public PioggiaMeteoriti(int livello) {
		super("Pioggia Meteoriti", "Diversi meteoriti colpiscono tutti i giocatori");
		numeroMeteoriti = (int) (Math.random() * 6) + 1;
		meteoriti = new Meteorite[numeroMeteoriti];
		for (int i = 0; i < numeroMeteoriti; i++) {		//genera un numero casuale di meteoriti
			meteoriti[i] = new Meteorite();		
		}

	}

	public Meteorite[] getMeteoriti() {
		return meteoriti;
	}
}
