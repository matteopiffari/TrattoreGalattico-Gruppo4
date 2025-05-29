package cards;

public class NaveAbbandonata extends Carta {
	private int equipaggioPerso;
	private int creditiGuadagnati;
	private int giorniPersi;

	public NaveAbbandonata(int livello) {	//generazione randomica delle ricompense e penalità
		if (livello==1) {
			equipaggioPerso = (int) (Math.random() * 2) + 2;		//funzione per generare un numero random da 1 a 7
			creditiGuadagnati = (int) (Math.random() * 2) + 3;
			giorniPersi =1;
		}
		else if(livello==2) {
			equipaggioPerso = (int) (Math.random() * 2) + 4;		
			creditiGuadagnati = (int) (Math.random() * 3) + 6;
			giorniPersi =(int) (Math.random() * 2) + 1;
		}
		else if(livello==3) {
			equipaggioPerso = (int) (Math.random() * 2) + 6;		
			creditiGuadagnati = (int) (Math.random() * 2) + 10;
			giorniPersi =2;
		}
		
	}
	//getter
	public int getEquipaggioPerso() {
		return equipaggioPerso;
	}

	public int getCreditiGuadagnati() {
		return creditiGuadagnati;
	}

	public int getGiorniPersi() {
		return giorniPersi;
	}

}
