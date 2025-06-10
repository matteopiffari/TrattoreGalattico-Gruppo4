package cards;

public class Schiavisti extends Carta {

	private int giorniPersi;
	private int equipaggioPerso;
	private int creditiGuadagnati;
	private int potenzaFuoco;

	public Schiavisti(int livello) {
		super("Schiavisti",
				"Si applica a tutti i giocatori; viene controllata la potenza di fuoco: se sufficiente guadagni crediti perdendo giorni, altrimenti perdi equipaggio");
		if (livello == 1) {
			giorniPersi = 1;
			creditiGuadagnati = 5;
			equipaggioPerso = 3;
			potenzaFuoco = 6;
		} else if (livello == 2) {
			giorniPersi = 2;
			creditiGuadagnati = 8;
			equipaggioPerso = 4;
			potenzaFuoco = 7;
		} else if (livello == 3) {
			giorniPersi = 2;
			creditiGuadagnati = 10;
			equipaggioPerso = 5;
			potenzaFuoco = 8;
		}

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
