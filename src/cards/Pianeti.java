package cards;

public class Pianeti extends Carta {

	private int pianeti[][];
	private int giorniPersi;
	

	public Pianeti(int livello) {
		int numeroPianeti=(int) (Math.random() * 4) + 1;
		int merci=(int) (Math.random() * 5) + 1;
		pianeti = new int[numeroPianeti][merci];
		for (int i=0; i<numeroPianeti;i++) {
			for(int j=0; j<merci;j++) {
				pianeti[i][j]=(int) (Math.random() * 4) + 1;
			}
		}
		giorniPersi = (int) (Math.random() * 5) + 1;
	}

	public int[][] getPianeti() {
		return pianeti;
	}

	public int getGiorniPersi() {
		return giorniPersi;
	}

}
