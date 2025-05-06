package entities;

public class PallaCannone {

	private Direzione direzione;
	private Dimensione grandezza;

	public PallaCannone() {
		direzione = Direzione.values()[(int) (Math.random() * Direzione.values().length)];
		grandezza = Math.random() < 0.5 ? Dimensione.PALLA_CANNONE_PICCOLA : Dimensione.PALLA_CANNONE_GRANDE;	// usa l'operatore ternario (condizione ? valore_se_vero : valore_se_falso) per assegnare un valore alla variabile grandezza.
	}

	public Direzione getDirezione() {
		return direzione;
	}

	public Dimensione getGrandezza() {
		return grandezza;
	}
}
