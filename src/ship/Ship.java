package ship;

import ship.components.Component;

public class Ship {
    private int humans;
    private int aliens;

    private Component[][] ship;

    public Ship() {
        this.ship = new Component[10][10]; // Example size, adjust as needed
        for (int i = 0; i < ship.length; i++) {
            for (int j = 0; j < ship[i].length; j++) {
                ship[i][j] = null;
            }
        }
    }

    @Override
    public String toString() {
        String s = "La tua nave:\n";
        s += "Equipaggio: " + humans + "\nAlieni: " + aliens + "\n\n";
        for (int i = 0; i < ship.length; i++) {
            for (int j = 0; j < ship[i].length; j++) {
                if (ship[i][j] != null) {
                    s += ship[i][j].toString() + "\t";
                } else {
                    s += "Vuoto\t";
                }
            }
            s += "\n";
        }
        return s;
    }
}
