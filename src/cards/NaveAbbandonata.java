package cards;

public class NaveAbbandonata extends Carta {
	private int equipaggioPerso;
	private int creditiGuadagnati;
	private int giorniPersi;

	public NaveAbbandonata() {			//generazione randomica delle ricompense e penalità
		equipaggioPerso = (int) (Math.random() * 7) + 1;
		creditiGuadagnati = (int) (Math.random() * 11) + 1;
		giorniPersi = (int) (Math.random() * 2) + 1;
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
