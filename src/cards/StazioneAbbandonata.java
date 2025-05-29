package cards;

public class StazioneAbbandonata extends Carta {

	private int merciGuadagnate[];
	private int giorniPersi;
	private int equipaggioNec;

	public StazioneAbbandonata(int livello) {
		if (livello==1) {
			merciGuadagnate = new int[2];
			for (int i = 0; i < merciGuadagnate.length; i++) {
				merciGuadagnate[i] = (int) (Math.random() * 4) + 1;
			}
			giorniPersi = 1;
			equipaggioNec = (int) (Math.random() * 2) + 5;
		}
		else if (livello==2) {
			merciGuadagnate = new int[3];
			for (int i = 0; i < merciGuadagnate.length; i++) {
				merciGuadagnate[i] = (int) (Math.random() * 4) + 1;
			}
			giorniPersi = 2;
			equipaggioNec = (int) (Math.random() * 2) + 7;
		}
		else if (livello==3) {
			merciGuadagnate = new int[4];
			for (int i = 0; i < merciGuadagnate.length; i++) {
				merciGuadagnate[i] = (int) (Math.random() * 4) + 1;
			}
			giorniPersi = 2;
			equipaggioNec = (int) (Math.random() * 2) + 9;
		}
		
	}

	public int getGiorniPersi() {
		return giorniPersi;
	}

	public int[] getMerciGuadagnate() {
		return merciGuadagnate;
	}

	public int getEquipaggioNec() {
		return equipaggioNec;
	}

}
