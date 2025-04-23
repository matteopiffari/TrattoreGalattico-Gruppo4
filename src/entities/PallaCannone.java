package entities;

public class PallaCannone {
	
	int direzione;
	int grandezza;
	
	public PallaCannone() {
		direzione= (int)(Math.random() * 4) +1;
		grandezza= (int)(Math.random() * 2) +1;
	}

	public int getDirezione() {
		return direzione;
	}

	public int getGrandezza() {
		return grandezza;
	}

}
