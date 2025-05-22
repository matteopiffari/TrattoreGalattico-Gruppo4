package ship.components;

//enum da utilizzare per i componenti
public enum Orientazione {
    NORD, EST, SUD, OVEST;

    public Orientazione ruota() {
        return values()[(this.ordinal() + 1) % values().length];
    }
}
