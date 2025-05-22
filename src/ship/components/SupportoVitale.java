package ship.components;

import ship.Nave;

public class SupportoVitale extends Cabina {

	public enum TipoSupporto {
		CANNONE, MOTORE
	}

	private TipoSupporto tipo;

	public SupportoVitale(Connettori[] connettori, TipoSupporto tipo) {
		super(connettori);
		this.tipo = tipo;
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
		boolean presenzaCabina = false;

		if (nave.getComponente(y - 1, x) instanceof Cabina
				&& (nave.getComponente(y - 1, x).getConnettore(Orientazione.SUD) == connettori[0]
						|| (connettori[0] == Connettori.UNIVERSALE
								&& nave.getComponente(y - 1, x).getConnettore(Orientazione.SUD) != Connettori.NIENTE)
						|| (nave.getComponente(y - 1, x).getConnettore(Orientazione.SUD) == Connettori.UNIVERSALE
								&& connettori[0] != Connettori.NIENTE)))
			presenzaCabina = true;
		if (nave.getComponente(y, x + 1) instanceof Cabina
				&& (nave.getComponente(y, x + 1).getConnettore(Orientazione.OVEST) == connettori[1]
						|| (connettori[1] == Connettori.UNIVERSALE
								&& nave.getComponente(y, x + 1).getConnettore(Orientazione.OVEST) != Connettori.NIENTE)
						|| (nave.getComponente(y, x + 1).getConnettore(Orientazione.OVEST) == Connettori.UNIVERSALE
								&& connettori[1] != Connettori.NIENTE)))
			presenzaCabina = true;
		if (nave.getComponente(y + 1, x) instanceof Cabina
				&& (nave.getComponente(y + 1, x).getConnettore(Orientazione.NORD) == connettori[2]
						|| (connettori[2] == Connettori.UNIVERSALE
								&& nave.getComponente(y + 1, x).getConnettore(Orientazione.NORD) != Connettori.NIENTE)
						|| (nave.getComponente(y + 1, x).getConnettore(Orientazione.NORD) == Connettori.UNIVERSALE
								&& connettori[2] != Connettori.NIENTE)))
			presenzaCabina = true;
		if (nave.getComponente(y, x - 1) instanceof Cabina
				&& (nave.getComponente(y, x - 1).getConnettore(Orientazione.EST) == connettori[3]
						|| (connettori[3] == Connettori.UNIVERSALE
								&& nave.getComponente(y, x - 1).getConnettore(Orientazione.EST) != Connettori.NIENTE)
						|| (nave.getComponente(y, x - 1).getConnettore(Orientazione.EST) == Connettori.UNIVERSALE
								&& connettori[3] != Connettori.NIENTE)))
			presenzaCabina = true;

		if (!presenzaCabina)
			return false;

		return true;
	}
}
