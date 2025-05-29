package cards;

public class Pianeti extends Carta {

	private int pianeti[][];
	private int giorniPersi;

	public Pianeti(int livello) {
		pianeti = new int[(int) (Math.random() * 4) + 1][(int) (Math.random() * 5) + 1];
		giorniPersi = (int) (Math.random() * 5) + 1;
	}

	public int[][] getPianeti() {
		return pianeti;
	}

	public int getGiorniPersi() {
		return giorniPersi;
	}

}
