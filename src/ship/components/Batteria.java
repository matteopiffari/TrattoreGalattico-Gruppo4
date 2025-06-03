package ship.components;

import ship.Nave;

public class Batteria extends Componente implements Ruotabile {

	private int carica;

	public Batteria(Connettori[] connettori, int carica) {
		super(connettori);
		this.carica = carica;
	}

	public int getCarica() {
		return carica;
	}

	public void setCarica(int carica) {
		this.carica = carica;
	}

	@Override	//controllo se si puo' posizionare il componente, viene controllato anche che un eventuale componente adiacente abbia un connettore
	public boolean posizionabile(Nave nave, int x, int y) {	
		Connettori[] connettori = this.getConnettori();

		if (nave.getComponente(y - 1, x).getConnettore(Orientazione.SUD) != connettori[0]
				&& ((nave.getComponente(y - 1, x).getConnettore(Orientazione.SUD) == Connettori.UNIVERSALE
						&& connettori[0] == Connettori.NIENTE)
						|| (connettori[0] == Connettori.UNIVERSALE
								&& nave.getComponente(y - 1, x).getConnettore(Orientazione.SUD) == Connettori.NIENTE)))
			return false;
		if (nave.getComponente(y, x + 1).getConnettore(Orientazione.OVEST) != connettori[1]
				&& ((nave.getComponente(y, x + 1).getConnettore(Orientazione.OVEST) == Connettori.UNIVERSALE
						&& connettori[1] == Connettori.NIENTE)
						|| (connettori[1] == Connettori.UNIVERSALE && nave.getComponente(y, x + 1)
								.getConnettore(Orientazione.OVEST) == Connettori.NIENTE)))
			return false;
		if (nave.getComponente(y + 1, x).getConnettore(Orientazione.NORD) != connettori[2]
				&& ((nave.getComponente(y + 1, x).getConnettore(Orientazione.NORD) == Connettori.UNIVERSALE
						&& connettori[2] == Connettori.NIENTE)
						|| (connettori[2] == Connettori.UNIVERSALE
								&& nave.getComponente(y + 1, x).getConnettore(Orientazione.NORD) == Connettori.NIENTE)))
			return false;
		if (nave.getComponente(y, x - 1).getConnettore(Orientazione.EST) != connettori[3]
				&& ((nave.getComponente(y, x - 1).getConnettore(Orientazione.EST) == Connettori.UNIVERSALE
						&& connettori[3] == Connettori.NIENTE)
						|| (connettori[3] == Connettori.UNIVERSALE
								&& nave.getComponente(y, x - 1).getConnettore(Orientazione.EST) == Connettori.NIENTE)))
			return false;

		return true;
	}

	@Override
	public void rotate() {
		Connettori last = this.getConnettori()[3];	//prende l'ultimo connettore per settarlo alla prima posizione nel return

		for (int i = 3; i > 0; i--) {
			this.getConnettori()[i] = this.getConnettori()[i - 1];	
		}

		this.getConnettori()[0] = last;
	}
	@Override
	public String toString() {
		return "Batteria";
	}
	
	public String toStringAbbreviato() {
		return "BS";
	}
	
}
