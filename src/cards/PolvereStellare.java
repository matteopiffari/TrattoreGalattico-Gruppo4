package cards;

import ship.Nave;

public class PolvereStellare extends Carta {

	public int giorniPersi(Nave nave) {
		return nave.contaConnettori();
	}

}
