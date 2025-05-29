package ship.components;

import ship.Nave;

public class Stiva extends Componente implements Ruotabile {
	protected int merci[];

	public Stiva(Connettori[] connettori) {
		super(connettori);		// prende il metodo della classe padre Componente
		merci = new int[(int) (Math.random() * 2) + 2]; 
	}

	public int[] getMerci() {
		return merci;
	}

	public boolean setMerci(int merce) {
		boolean aggiungi = false;
		if (merce > 3) {
			return false;
		}
		for (int i = 0; i < merci.length; i++) {
			if (merci[i] == 0) {
				merci[i] = merce;
				aggiungi = true;
			}
		}
		return aggiungi;
	}

	@Override
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
		Connettori last = this.getConnettori()[3];

		for (int i = 3; i > 0; i--) {
			this.getConnettori()[i] = this.getConnettori()[i - 1];
		}

		this.getConnettori()[0] = last;
	}
}
