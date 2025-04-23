package cards;

public class NaveAbbandonata extends Carta {
	int equipaggioPerso;
	int creditiGuadagnati;
	int giorniPersi;
	
	public NaveAbbandonata () {
		equipaggioPerso= (int)(Math.random() * 7) +1;
		creditiGuadagnati= (int)(Math.random() * 11) +1;
		giorniPersi= (int)(Math.random() * 2) +1;
	}

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
