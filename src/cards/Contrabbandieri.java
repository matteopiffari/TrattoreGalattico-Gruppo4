package cards;

public class Contrabbandieri extends Carta {

	private int giorniPersi;
	private int potenzaFuoco;
	private int merciPerse;
	private int merciGuadagnate[];

	public Contrabbandieri() {
		giorniPersi = (int) (Math.random() * 2) + 1;
		potenzaFuoco = (int) (Math.random() * 6) + 4;
		merciPerse = (int) (Math.random() * 3) + 2;
		merciGuadagnate = new int[(int) (Math.random() * 4) + 1];
		for (int i = 0; i < merciGuadagnate.length; i++) {
			merciGuadagnate[i] = (int) (Math.random() * 4) + 1;
		}
	}

	public int getGiorniPersi() {
		return giorniPersi;
	}

	public int getPotenzaFuoco() {
		return potenzaFuoco;
	}

	public int getMerciPerse() {
		return merciPerse;
	}

	public int[] getMerciGuadagnate() {
		return merciGuadagnate;
	}
}
