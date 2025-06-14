package ship.components;

import ship.Nave;

public class Motore extends Componente {

	public Motore(Connettori[] connettori) {
		super(connettori);

	}

	@Override
	public boolean posizionabile(Nave nave, int x, int y) { //controllo particolare per il motore, che deve essere forzatamente posizionato verso il basso
		Connettori[] connettori = this.getConnettori();
		boolean allNull = true;

        if ((nave.getComponente(y - 1, x) != null))
            allNull = false;
        if ((nave.getComponente(y, x + 1) != null))
            allNull = false;
        if ((nave.getComponente(y + 1, x) != null))
            allNull = false;
        if ((nave.getComponente(y, x - 1) != null))
            allNull = false;

        if (allNull)
            return false;
		if (nave.getComponente(y - 1, x) != null) {
		if (nave.getComponente(y - 1, x).getConnettore(Orientazione.SUD) != connettori[0]
				&& ((nave.getComponente(y - 1, x).getConnettore(Orientazione.SUD) == Connettori.UNIVERSALE
						&& connettori[0] == Connettori.NIENTE)
						|| (connettori[0] == Connettori.UNIVERSALE
								&& nave.getComponente(y - 1, x).getConnettore(Orientazione.SUD) == Connettori.NIENTE)))
			return false;
		}
		if (nave.getComponente(y, x + 1) != null) {
		if (nave.getComponente(y, x + 1).getConnettore(Orientazione.OVEST) != connettori[1]
				&& ((nave.getComponente(y, x + 1).getConnettore(Orientazione.OVEST) == Connettori.UNIVERSALE
						&& connettori[1] == Connettori.NIENTE)
						|| (connettori[1] == Connettori.UNIVERSALE && nave.getComponente(y, x + 1)
								.getConnettore(Orientazione.OVEST) == Connettori.NIENTE)))
			return false;
		}
		if (nave.getComponente(y + 1, x) != null) {
		if (nave.getComponente(y + 1, x).getConnettore(Orientazione.NORD) != connettori[2]
				&& ((nave.getComponente(y + 1, x).getConnettore(Orientazione.NORD) == Connettori.UNIVERSALE
						&& connettori[2] == Connettori.NIENTE)
						|| (connettori[2] == Connettori.UNIVERSALE
								&& nave.getComponente(y + 1, x).getConnettore(Orientazione.NORD) == Connettori.NIENTE)))
			return false;
		}
		if (nave.getComponente(y, x - 1) != null) {
		if (nave.getComponente(y, x - 1).getConnettore(Orientazione.EST) != connettori[3]
				&& ((nave.getComponente(y, x - 1).getConnettore(Orientazione.EST) == Connettori.UNIVERSALE
						&& connettori[3] == Connettori.NIENTE)
						|| (connettori[3] == Connettori.UNIVERSALE
								&& nave.getComponente(y, x - 1).getConnettore(Orientazione.EST) == Connettori.NIENTE)))
			return false;
		}

		if (nave.getComponente(y + 1, x) != null)
			return false;

		return true;
	}
	@Override
	public String toString() {
		return "Motore";
	}
	public String toStringAbbreviato() {
		return "MO";
	}
}
