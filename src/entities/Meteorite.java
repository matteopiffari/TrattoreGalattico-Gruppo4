package entities;

public class Meteorite {
    private Direzione direzione;
    private Dimensione grandezza;

    public Meteorite() {
        direzione = Direzione.values()[(int) (Math.random() * Direzione.values().length)];
        grandezza = Math.random() < 0.5 ? Dimensione.METEORITE_PICCOLO : Dimensione.METEORITE_GRANDE;
    }

    public Direzione getDirezione() {
        return direzione;
    }

    public Dimensione getGrandezza() {
        return grandezza;
    }
}
