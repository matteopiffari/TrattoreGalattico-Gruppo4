package ship.components;

public enum Orientazione {
    SOPRA, DESTRA, SOTTO, SINISTRA;

    public Orientazione ruota() {
        return values()[(this.ordinal() + 1) % values().length];
    }
}
