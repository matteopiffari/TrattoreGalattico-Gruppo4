package ship;

import ship.components.Componente;

public class Nave {
    private int umani;
    private int alieni;

    private Componente[][] nave;

    public Nave() {
        this.nave = new Componente[10][10]; // Dimensione dimostrativa, cambiare in base alle necessità
        for (int i = 0; i < nave.length; i++) {
            for (int j = 0; j < nave[i].length; j++) {
                nave[i][j] = null;
            }
        }
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
