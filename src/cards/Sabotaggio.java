package cards;

import ship.Nave;

public class Sabotaggio extends Carta {
	
	public void esegui(Nave nave) {
		for(int i=0; i<3; i++) {
			int x= (int)(Math.random() * 11) + 2;
			int y= (int)(Math.random() * 11) + 2;
			
			if(!nave.isEmpty(x,y)) {
				nave.distruggiComponente(x,y);
				break;
			}
		}
	}

}
