package ship.components;

import ship.Nave;

public class SupportoVitale extends Cabina { // componente necessario per ospitare un alieno

	public enum TipoSupporto {
		CANNONE, MOTORE
	}

	private TipoSupporto tipo;

	public SupportoVitale(Connettori[] connettori, TipoSupporto tipo) {
		super(connettori);
		this.tipo = tipo;
	}

	@Override // controllo specifico per il supporto vitale, che deve essere posizionato
				// forzatamente adiacente ad una cabina
	public boolean posizionabile(Nave nave, int colonna, int riga) {
		Connettori[] connettori = this.getConnettori();
		boolean allNull = true;

		if ((nave.getComponente(riga - 1, colonna) != null))
			allNull = false;
		if ((nave.getComponente(riga, colonna + 1) != null))
			allNull = false;
		if ((nave.getComponente(riga + 1, colonna) != null))
			allNull = false;
		if ((nave.getComponente(riga, colonna - 1) != null))
			allNull = false;

		if (allNull)
			return false;

		if (nave.getComponente(riga - 1, colonna) != null) { // se è diverso da null, chiama gli altri metodi
			if (nave.getComponente(riga - 1, colonna).getConnettore(Orientazione.SUD) != connettori[0]
					&& ((nave.getComponente(riga - 1, colonna).getConnettore(Orientazione.SUD) == Connettori.UNIVERSALE
							&& connettori[0] == Connettori.NIENTE)
							|| (connettori[0] == Connettori.UNIVERSALE
									&& nave.getComponente(riga - 1, colonna)
											.getConnettore(Orientazione.SUD) == Connettori.NIENTE)))
				return false;
		}
		if (nave.getComponente(riga, colonna + 1) != null) {
			if (nave.getComponente(riga, colonna + 1).getConnettore(Orientazione.OVEST) != connettori[1]
					&& ((nave.getComponente(riga, colonna + 1)
							.getConnettore(Orientazione.OVEST) == Connettori.UNIVERSALE
							&& connettori[1] == Connettori.NIENTE)
							|| (connettori[1] == Connettori.UNIVERSALE && nave.getComponente(riga, colonna + 1)
									.getConnettore(Orientazione.OVEST) == Connettori.NIENTE)))
				return false;
		}
		if (nave.getComponente(riga + 1, colonna) != null) {
			if (nave.getComponente(riga + 1, colonna).getConnettore(Orientazione.NORD) != connettori[2]
					&& ((nave.getComponente(riga + 1, colonna).getConnettore(Orientazione.NORD) == Connettori.UNIVERSALE
							&& connettori[2] == Connettori.NIENTE)
							|| (connettori[2] == Connettori.UNIVERSALE
									&& nave.getComponente(riga + 1, colonna)
											.getConnettore(Orientazione.NORD) == Connettori.NIENTE)))
				return false;
		}
		if (nave.getComponente(riga, colonna - 1) != null) {
			if (nave.getComponente(riga, colonna - 1).getConnettore(Orientazione.EST) != connettori[3]
					&& ((nave.getComponente(riga, colonna - 1).getConnettore(Orientazione.EST) == Connettori.UNIVERSALE
							&& connettori[3] == Connettori.NIENTE)
							|| (connettori[3] == Connettori.UNIVERSALE
									&& nave.getComponente(riga, colonna - 1)
											.getConnettore(Orientazione.EST) == Connettori.NIENTE)))
				return false;
		}
		boolean presenzaCabina = false;

		if (nave.getComponente(riga - 1, colonna) instanceof Cabina
				&& (nave.getComponente(riga - 1, colonna).getConnettore(Orientazione.SUD) == connettori[0]
						|| (connettori[0] == Connettori.UNIVERSALE
								&& nave.getComponente(riga - 1, colonna)
										.getConnettore(Orientazione.SUD) != Connettori.NIENTE)
						|| (nave.getComponente(riga - 1, colonna)
								.getConnettore(Orientazione.SUD) == Connettori.UNIVERSALE
								&& connettori[0] != Connettori.NIENTE)))
			presenzaCabina = true;
		if (nave.getComponente(riga, colonna + 1) instanceof Cabina
				&& (nave.getComponente(riga, colonna + 1).getConnettore(Orientazione.OVEST) == connettori[1]
						|| (connettori[1] == Connettori.UNIVERSALE
								&& nave.getComponente(riga, colonna + 1)
										.getConnettore(Orientazione.OVEST) != Connettori.NIENTE)
						|| (nave.getComponente(riga, colonna + 1)
								.getConnettore(Orientazione.OVEST) == Connettori.UNIVERSALE
								&& connettori[1] != Connettori.NIENTE)))
			presenzaCabina = true;
		if (nave.getComponente(riga + 1, colonna) instanceof Cabina
				&& (nave.getComponente(riga + 1, colonna).getConnettore(Orientazione.NORD) == connettori[2]
						|| (connettori[2] == Connettori.UNIVERSALE
								&& nave.getComponente(riga + 1, colonna)
										.getConnettore(Orientazione.NORD) != Connettori.NIENTE)
						|| (nave.getComponente(riga + 1, colonna)
								.getConnettore(Orientazione.NORD) == Connettori.UNIVERSALE
								&& connettori[2] != Connettori.NIENTE)))
			presenzaCabina = true;
		if (nave.getComponente(riga, colonna - 1) instanceof Cabina
				&& (nave.getComponente(riga, colonna - 1).getConnettore(Orientazione.EST) == connettori[3]
						|| (connettori[3] == Connettori.UNIVERSALE
								&& nave.getComponente(riga, colonna - 1)
										.getConnettore(Orientazione.EST) != Connettori.NIENTE)
						|| (nave.getComponente(riga, colonna - 1)
								.getConnettore(Orientazione.EST) == Connettori.UNIVERSALE
								&& connettori[3] != Connettori.NIENTE)))
			presenzaCabina = true;

		if (!presenzaCabina)
			return false;

		return true;
	}

	@Override
	public String toString() {
		return "Supporto Vitale";
	}

	public String toStringAbbreviato() {
		return "SV";
	}
}
