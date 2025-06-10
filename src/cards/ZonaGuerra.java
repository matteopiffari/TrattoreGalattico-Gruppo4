package cards;

import java.util.*;
import java.util.function.Consumer;

import entities.PallaCannone;
import entities.Tabellone;
import ship.Nave;

public class ZonaGuerra extends Carta {
	public ZonaGuerra(){
		super("Zona di Guerra","La nave con minore equipaggio, quella con minore potenza motrice e quella con minore potenza di fuoco subiscono delle penalita'");
	}

	public void esegui(Tabellone tabellone) {
		List<Consumer<Nave>> penalty = Arrays.asList(// Vengono scelte tre penalità tra: perdita di giorni, merci, equipaggio e cannonate,
				(n) -> perdiGiorni(tabellone, n),//mescolate in ordine casuale, e applicate rispettivamente alla potenza di fuoco,
				this::perdiMerci,//alla potenza motrice e all’equipaggio della nave.
				this::perdiEquipaggio,//
				this::cannonate);//
		Collections.shuffle(penalty);//L’associazione penalità-caratteristica cambia a ogni esecuzione.

		menoPotenzaFuoco(tabellone, penalty.get(0)); // 
		menoPotenzaMotrice(tabellone, penalty.get(1)); // 
		menoEquipaggio(tabellone, penalty.get(2)); // 
	}

	// #region Penalità

	private void perdiGiorni(Tabellone tabellone, Nave nave) {
		tabellone.setPosizione(nave, nave.getPosizione() - ((int) (Math.random() * 4) + 1));
	}

	private void perdiMerci(Nave nave) {
		nave.perdiMerci((int) (Math.random() * 4) + 1);
	}

	private void perdiEquipaggio(Nave nave) {
		nave.perdiEquipaggio((int) (Math.random() * 4) + 1);
	}

	private void cannonate(Nave nave) {
		PallaCannone cannonate[] = new PallaCannone[(int) (Math.random() * 5) + 2];
		for (int i = 0; i < cannonate.length; i++) {
			cannonate[i] = new PallaCannone();
		}
		nave.prendiCannonate(cannonate);

	}

	// #endregion

	private void menoPotenzaFuoco(Tabellone tabellone, Consumer<Nave> penalty) {
		Nave nave = tabellone.minorPotenzaFuoco();
		penalty.accept(nave);
	}

	private void menoPotenzaMotrice(Tabellone tabellone, Consumer<Nave> penalty) {
		Nave nave = tabellone.minorPotenzaMotrice();
		penalty.accept(nave);
	}

	private void menoEquipaggio(Tabellone tabellone, Consumer<Nave> penalty) {
		Nave nave = tabellone.minorEquipaggio();
		penalty.accept(nave);
	}
}