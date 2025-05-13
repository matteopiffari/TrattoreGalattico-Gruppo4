package cards;

public class Cards {
    private String nome;
    private int valore1;
    private int valore2;
    private int valore3;
    private int valore4;
    private int valore5;

    // Constructor matching the required signature
    public Carta(String nome, int valore1, int valore2, int valore3, int valore4, int valore5) {
        this.nome = nome;
        this.valore1 = valore1;
        this.valore2 = valore2;
        this.valore3 = valore3;
        this.valore4 = valore4;
        this.valore5 = valore5;
    }

    // Getters and other methods (if needed)
    public String getNome() {
        return nome;
    }
}