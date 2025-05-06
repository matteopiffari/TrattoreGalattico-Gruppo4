package ship.components;

public class Cannone extends Componente {
	private int potenza;
	
	public Cannone (Connettori[] connettori, int potenza){
		super(connettori);
		this.potenza=potenza;
	}
	
	public int getPotenza() {
		return potenza;
	}

}
