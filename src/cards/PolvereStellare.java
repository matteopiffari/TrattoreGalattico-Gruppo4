package cards;

import ship.Nave;

public class PolvereStellare extends Carta {
	public PolvereStellare() {
		super("Polvere Stellare", "Perdi giorni in base a connettori esposti");
	}

	public int giorniPersi(Nave nave) {
		return nave.contaConnettori();
	}

}
