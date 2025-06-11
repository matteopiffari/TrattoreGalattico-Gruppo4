package logica;

import java.util.ArrayList;

import cards.*;
import entities.Dimensione;
import entities.Direzione;
import entities.Giocatore;
import entities.Mazzo;
import entities.Meteorite;
import entities.Tabellone;
import ship.Nave;
import ship.components.*;

public class InizioGioco {
	java.util.Scanner scanner = new java.util.Scanner(System.in);

	public void inizia(ArrayList<Giocatore> giocatori, Tabellone tabellone, Mazzo<Carta> mazzoCarte) {
		// Logica per iniziare il gioco
		System.out.println("Il gioco sta iniziando...");
		// Qui si possono aggiungere ulteriori logiche di inizializzazione
		while (true) {
			Carta carta;
			try {
				carta = mazzoCarte.pescaCarta();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("Il gioco termina.");
				return; // se c'è eccezione esce dalla funzione
			}

			System.out.println("Carta pescata: " + carta.getNome());
			System.out.println("Descrizione: " + carta.getDescrizione() + "\n");

			if (carta instanceof Contrabbandieri) {
				System.out.println("Potenza di fuoco necessaria per affrontare i contrabbandieri: "
						+ ((Contrabbandieri) carta).getPotenzaFuoco() + "\n");

				for (int i = 0; i < giocatori.size(); i++) {
					// prima casta carta in contrabbandieri, ottiene potenza di fuoco e casta valore
					// di ritorno in double
					if (giocatori.get(i).getNave()
							.getPotenzaFuoco() >= (double) (((Contrabbandieri) carta).getPotenzaFuoco())) {
						int[] merciGuadagnate = ((Contrabbandieri) carta).getMerciGuadagnate();

						System.out.println("Vuoi perdere " + ((Contrabbandieri) carta).getGiorniPersi()
								+ " giorni per guadagnare merci di questi livelli? (si/no)");

						for (int t = 0; t < merciGuadagnate.length; t++) {
							System.out.print(merciGuadagnate[t] + " ");
						}

						scanner.nextLine();
						String r = scanner.nextLine();
						if (!r.equals("si")) {
							continue; // se non vuole perdere giorni, salta al prossimo giocatore
						}

						// se ha abbastanza potenza di fuoco guadagna merci, chiamando il metodo
						// guadagnaMerci che ha come parametro un array di merci
						giocatori.get(i).getNave().guadagnaMerci(merciGuadagnate);

						// perde giorni
						giocatori.get(i).getNave().setPosizione(
								giocatori.get(i).getNave().getPosizione() - ((Contrabbandieri) carta).getGiorniPersi());
						break;
					} else {
						// se non ha abbastanza potenza di fuoco perde merci, chiamando il metodo
						// perdiMerci che ha come parametro il numero di merci perse
						System.out.println(giocatori.get(i).getColore() + giocatori.get(i).getNome() + "\u001B[0m" +
								" non ha abbastanza potenza di fuoco, quindi perde merci.");
						giocatori.get(i).getNave().perdiMerci(
								((Contrabbandieri) carta).getMerciPerse());
					}

				}
			} else if (carta instanceof Epidemia) {
				for (int i = 0; i < giocatori.size(); i++) {
					((Epidemia) carta).esegui(giocatori.get(i).getNave());
				}
			} else if (carta instanceof NaveAbbandonata) {
				for (int i = 0; i < giocatori.size(); i++) {
					if (giocatori.get(i).getNave().getEquipaggio() > ((NaveAbbandonata) carta).getEquipaggioPerso()) {
						System.out.println("Vuoi perdere " + ((NaveAbbandonata) carta).getEquipaggioPerso()
								+ " equipaggio e " + ((NaveAbbandonata) carta).getGiorniPersi()
								+ " giorni per guadagnare "
								+ ((NaveAbbandonata) carta).getCreditiGuadagnati() + " crediti? (si/no)");

						scanner.nextLine();
						String r = scanner.nextLine();

						if (r.equals("si")) {
							giocatori.get(i).getNave().setPosizione(
									giocatori.get(i).getNave().getPosizione()
											- ((NaveAbbandonata) carta).getGiorniPersi());
							giocatori.get(i).getNave().setEquipaggio(giocatori.get(i).getNave().getEquipaggio()
									- ((NaveAbbandonata) carta).getEquipaggioPerso());
							giocatori.get(i).getNave().setCrediti(giocatori.get(i).getNave().getCrediti()
									+ ((NaveAbbandonata) carta).getCreditiGuadagnati());
							break;
						}
					} else {
						System.out.println(giocatori.get(i).getColore() + giocatori.get(i).getNome() + "\u001B[0m"
								+ " non ha abbastanza equipaggio per prendere la nave abbandonata.");
					}
				}
			} else if (carta instanceof Pianeti) {
				int pianeti[][] = ((Pianeti) carta).getPianeti();

				for (int i = 0; i < giocatori.size(); i++) {
					if (pianeti.length != 0) {
						for (int s = 0; s < pianeti.length; s++) {
							System.out.println("Pianeta " + (s + 1) + ":");
							System.out.println("Giorni persi: " + ((Pianeti) carta).getGiorniPersi());
							System.out.println("Livello merci:");
							for (int j = 0; j < pianeti[s].length; j++) {
								System.out.println("\t- " + pianeti[s][j]);

							}
							System.out.println("\n");
						}
						System.out.println(giocatori.get(i).getColore() + giocatori.get(i).getNome() + "\u001B[0m"
								+ " vuoi scendere in uno dei pianeti? (si/no)");

						scanner.nextLine();
						String r = scanner.nextLine();

						if (r.equals("si")) {
							System.out.println("In quale pianeta vuoi andare?");
							int numeroP = -1;
							do {
								if (scanner.hasNextInt())
									numeroP = scanner.nextInt();
								else
									scanner.next();
							} while (numeroP < 0 || numeroP > 4);

							int[] merciGuadagnate = pianeti[numeroP - 1];

							// Guadagna merci
							giocatori.get(i).getNave().guadagnaMerci(merciGuadagnate);

							// perde giorni
							giocatori.get(i).getNave().setPosizione(
									giocatori.get(i).getNave().getPosizione()
											- ((Pianeti) carta).getGiorniPersi());

							int pianeti2[][] = new int[pianeti.length - 1][pianeti[0].length];
							int conta = 0;
							for (int s = 0; s < pianeti.length; s++) {
								if (s != numeroP - 1) {
									pianeti2[conta] = pianeti[s];
									conta++;
								}
							}
							pianeti = pianeti2;
						}
					}
				}
			} else if (carta instanceof PioggiaMeteoriti) {
				Meteorite meteoriti[] = ((PioggiaMeteoriti) carta).getMeteoriti();
				for (int j = 0; j < meteoriti.length; j++) {
					int posizione = (int) (Math.random() * 10) + 2; // posizione casuale tra 2 e 11
					Direzione dir = meteoriti[j].getDirezione();
					Dimensione dim = meteoriti[j].getGrandezza();

					System.out.println("Sta arrivando un meteorite "
							+ (dim == Dimensione.METEORITE_GRANDE ? "grande" : "piccolo") + " da " + dir
							+ " alla posizione " + (posizione + 1) + "\n");

					for (int i = 0; i < giocatori.size(); i++) {
						Nave nave = giocatori.get(i).getNave();

						if (dir == Direzione.SOPRA) {
							for (int t = 0; t < 12; t++) {
								if (nave.getComponente(t, posizione) != null) {
									if (dim == Dimensione.METEORITE_GRANDE) {
										if (nave.getComponente(t, posizione) instanceof Cannone) {
											if ((((Cannone) nave.getComponente(t, posizione))
													.getOrientazione() == Orientazione.NORD && dir == Direzione.SOPRA)
													||
													(((Cannone) nave.getComponente(t, posizione))
															.getOrientazione() == Orientazione.OVEST
															&& dir == Direzione.SINISTRA)
													|| (((Cannone) nave.getComponente(t, posizione))
															.getOrientazione() == Orientazione.EST
															&& dir == Direzione.DESTRA)
													|| (((Cannone) nave.getComponente(t, posizione))
															.getOrientazione() == Orientazione.SUD
															&& dir == Direzione.SOTTO)) {

												System.out.println(
														giocatori.get(i).getColore() + giocatori.get(i).getNome()
																+ "\u001B[0m" + " vuoi sparare al meteorite? (si/no)");

												scanner.nextLine();
												String r = scanner.nextLine();
												if (r.equals("si")) {
													System.out.println("il meteorite è esploso");
													break;
												} else {
													nave.distruggiComponente(t, posizione);
													break; // Esce dal ciclo dopo aver distrutto il primo componente
												}
											}
										}
									} else if (dim == Dimensione.METEORITE_PICCOLO) {
										if (nave.getComponente(t, posizione)
												.getConnettore(Orientazione.NORD) != Connettori.NIENTE) {
											if (nave.presenzaScudo(Orientazione.NORD) == false) {
												nave.distruggiComponente(t, posizione);
												break; // Esce dal ciclo dopo aver distrutto il primo componente
											} else {
												System.out.println("il meteorite rimbalza sullo scudo");
												break;
											}
										}
									}
								}

							}
						} else if (dir == Direzione.DESTRA) {
							for (int t = 11; t >= 0; t--) {
								if (nave.getComponente(posizione, t) != null) {
									if (dim == Dimensione.METEORITE_GRANDE) {
										if (nave.getComponente(posizione, t) instanceof Cannone) {
											System.out.println(giocatori.get(i).getColore() + giocatori.get(i).getNome()
													+ "\u001B[0m" + " vuoi sparare al meteorite? (si/no)");

											scanner.nextLine();
											String r = scanner.nextLine();
											if (r.equals("si")) {
												System.out.println("il meteorite è esploso");
												break;
											} else {
												nave.distruggiComponente(posizione, t);
												break; // Esce dal ciclo dopo aver distrutto il primo componente
											}
										}
									} else if (dim == Dimensione.METEORITE_PICCOLO) {
										if (nave.getComponente(posizione, t)
												.getConnettore(Orientazione.EST) != Connettori.NIENTE) {
											if (nave.presenzaScudo(Orientazione.EST) == false) {
												nave.distruggiComponente(posizione, t);
												break;
											} else {
												System.out.println("il meteorite rimbalza sullo scudo");
												break;
											}
										}
									}
								}

							}
						} else if (dir == Direzione.SOTTO) {
							for (int t = 11; t >= 0; t--) {
								if (nave.getComponente(t, posizione) != null) {
									if (dim == Dimensione.METEORITE_GRANDE) {
										if (nave.getComponente(t, posizione) instanceof Cannone) {
											System.out.println(giocatori.get(i).getColore() + giocatori.get(i).getNome()
													+ "\u001B[0m" + " vuoi sparare al meteorite? (si/no)");

											scanner.nextLine();
											String r = scanner.nextLine();
											if (r.equals("si")) {
												System.out.println("il meteorite è esploso");
												break;
											} else {
												nave.distruggiComponente(t, posizione);
												break;
											}
										}
									} else if (dim == Dimensione.METEORITE_PICCOLO) {
										if (nave.getComponente(t, posizione)
												.getConnettore(Orientazione.SUD) != Connettori.NIENTE) {
											if (nave.presenzaScudo(Orientazione.SUD) == false) {
												nave.distruggiComponente(t, posizione);
												break;
											} else {
												System.out.println("il meteorite rimbalza sullo scudo");
												break;
											}
										}
									}
								}

							}
						} else if (dir == Direzione.SINISTRA) {
							for (int t = 0; t < 12; t++) {
								if (nave.getComponente(posizione, t) != null) {
									if (dim == Dimensione.METEORITE_GRANDE) {
										if (nave.getComponente(posizione, t) instanceof Cannone) {
											System.out.println(giocatori.get(i).getColore() + giocatori.get(i).getNome()
													+ "\u001B[0m" + " vuoi sparare al meteorite? (si/no)");

											scanner.nextLine();
											String r = scanner.nextLine();
											if (r.equals("si")) {
												System.out.println("il meteorite è esploso");
												break;
											} else {
												nave.distruggiComponente(posizione, t);
												break;
											}
										}
									} else if (dim == Dimensione.METEORITE_PICCOLO) {
										if (nave.getComponente(posizione, t)
												.getConnettore(Orientazione.OVEST) != Connettori.NIENTE) {
											if (nave.presenzaScudo(Orientazione.OVEST) == false) {
												nave.distruggiComponente(posizione, t);
												break;
											} else {
												System.out.println("il meteorite rimbalza sullo scudo");
												break;
											}
										}
									}
								}

							}
						}
					}

				}

			} else if (carta instanceof Pirati) {
				for (int i = 0; i < giocatori.size(); i++) {
					Nave nave = giocatori.get(i).getNave();

					System.out.println("Potenza di fuoco necessaria per affrontare i pirati: "
							+ ((Pirati) carta).getPotenzaFuoco() + "\n");

					if (nave.getPotenzaFuoco() >= (double) ((Pirati) carta).getPotenzaFuoco()) {
						System.out.println(giocatori.get(i).getColore() + giocatori.get(i).getNome() + "\u001B[0m"
								+ " ha abbastanza potenza di fuoco per affrontare i pirati quindi guadagna crediti e perde giorni.");
						nave.setPosizione(nave.getPosizione() - ((Pirati) carta).getGiorniPersi());
						nave.setCrediti(nave.getCrediti() + ((Pirati) carta).getCreditiGuadagnati());
						break; // esce dal ciclo dopo che un giocatore ha affrontato i pirati
					} else {
						System.out.println(giocatori.get(i).getColore() + giocatori.get(i).getNome() + "\u001B[0m"
								+ " non ha abbastanza potenza di fuoco per affrontare i pirati quindi riceve delle cannonate.");
						nave.prendiCannonate(((Pirati) carta).getCannonate());
					}
				}

			} else if (carta instanceof PolvereStellare) {
				for (int i = 0; i < giocatori.size(); i++) {
					System.out.println(giocatori.get(i).getColore() + giocatori.get(i).getNome() + "\u001B[0m"
							+ " perde " + ((PolvereStellare) carta).giorniPersi(giocatori.get(i).getNave())
							+ " giorni.");

					giocatori.get(i).getNave().setPosizione(giocatori.get(i).getNave().getPosizione()
							- ((PolvereStellare) carta).giorniPersi(giocatori.get(i).getNave()));
				}

			} else if (carta instanceof Sabotaggio) {
				for (int i = 0; i < giocatori.size(); i++) {
					((Sabotaggio) carta).esegui(giocatori.get(i).getNave());
				}

			} else if (carta instanceof Schiavisti) {
				for (int i = 0; i < giocatori.size(); i++) {
					if (giocatori.get(i).getNave()
							.getPotenzaFuoco() >= (double) (((Schiavisti) carta).getPotenzaFuoco())) {
						System.out.println(giocatori.get(i).getColore() + giocatori.get(i).getNome() + "\u001B[0m"
								+ " vuoi guadagnare" + ((Schiavisti) carta).getCreditiGuadagnati()
								+ " crediti e perdere " + ((Schiavisti) carta).getGiorniPersi() + " giorni? (si/no)");

						scanner.nextLine();

						String r = scanner.nextLine();
						if (r.equals("si")) {
							giocatori.get(i).getNave().setPosizione(
									giocatori.get(i).getNave().getPosizione() - ((Schiavisti) carta).getGiorniPersi());
							giocatori.get(i).getNave().setCrediti(
									giocatori.get(i).getNave().getCrediti()
											+ ((Schiavisti) carta).getCreditiGuadagnati());
							break;
						}

					} else {
						System.out.println(giocatori.get(i).getColore() + giocatori.get(i).getNome() + "\u001B[0m"
								+ " non ha abbastanza potenza di fuoco, quindi perde equipaggio.");
						giocatori.get(i).getNave().setEquipaggio(
								giocatori.get(i).getNave().getEquipaggio() - ((Schiavisti) carta).getEquipaggioPerso());
					}

				}
			} else if (carta instanceof SpazioAperto) {
				for (int i = 0; i < giocatori.size(); i++) {
					((SpazioAperto) carta).esegui(giocatori.get(i).getNave(), tabellone,
							giocatori.get(i).getNave().getPotenzaMotrice());
				}
			} else if (carta instanceof StazioneAbbandonata) {
				for (int i = 0; i < giocatori.size(); i++) {
					if (giocatori.get(i).getNave().getEquipaggio() >= ((StazioneAbbandonata) carta)
							.getEquipaggioNec()) {
						System.out.println(giocatori.get(i).getColore() + giocatori.get(i).getNome() + "\u001B[0m"
								+ " vuoi perdere giorni e guadagnare merci? (si/no)");

						scanner.nextLine();
						String r = scanner.nextLine();

						if (r.equals("si")) {
							giocatori.get(i).getNave().setPosizione(giocatori.get(i).getNave().getPosizione()
									- ((StazioneAbbandonata) carta).getGiorniPersi());

							int[] merciGuadagnate = ((StazioneAbbandonata) carta).getMerciGuadagnate();

							// Guadagna merci
							giocatori.get(i).getNave().guadagnaMerci(merciGuadagnate);
						}
						break; // esce dal ciclo dopo che un giocatore ha affrontato la stazione abbandonata
					} else {
						System.out.println(giocatori.get(i).getColore() + giocatori.get(i).getNome() + "\u001B[0m"
								+ " non ha abbastanza equipaggio per affrontare la stazione abbandonata.");
					}
				}
			} else if (carta instanceof ZonaGuerra) {
				((ZonaGuerra) carta).esegui(tabellone);
			}

			tabellone.controllaSconfitte(giocatori);
			if (tabellone.controllaVittorie(giocatori)) {
				System.out.println("Il gioco è terminato.");
				return; // se c'è un vincitore o sono tutti sconfitti esce dalla funzione
			}
			// Riordina i giocatori in base alla posizione della nave
			tabellone.riordinaGiocatori(giocatori);

			// Stampa le varie informazioni del gioco
			System.out.println(tabellone.toString());

			System.out.println("\n" + "Premi un tasto per continuare" + "\n");
			scanner.nextLine();
		}
	}
}
