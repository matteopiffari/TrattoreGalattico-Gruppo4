package cards;

import ship.Nave;
import ship.components.Connettori;

public class Epidemia extends Carta{
	
	public void esegui(Nave nave) {
		
		for(int i=3; i<9; i++) {
			for(int j=2; j<11; j++) {
				if(nave[i][j] instanceof Cabina) {
					if(nave[i-1][j] instanceof Cabina && nave[i][j].getConnettori[0]!=Connettori.NIENTE) {
						
						nave[i][j].setEquipaggio(nave[i][j].getEquipaggio - 1);
						nave[i-1][j].setEquipaggio(nave[i-1][j].getEquipaggio - 1);
						
					}else if(nave[i+1][j] instanceof Cabina && nave[i][j].getConnettori[2]!=Connettori.NIENTE){
						nave[i][j].setEquipaggio(nave[i][j].getEquipaggio - 1);
						nave[i+1][j].setEquipaggio(nave[i+1][j].getEquipaggio - 1);
						
					}else if(nave[i][j-1] instanceof Cabina && nave[i][j].getConnettori[3]!=Connettori.NIENTE) {
						nave[i][j].setEquipaggio(nave[i][j].getEquipaggio - 1);
						nave[i][j-1].setEquipaggio(nave[i][j-1].getEquipaggio - 1);
						
					}else if(nave[i][j+1] instanceof Cabina && nave[i][j].getConnettori[1]!=Connettori.NIENTE) {
						nave[i][j].setEquipaggio(nave[i][j].getEquipaggio - 1);
						nave[i][j+1].setEquipaggio(nave[i][j+1].getEquipaggio - 1);
						
					}
				}
			}
		}
	}
}


