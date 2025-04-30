package ship;

import entities.Mazzo;
import entities.PallaCannone;
import ship.components.Componente;

public class Nave {
    private int umani = 3;
    private int alieni = 0;

    private Componente[][] nave;

    public Nave(int livello) {
        this.nave = new Componente[12][12]; // Dimensione dimostrativa, cambiare in base alle necessità
        
        if(livello==1){
            for (int i = 0; i < nave.length; i++) {
                for (int j = 0; j < nave[i].length; j++) {
                    nave[i][j] = ;                  //DA CAMBIARE!!!
                }
            }
        }
        
        for (int i = 0; i < nave.length; i++) {
            for (int j = 0; j < nave[i].length; j++) {
                nave[i][j] = ;                  //DA CAMBIARE!!!
            }
        }
    }

    public void costruisciNave() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        boolean pesca = true;

        // Logica per posizionare componenti nella nave
        while (pesca == true) {
            Componente c = mazzoComponenti.pesca(); // Pesca un componente dal mazzo
            if (c == null) {
                break; // Esci dal ciclo se non ci sono più componenti nel mazzo
            }
            System.out.println("hai pescato: " + c.toString() + " vuoi tenerlo? (si/no)");

            String risposta = scanner.nextLine().trim().toLowerCase();
            if (risposta.equals("si")) {
                System.out.println("Dove vuoi posizionarlo? Inserisci riga e colonna (es. 2 3):");
                int riga = scanner.nextInt();
                int colonna = scanner.nextInt();
                scanner.nextLine(); // Consuma il newline rimasto
                if (nave[riga][colonna] != null) { // creare e integrare forma effettiva della nave!!!
                    nave[riga][colonna] = c;
                    System.out.println("Componente posizionato in [" + riga + "][" + colonna + "].");
                    mazzoComponenti.remove(c);
                } else {
                    System.out.println("Posizione non valida.");
                }
            } else {
                System.out.println("Componente scartato.");
            }
        }
    }

    public void perdiMerci(int merci) {
        // Logica per perdere merci
    }

    public void perdiEquipaggio(int equipaggio) {
        // Logica per perdere equipaggio
    }

    public void prendiCannonate(PallaCannone[] cannonate) {
        // Logica per prendere cannonate
    }

    public int getPosizione() {
        return 0; // Restituisce la posizione della nave
    }

    public Componente[][] getNave() {
        return nave; // Restituisce la matrice della nave
    }

    public void distruggiComponente(int x, int y) {
        if (nave[x][y] != null) {
            nave[x][y] = null; // Distruggi il componente
        }
    }

    public boolean isEmpty(int x, int y) {
        return nave[x][y] == null; // Controlla se la posizione è vuota
    }

    public int contaConnettori() {
        return 0; // Logica per contare i connettori
    }

    @Override
    public String toString() {
        String s = "La tua nave:\n";
        s += "Equipaggio: " + umani + "\nAlieni: " + alieni + "\n\n";
        for (int i = 0; i < nave.length; i++) {
            for (int j = 0; j < nave[i].length; j++) {
                if (nave[i][j] != null) {
                    s += nave[i][j].toString() + "\t";
                } else {
                    s += "Vuoto\t";
                }
            }
            s += "\n";
        }
        return s;
    }

}
