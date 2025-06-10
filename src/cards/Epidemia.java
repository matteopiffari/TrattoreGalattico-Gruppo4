package cards;

import ship.Nave;
import ship.components.Connettori;
import ship.components.Cabina;
import ship.components.Componente;

public class Epidemia extends Carta {
	public Epidemia(){
		super("Epidemia", "rimuove un membro dell'equipaggio da ogni cabina occupata interconnessa ad un'altra cabina occupata");
	}

	public void esegui(Nave ship) {

		Componente[][] nave = ship.getNave();		//ottiene la matrice della nave
		// Ciclo sui settori interni della nave (evita i bordi): righe 3–8, colonne 2–10
		for (int i = 3; i < 9; i++) {
			for (int j = 2; j < 11; j++) {
				if (nave[i][j] instanceof Cabina) {		// controlla se la cella contiene una cabina
					if (nave[i - 1][j] instanceof Cabina && nave[i][j].getConnettori()[0] != Connettori.NIENTE) {

						((Cabina) nave[i][j]).setEquipaggio(((Cabina) nave[i][j]).getEquipaggio() - 1);
						((Cabina) nave[i - 1][j]).setEquipaggio(((Cabina) nave[i - 1][j]).getEquipaggio() - 1);
																												//verifica la presenza di altre cabine e riduce l'equipaggio
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
