package cards;

import entities.PallaCannone;

public class Pirati extends Carta {

	private int giorniPersi;
	private int potenzaFuoco;
	private int creditiGuadagnati;
	private int numeroCannonate;
	private PallaCannone cannonate[];

	public Pirati(int livello) {	//generazione randomica delle ricompense e penalità
		if (livello==1) {
			giorniPersi = 1;
			creditiGuadagnati = 4;
			numeroCannonate = 3 ;
			potenzaFuoco=5;
			cannonate = new PallaCannone[numeroCannonate];
			for (int i = 0; i < numeroCannonate; i++) {
				cannonate[i] = new PallaCannone();
			}
		}
		else if(livello==2) {
			giorniPersi = 2;
			creditiGuadagnati = 7;
			numeroCannonate = 3 ;
			potenzaFuoco=6;
			cannonate = new PallaCannone[numeroCannonate];
			for (int i = 0; i < numeroCannonate; i++) {
				cannonate[i] = new PallaCannone();
			}
		}
		else if(livello==3) {
			giorniPersi = 2;
			creditiGuadagnati = 12;
			numeroCannonate = 5 ;
			potenzaFuoco=10;
			cannonate = new PallaCannone[numeroCannonate];
			for (int i = 0; i < numeroCannonate; i++) {
				cannonate[i] = new PallaCannone();
			}
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
	public int getPotenzaFuoco() {
		return potenzaFuoco;
	}
}