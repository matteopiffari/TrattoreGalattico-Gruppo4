package cards;

public class SpazioAperto extends Carta {
    public SpazioAperto() {
        super("Spazio Aperto", 0, 0, 0, 0, 0);
    }

    @Override
    public String toString() {
        return "Spazio Aperto";
    }

    @Override
    public String getTipo() {
        return "Spazio Aperto";
    }

    @Override
    public String getDescrizione() {
        return "Carta che rappresenta uno spazio aperto.";
    }
	

}
