package ship.components;

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

}
