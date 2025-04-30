package cards;

import ship.Nave;
import ship.components.Connettori;
import ship.components.Cabina;
import ship.components.Componente;

public class Epidemia extends Carta {

	public void esegui(Nave ship) {

		Componente[][] nave = ship.getNave();

		for (int i = 3; i < 9; i++) {
			for (int j = 2; j < 11; j++) {
				if (nave[i][j] instanceof Cabina) {
					if (nave[i - 1][j] instanceof Cabina && nave[i][j].getConnettori()[0] != Connettori.NIENTE) {

						((Cabina) nave[i][j]).setEquipaggio(((Cabina) nave[i][j]).getEquipaggio() - 1);
						((Cabina) nave[i - 1][j]).setEquipaggio(((Cabina) nave[i - 1][j]).getEquipaggio() - 1);

					} else if (nave[i + 1][j] instanceof Cabina && nave[i][j].getConnettori()[2] != Connettori.NIENTE) {
						((Cabina) nave[i][j]).setEquipaggio(((Cabina) nave[i][j]).getEquipaggio() - 1);
						((Cabina) nave[i + 1][j]).setEquipaggio(((Cabina) nave[i + 1][j]).getEquipaggio() - 1);

					} else if (nave[i][j - 1] instanceof Cabina && nave[i][j].getConnettori()[3] != Connettori.NIENTE) {
						((Cabina) nave[i][j]).setEquipaggio(((Cabina) nave[i][j]).getEquipaggio() - 1);
						((Cabina) nave[i][j - 1]).setEquipaggio(((Cabina) nave[i][j - 1]).getEquipaggio() - 1);

					} else if (nave[i][j + 1] instanceof Cabina && nave[i][j].getConnettori()[1] != Connettori.NIENTE) {
						((Cabina) nave[i][j]).setEquipaggio(((Cabina) nave[i][j]).getEquipaggio() - 1);
						((Cabina) nave[i][j + 1]).setEquipaggio(((Cabina) nave[i][j + 1]).getEquipaggio() - 1);

					}
				}
			}
		}
	}
}
