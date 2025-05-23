package entities;

public class PallaCannone {

	private Direzione direzione;
	private Dimensione grandezza;

	public PallaCannone() {
		direzione = Direzione.values()[(int) (Math.random() * Direzione.values().length)];
		// usa l'operatore ternario (condizione ? vero : falso) per assegnare un valore
		// alla variabile grandezza.
		grandezza = Math.random() < 0.5 ? Dimensione.PALLA_CANNONE_PICCOLA : Dimensione.PALLA_CANNONE_GRANDE;
	}

	public Direzione getDirezione() {
		return direzione;
	}

	public Dimensione getGrandezza() {
		return grandezza;
	}
}
