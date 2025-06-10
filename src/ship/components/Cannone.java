package ship.components;

import ship.Nave;

public class Cannone extends Componente implements Ruotabile {
	private double potenza;

	public Cannone(Connettori[] connettori, double potenza) {
		super(connettori);
		this.potenza = potenza;
	}

	public double getPotenza() {
		return potenza;
	}

	@Override
	public boolean posizionabile(Nave nave, int x, int y) {
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
									&& nave.getComponente(y - 1, x)
											.getConnettore(Orientazione.SUD) == Connettori.NIENTE)))
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
									&& nave.getComponente(y + 1, x)
											.getConnettore(Orientazione.NORD) == Connettori.NIENTE)))
				return false;
		}
		if (nave.getComponente(y, x - 1) != null) {
			if (nave.getComponente(y, x - 1).getConnettore(Orientazione.EST) != connettori[3]
					&& ((nave.getComponente(y, x - 1).getConnettore(Orientazione.EST) == Connettori.UNIVERSALE
							&& connettori[3] == Connettori.NIENTE)
							|| (connettori[3] == Connettori.UNIVERSALE
									&& nave.getComponente(y, x - 1)
											.getConnettore(Orientazione.EST) == Connettori.NIENTE)))
				return false;
		}

		Orientazione punta = this.getOrientazione(); // controlla dove mira il cannone per assegnare la giusta potenza
														// di fuoco
		if (punta == Orientazione.NORD && nave.getComponente(y - 1, x) != null) {
			System.out.println("False, componente sopra");
			return false;
		} else if (punta == Orientazione.EST && nave.getComponente(y, x + 1) != null) {
			System.out.println("False, componente a destra");
			return false;
		} else if (punta == Orientazione.SUD && nave.getComponente(y + 1, x) != null) {
			System.out.println("False, componente sotto");
			return false;
		} else if (punta == Orientazione.OVEST && nave.getComponente(y, x - 1) != null) {
			System.out.println("False, componente a sinistra");
			return false;
		}

		return true;
	}

	@Override
	public void rotate() {
		this.setOrientazione(this.getOrientazione().ruota());
		Connettori last = this.getConnettori()[3];

		for (int i = 3; i > 0; i--) {
			this.getConnettori()[i] = this.getConnettori()[i - 1];
		}

		this.getConnettori()[0] = last;

	}

	@Override
	public String toString() {
		return "Cannone";
	}

	public String toStringAbbreviato() {
		return "CN";
	}
}
