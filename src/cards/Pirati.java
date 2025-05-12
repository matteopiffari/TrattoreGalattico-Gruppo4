package cards;

import entities.PallaCannone;

public class Pirati extends Carta {

	private int giorniPersi;
	private int potenzaFuoco;
	private int creditiGuadagnati;
	private int numeroCannonate;
	private PallaCannone cannonate[];

	public Pirati() {			//generazione randomica delle ricompense e penalità
		giorniPersi = 2;
		creditiGuadagnati = (int) (Math.random() * 12) + 1;
		numeroCannonate = (int) (Math.random() * 6) + 1;
		cannonate = new PallaCannone[numeroCannonate];
		for (int i = 0; i < numeroCannonate; i++) {
			cannonate[i] = new PallaCannone();
		}

	}

	public PallaCannone[] getCannonate() {
		return cannonate;
	}

	public int getGiorniPersi() {
		return giorniPersi;
	}

	public int getCreditiGuadagnati() {
		return creditiGuadagnati;
	}
}