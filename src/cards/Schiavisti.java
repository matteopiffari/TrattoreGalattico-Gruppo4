package cards;

public class Schiavisti extends Carta {

	private int giorniPersi;
	private int equipaggioPerso;
	private int creditiGuadagnati;
	private int potenzaFuoco;

	public Schiavisti() {
		giorniPersi = (int) (Math.random() * 2) + 1;
		creditiGuadagnati = (int) (Math.random() * 6) + 5;
		equipaggioPerso = (int) (Math.random() * 3) + 3;
		potenzaFuoco = (int) (Math.random() * 3) + 6;
	}

	public int getGiorniPersi() {
		return giorniPersi;
	}

	public int getEquipaggioPerso() {
		return equipaggioPerso;
	}

	public int getCreditiGuadagnati() {
		return creditiGuadagnati;
	}

	public int getPotenzaFuoco() {
		return potenzaFuoco;
	}

}
