package ship.components;

import ship.Nave;

public class Batteria extends Componente {
	
	private int carica;
	
	public Batteria (Connettori[] connettori, int carica){
		super(connettori);
		this.carica=carica;
	}

	public int getCarica() {
		return carica;
	}

	public void setCarica(int carica) {
		this.carica = carica;
	}
	
	@Override
	public boolean posizionabile(Nave nave, int x, int y) {
		Connettori[] connettori=this.getConnettori();
		for (int i=0; i<connettori.length; i++) {
			Connettori connettore=nave.getComponente(x, y-1).getConnettore(Orientazione.values()[(i+2)%4]);	//prende il componente una riga sopra, poi prende il tipo di connettore a sud del componente 
			if (connettori[i]!=connettore && (connettore!=Connettori.UNIVERSALE && connettori[i]!=Connettori.UNIVERSALE)) {
				return false;
			}
		}
		 

		return true;
	}
}
