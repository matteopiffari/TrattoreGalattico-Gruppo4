package ship.components;

public enum Side {
    UP, RIGHT, DOWN, LEFT;

    public Side rotate() {
        return values()[(this.ordinal() + 1) % values().length];
    }
}
