package cards;

public class StazioneAbbandonata extends Carta {

	int merciGuadagnate[];
	int giorniPersi;
	int equipaggioNec;
	
	public StazioneAbbandonata () {
		merciGuadagnate=new int[(int)(Math.random() * 4) +1];
		for (int i=0; i<merciGuadagnate.length; i++) {
			merciGuadagnate[i]=(int)(Math.random() * 4) +1;
		}
		giorniPersi= (int)(Math.random() * 2) +1;
		equipaggioNec=(int)(Math.random() * 10) +1;
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
