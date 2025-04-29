package cards;

import java.util.*;

import entities.PallaCannone;
import ship.Nave;

public class ZonaGuerra extends Carta{
	
	public void esegui(Tabellone tabellone) {
		List<Runnable> penalty= Arrays.asList(
				()->perdiGiorni(tabellone),
				()->perdiMerci(),
				()->perdiEquipaggio(tabellone),
				()->cannonate(tabellone)
		);
		Collections.shuffle(penalty);	
	}
	
	private void perdiGiorni(Tabellone tabellone, Nave nave) {
		tabellone.setPosizione(nave, nave.getPosizione()-((int) (Math.random() * 4) + 1));
	}
	
	private void perdiMerci(Nave nave) {
		nave.perdiMerci((int) (Math.random() * 4) + 1));
	}
	
	private void perdiEquipaggio(Nave nave) {
		nave.perdiEquipaggio((int) (Math.random() * 4) + 1));
	}
	
	private void cannonate(Nave nave) {
		PallaCannone cannonate[] = new PallaCannone[(int) (Math.random() * 5) + 2];
		for(int i=0; i<cannonate.length; i++) {
			cannonate[i]=new PallaCannone();
		}
		nave.prendiCannonate(cannonate);

	}
	
	private void menoPotenzaFuoco(Tabellone tabellone, Runnable penalty) {
		Nave nave = tabellone.minorPotenzaFuoco();
		penalty.run(tabellone, nave);
	}
	
	private void menoPotenzaMotrice(Tabellone tabellone, Runnable penalty) {
		Nave nave = tabellone.minorPotenzaMotrice();
		penalty.run(nave);
	}
	
	private void menoEquipaggio(Tabellone tabellone, Runnable penalty) {
		Nave nave = tabellone.minorEquipaggio();
		penalty.run(nave);
	}

}