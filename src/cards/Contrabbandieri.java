package cards;

public class Contrabbandieri extends Carta {

	private int giorniPersi;
	private int potenzaFuoco;
	private int merciPerse;
	private int merciGuadagnate[];
	//generazione randomica dei parametri della carta
	public Contrabbandieri(int livello) {
		if (livello==1) {
			giorniPersi = 1;
			potenzaFuoco = 4;
			merciPerse = 2;
			merciGuadagnate = new int[3];	
			for (int i = 0; i < merciGuadagnate.length; i++) {			
				merciGuadagnate[i] = (int) (Math.random() * 4) + 1;
			}
		}
		else if(livello==2) {
			giorniPersi = 1;
			potenzaFuoco = 8;
			merciPerse = 3;
			merciGuadagnate = new int[3];	
			for (int i = 0; i < merciGuadagnate.length; i++) {			
				merciGuadagnate[i] = (int) (Math.random() * 4) + 1;
			}
		}
		else if(livello==3) {
			giorniPersi = 2;
			potenzaFuoco = 9;
			merciPerse = 4;
			merciGuadagnate = new int[5];	
			for (int i = 0; i < merciGuadagnate.length; i++) {			
				merciGuadagnate[i] = (int) (Math.random() * 4) + 1;
			}
		}
	}
	
// Metodi per accedere alle informazioni della carta
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
