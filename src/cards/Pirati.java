package cards;

import entities.PallaCannone;

public class Pirati extends Carta {
	
	int giorniPersi;
	int potenzaFuoco;
	int creditiGuadagnati;
	int numeroCannonate;
	PallaCannone cannonate[];
	
	
	public Pirati () {
		giorniPersi= 2;
		creditiGuadagnati= (int)(Math.random() * 12) +1;
		numeroCannonate= (int)(Math.random() * 6) +1 ;
		cannonate=new PallaCannone[numeroCannonate];
		for(int i=0; i<numeroCannonate; i++) {
			cannonate[i]= new PallaCannone ();
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
