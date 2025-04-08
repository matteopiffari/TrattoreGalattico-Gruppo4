package entities;

public class Dado {
    public int tira() {
        return (int) (Math.random() * 6) + 1;
    }
}
