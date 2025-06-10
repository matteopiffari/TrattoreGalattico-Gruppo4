package logica;

import java.util.ArrayList;

import cards.*;
import entities.Dimensione;
import entities.Direzione;
import entities.Giocatore;
import entities.Mazzo;
import entities.Meteorite;
import entities.PallaCannone;
import entities.Tabellone;
import ship.Nave;
import ship.components.*;

public class InizioGioco {
	public void inizia( ArrayList<Giocatore> giocatori, Tabellone tabellone, Mazzo<Carta> mazzoCarte) {
		// Logica per iniziare il gioco
		System.out.println("Il gioco sta iniziando...");
		// Qui si possono aggiungere ulteriori logiche di inizializzazione
		Carta carta;
		try {
			carta=mazzoCarte.pescaCarta();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			return ;   //se c'è eccezione esce dalla funzione
		}


		ArrayList<Componente> componenti = giocatori.get(i).getNave().getComponentiSpecifici(Stiva.class);
		if (carta instanceof Contrabbandieri) {
			for (int i=0; i<giocatori.size(); i++) {
				//prima casta carta in contrabbandieri, ottiene potenza di fuoco e casta valore di ritorno in double
				if (giocatori.get(i).getNave().getPotenzaFuoco()>=(double)(((Contrabbandieri)carta).getPotenzaFuoco())) { 
					int[] merciGuadagnate=((Contrabbandieri)carta).getMerciGuadagnate();

					for(int t=0; t<merciGuadagnate.length; t++) {
						if(merciGuadagnate[t]==4) {
							for(int s=0; s<componenti.size(); s++ ) {
								if (componenti.get(s) instanceof StivaSpeciale) {
									//controlla se in stive speciali c'è posto per mettere merci di livello 4
									boolean controllo=((StivaSpeciale)componenti.get(s)).setMerci(merciGuadagnate[t]);
									if (controllo==true) {
										merciGuadagnate[t]= - 1;
										break;
									}	
								}	
							}
						}else {
							for(int s=0; s<componenti.size(); s++ ) {
								if (!(componenti.get(s) instanceof StivaSpeciale)) {
									//controlla se in stive normali c'è posto per mettere merci
									boolean controllo=((Stiva)componenti.get(s)).setMerci(merciGuadagnate[t]);
									if (controllo==true) {
										merciGuadagnate[t]= - 1;
										break;
									}	
								}	
							}
						}	
					}
					for (int t=0; t<merciGuadagnate.length; t++) {
						//se merci non sono state messe in stive normali controllo se c'è posto in stive speciali 
						if(merciGuadagnate[t]!= -1) {
							for(int s=0; s<componenti.size(); s++ ) {
								if (componenti.get(s) instanceof StivaSpeciale) {
									boolean controllo=((StivaSpeciale)componenti.get(s)).setMerci(merciGuadagnate[t]);
									if (controllo==true) {
										merciGuadagnate[t]= - 1;
										break;
									}	
								}	
							}
						}
					}
					//perde giorni 
					giocatori.get(i).getNave().setPosizione(giocatori.get(i).getNave().getPosizione()-((Contrabbandieri)carta).getGiorniPersi());
					break;
				}
				else {
					for (int t=0; t<((Contrabbandieri)carta).getMerciPerse(); t++) {
						for(int s=0; s<componenti.size(); s++ ) {
							if (componenti.get(s) instanceof StivaSpeciale) {
								boolean controllo=((StivaSpeciale)componenti.get(s)).perdiMerci();
								if(controllo==true) 
									break;
							} else {
								boolean controllo=((Stiva)componenti.get(s)).perdiMerci();
								if(controllo==true) 
									break;
							}
						}	
					}
				}

			}
		} else if (carta instanceof Epidemia) {
			for (int i=0; i<giocatori.size(); i++) {
				((Epidemia) carta).esegui(giocatori.get(i).getNave());
			}

		}else if (carta instanceof NaveAbbandonata) {
			for (int i=0; i<giocatori.size(); i++) {
				if (giocatori.get(i).getNave().getEquipaggio()>((NaveAbbandonata)carta).getEquipaggioPerso()) { 
					System.out.println("Vuoi perdere "+ ((NaveAbbandonata)carta).getEquipaggioPerso()+" equipaggio e "+((NaveAbbandonata)carta).getGiorniPersi()+" giorni per guadagnare " + ((NaveAbbandonata)carta).getCreditiGuadagnati()+ " crediti? (si/no)");
					java.util.Scanner scanner = new java.util.Scanner(System.in);
					scanner.nextLine();
					String r = scanner.nextLine();
					if (r.equals("si")) {
						giocatori.get(i).getNave().setPosizione(giocatori.get(i).getNave().getPosizione()-((NaveAbbandonata)carta).getGiorniPersi());
						giocatori.get(i).getNave().setEquipaggio(giocatori.get(i).getNave().getEquipaggio()-((NaveAbbandonata)carta).getEquipaggioPerso());
						giocatori.get(i).getNave().setCrediti(giocatori.get(i).getNave().getCrediti()+((NaveAbbandonata)carta).getCreditiGuadagnati());
						break;
					}
				} 
			}	
		}else if (carta instanceof Pianeti) {
			int pianeti[][]=((Pianeti)carta).getPianeti();

			for (int i=0; i<giocatori.size(); i++) {
				if(pianeti.length==0) {
					for (int s=0; s<pianeti.length; s++) {
						System.out.println("Pianeta "+ (s+1) +" ");
						for(int j=0; j<pianeti[s].length;j++) {
							System.out.println(pianeti[s][j]+" ");

						}
						System.out.println("\n");
					}
					System.out.println(giocatori.get(i).getNome() + " vuoi scendere in uno dei pianeti? (si/no)");
					java.util.Scanner scanner = new java.util.Scanner(System.in);
					scanner.nextLine();
					String r = scanner.nextLine();
					if (r.equals("si")) {
						System.out.println("In quale pianeta vuoi andare?");
						int numeroP;
						do {
							if (scanner.hasNextInt())
								numeroP = scanner.nextInt();
							else
								scanner.next();
						} while (numeroP < 0 || numeroP > 4);
						int[] merciGuadagnate=pianeti[numeroP];

						for(int t=0; t<merciGuadagnate.length; t++) {
							if(merciGuadagnate[t]==4) {
								for(int s=0; s<componenti.size(); s++ ) {
									if (componenti.get(s) instanceof StivaSpeciale) {
										//controlla se in stive speciali c'è posto per mettere merci di livello 4
										boolean controllo=((StivaSpeciale)componenti.get(s)).setMerci(merciGuadagnate[t]);
										if (controllo==true) {
											merciGuadagnate[t]= - 1;
											break;
										}	
									}	
								}
							}else {
								for(int s=0; s<componenti.size(); s++ ) {
									if (!(componenti.get(s) instanceof StivaSpeciale)) {
										//controlla se in stive normali c'è posto per mettere merci
										boolean controllo=((Stiva)componenti.get(s)).setMerci(merciGuadagnate[t]);
										if (controllo==true) {
											merciGuadagnate[t]= - 1;
											break;
										}	
									}	
								}
							}	
						}
						for (int t=0; t<merciGuadagnate.length; t++) {
							//se merci non sono state messe in stive normali controllo se c'è posto in stive speciali 
							if(merciGuadagnate[t]!= -1) {
								for(int s=0; s<componenti.size(); s++ ) {
									if (componenti.get(s) instanceof StivaSpeciale) {
										boolean controllo=((StivaSpeciale)componenti.get(s)).setMerci(merciGuadagnate[t]);
										if (controllo==true) {
											merciGuadagnate[t]= - 1;
											break;
										}	
									}	
								}
							}
						}
						//perde giorni 
						giocatori.get(i).getNave().setPosizione(giocatori.get(i).getNave().getPosizione()-((Contrabbandieri)carta).getGiorniPersi());

						int pianeti2[][]=new int[pianeti.length-1][pianeti[0].length];
						int conta=0;
						for (int s=0; s<pianeti.length; s++) {
							if(s!=numeroP-1) {
								pianeti2[conta]=pianeti[s];
								conta++;
							}
						}
						pianeti=pianeti2;
					}
				}	
			}
		} else if (carta instanceof PioggiaMeteoriti) {
			Meteorite meteoriti[]=((PioggiaMeteoriti)carta).getMeteoriti();
			for (int i=0; i<giocatori.size(); i++) {
				Nave nave = giocatori.get(i).getNave();
				for (int j=0; j<meteoriti.length; j++) {
					Direzione dir = meteoriti[j].getDirezione();
					Dimensione dim = meteoriti[j].getGrandezza();
					int posizione=(int) (Math.random() * 11) + 2;
					if (dir==Direzione.SOPRA) {
						for (int t=0; t<12; t++) {
							if(nave.getComponente(t, posizione) != null) {
								if (dim==Dimensione.METEORITE_GRANDE) {
									if (nave.getComponente(t, posizione) instanceof Cannone) {
										System.out.println("vuoi sparare al meteorite? (si/no)");
										java.util.Scanner scanner = new java.util.Scanner(System.in);
										scanner.nextLine();
										String r = scanner.nextLine();
										if (r.equals("si")) {
											System.out.println("il meteorite è esploso");
										}else {
											nave.distruggiComponente(t, posizione);
										}
									}
								}else if(dim==Dimensione.METEORITE_PICCOLO) {
									if (nave.getComponente(t, posizione).getConnettore(Orientazione.NORD) != Connettori.NIENTE) {
										if(nave.presenzaScudo(Orientazione.NORD)== false) {
											nave.distruggiComponente(t, posizione);
										}else {
											System.out.println("il meteorite rimbalza sullo scudo"); 
										}
									}
								}
							}

						}
					}else if(dir==Direzione.DESTRA) {
						for (int t=11; t>=0; t--) {
							if(nave.getComponente(posizione,t) != null) {
								if (dim==Dimensione.METEORITE_GRANDE) {
									if (nave.getComponente(posizione,t) instanceof Cannone) {
										System.out.println("vuoi sparare al meteorite? (si/no)");
										java.util.Scanner scanner = new java.util.Scanner(System.in);
										scanner.nextLine();
										String r = scanner.nextLine();
										if (r.equals("si")) {
											System.out.println("il meteorite è esploso");
										}else {
											nave.distruggiComponente(posizione,t);
										}
									}
								}else if(dim==Dimensione.METEORITE_PICCOLO) {
									if (nave.getComponente(posizione,t).getConnettore(Orientazione.EST) != Connettori.NIENTE) {
										if(nave.presenzaScudo(Orientazione.EST)== false) {
											nave.distruggiComponente(posizione,t);
										}else {
											System.out.println("il meteorite rimbalza sullo scudo"); 
										}
									}
								}
							}

						}
					}else if(dir==Direzione.SOTTO) {
						for (int t=11; t>=0; t--) {
							if(nave.getComponente(t, posizione) != null) {
								if (dim==Dimensione.METEORITE_GRANDE) {
									if (nave.getComponente(t, posizione) instanceof Cannone) {
										System.out.println("vuoi sparare al meteorite? (si/no)");
										java.util.Scanner scanner = new java.util.Scanner(System.in);
										scanner.nextLine();
										String r = scanner.nextLine();
										if (r.equals("si")) {
											System.out.println("il meteorite è esploso");
										}else {
											nave.distruggiComponente(t, posizione);
										}
									}
								}else if(dim==Dimensione.METEORITE_PICCOLO) {
									if (nave.getComponente(t, posizione).getConnettore(Orientazione.SUD) != Connettori.NIENTE) {
										if(nave.presenzaScudo(Orientazione.SUD)== false) {
											nave.distruggiComponente(t, posizione);
										}else {
											System.out.println("il meteorite rimbalza sullo scudo"); 
										}
									}
								}
							}

						}
					}else if(dir==Direzione.SINISTRA) {
						for (int t=0; t<12; t++) {
							if(nave.getComponente(posizione,t) != null) {
								if (dim==Dimensione.METEORITE_GRANDE) {
									if (nave.getComponente(posizione,t) instanceof Cannone) {
										System.out.println("vuoi sparare al meteorite? (si/no)");
										java.util.Scanner scanner = new java.util.Scanner(System.in);
										scanner.nextLine();
										String r = scanner.nextLine();
										if (r.equals("si")) {
											System.out.println("il meteorite è esploso");
										}else {
											nave.distruggiComponente(posizione,t);
										}
									}
								}else if(dim==Dimensione.METEORITE_PICCOLO) {
									if (nave.getComponente(posizione,t).getConnettore(Orientazione.OVEST) != Connettori.NIENTE) {
										if(nave.presenzaScudo(Orientazione.OVEST)== false) {
											nave.distruggiComponente(posizione,t);
										}else {
											System.out.println("il meteorite rimbalza sullo scudo"); 
										}
									}
								}
							}

						}
					}
				}

			}

		}else if (carta instanceof Pirati) {
			for (int i=0; i<giocatori.size(); i++) {
				Nave nave = giocatori.get(i).getNave();
				if (nave.getPotenzaFuoco()>=(double)((Pirati)carta).getPotenzaFuoco()) {
					nave.setPosizione(nave.getPosizione()-((Pirati)carta).getGiorniPersi());  
					nave.setCrediti(nave.getCrediti()+((Pirati)carta).getCreditiGuadagnati());
				}else {
					PallaCannone cannonate[]=((Pirati)carta).getCannonate();
					for (int j=0; j<cannonate.length; j++) {
						Direzione dir = cannonate[j].getDirezione();
						Dimensione dim = cannonate[j].getGrandezza();
						int posizione=(int) (Math.random() * 11) + 2;
						if (dir==Direzione.SOPRA) {
							for (int t=0; t<12; t++) {
								if(nave.getComponente(t, posizione) != null) {
									if (dim==Dimensione.PALLA_CANNONE_GRANDE) {
										nave.distruggiComponente(t, posizione);
									}else if(dim==Dimensione.PALLA_CANNONE_PICCOLA) {
										if(nave.presenzaScudo(Orientazione.NORD)== false) {
											nave.distruggiComponente(t, posizione);
										}else {
											System.out.println("la palla di cannone rimbalza sullo scudo"); 

										}
									}
								}
							}		

						}else if(dir==Direzione.DESTRA) {
							for (int t=11; t>=0; t--) {
								if(nave.getComponente(posizione,t) != null) {
									if (dim==Dimensione.PALLA_CANNONE_GRANDE) {
										nave.distruggiComponente(posizione,t);
									}else if(dim==Dimensione.PALLA_CANNONE_PICCOLA) {
										if(nave.presenzaScudo(Orientazione.EST)== false) {
											nave.distruggiComponente(posizione,t);
										}else {
											System.out.println("la palla di cannone rimbalza sullo scudo"); 
										}
									}
								}
							}	
						}else if(dir==Direzione.SOTTO) {
							for (int t=11; t>=0; t--) {
								if(nave.getComponente(t, posizione) != null) {
									if (dim==Dimensione.PALLA_CANNONE_GRANDE) {
										nave.distruggiComponente(t, posizione);
									}else if(dim==Dimensione.PALLA_CANNONE_PICCOLA) {
										if(nave.presenzaScudo(Orientazione.SUD)== false) {
											nave.distruggiComponente(t, posizione);
										}else {
											System.out.println("la palla di cannone rimbalza sullo scudo"); 
										}
									}
								}
							}
						}else if(dir==Direzione.SINISTRA) {
							for (int t=0; t<12; t++) {
								if(nave.getComponente(posizione,t) != null) {
									if (dim==Dimensione.PALLA_CANNONE_GRANDE) {
										nave.distruggiComponente(posizione,t);
									}else if(dim==Dimensione.PALLA_CANNONE_PICCOLA) {
										if(nave.presenzaScudo(Orientazione.OVEST)== false) {
											nave.distruggiComponente(posizione,t);
										}else {
											System.out.println("la palla di cannone rimbalza sullo scudo"); 
										}
									}
								}
							}

						}
					}
				}
			}

		}else if (carta instanceof PolvereStellare) {
			for (int i=0; i<giocatori.size(); i++) {
				giocatori.get(i).getNave().setPosizione(giocatori.get(i).getNave().getPosizione()-((PolvereStellare)carta).giorniPersi(giocatori.get(i).getNave()));        	
			}

		}else if (carta instanceof Sabotaggio) {
			for (int i=0; i<giocatori.size(); i++) {
				((Sabotaggio) carta).esegui(giocatori.get(i).getNave());
			}

		}else if (carta instanceof Schiavisti) {
			for (int i=0; i<giocatori.size(); i++) {
				if (giocatori.get(i).getNave().getPotenzaFuoco()>=(double)(((Schiavisti)carta).getPotenzaFuoco())) { 
					System.out.println("Vuoi guadagnare" +((Schiavisti)carta).getCreditiGuadagnati())+" crediti e perdere "+((Schiavisti)carta).getGiorniPersi())+" giorni? (si/no)");
					java.util.Scanner scanner = new java.util.Scanner(System.in);
					scanner.nextLine();

					String r = scanner.nextLine();
					if (r.equals("si")) {
						giocatori.get(i).getNave().setPosizione(giocatori.get(i).getNave().getPosizione()-((Schiavisti)carta).getGiorniPersi());
						giocatori.get(i).getNave().setCrediti(giocatori.get(i).getNave().getCrediti()+((Schiavisti)carta).getCreditiGuadagnati());
						break;
					} 

				}else {
					giocatori.get(i).getNave().setEquipaggio(giocatori.get(i).getNave().getEquipaggio()-((Schiavisti)carta).getEquipaggioPerso());
				}

			}else if (carta instanceof SpazioAperto) {
				for (int i=0; i<giocatori.size(); i++) {
					((SpazioAperto) carta).esegui(giocatori.get(i).getNave(), tabellone , giocatori.get(i).getNave().getPotenzaMotrice());
				}

			}else if (carta instanceof StazioneAbbandonata) {
				for (int i=0; i<giocatori.size(); i++) {
					if (giocatori.get(i).getNave().getEquipaggio()>=((StazioneAbbandonata)carta).getEquipaggioNec()) { 
						System.out.println("Vuoi perdere giorni e guadagnare merci? (si/no)");
						java.util.Scanner scanner = new java.util.Scanner(System.in);
						scanner.nextLine();
						String r = scanner.nextLine();
						if (r.equals("si")) {
							giocatori.get(i).getNave().setPosizione(giocatori.get(i).getNave().getPosizione()-((StazioneAbbandonata)carta).getGiorniPersi());
							int[] merciGuadagnate=((StazioneAbbandonata)carta).getMerciGuadagnate();

							for(int t=0; t<merciGuadagnate.length; t++) {
								if(merciGuadagnate[t]==4) {
									for(int s=0; s<componenti.size(); s++ ) {
										if (componenti.get(s) instanceof StivaSpeciale) {
											boolean controllo=((StivaSpeciale)componenti.get(s)).setMerci(merciGuadagnate[t]);
											if (controllo==true) {
												merciGuadagnate[t]= - 1;
												break;
											}	
										}	
									}
								}else {
									for(int s=0; s<componenti.size(); s++ ) {
										if (!(componenti.get(s) instanceof StivaSpeciale)) {
											boolean controllo=((Stiva)componenti.get(s)).setMerci(merciGuadagnate[t]);
											if (controllo==true) {
												merciGuadagnate[t]= - 1;
												break;
											}	
										}	
									}
								}	
							}
							for (int t=0; t<merciGuadagnate.length; t++) {
								if(merciGuadagnate[t]!= -1) {
									for(int s=0; s<componenti.size(); s++ ) {
										if (componenti.get(s) instanceof StivaSpeciale) {
											boolean controllo=((StivaSpeciale)componenti.get(s)).setMerci(merciGuadagnate[t]);
											if (controllo==true) {
												merciGuadagnate[t]= - 1;
												break;
											}	
										}	
									}
								}
							}
						}
					}
				}	

			}else if (carta instanceof ZonaGuerra){
				((ZonaGuerra) carta).esegui(tabellone);
			}

		}
	}
