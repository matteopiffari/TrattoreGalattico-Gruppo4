package cards;

public class PioggiaMeteoriti extends Carta {
	
	int numeroMeteoriti;
	int direzione[][];
	
	public PioggiaMeteoriti() {
		numeroMeteoriti = (int)(Math.random() * 6) +1 ;
		direzione=new int[numeroMeteoriti][2];
		for(int i=0; i<numeroMeteoriti; i++) {
			direzione[i][0]= (int)(Math.random() * 4);
			direzione[i][1]= (int)(Math.random() * 10) +2;
		}

	}
	
	public int[][] direzioni () {
		return direzione;
		
	}

}
