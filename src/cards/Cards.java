package cards;

public class Cards {
    private String nome;
    private int valore1;
    private int valore2;
    private int valore3;
    private int valore4;
    private int valore5;

    // costruttore di default 
    public Carta(String nome, int valore1, int valore2, int valore3, int valore4, int valore5) {
        this.nome = nome;
        this.valore1 = valore1;
        this.valore2 = valore2;
        this.valore3 = valore3;
        this.valore4 = valore4;
        this.valore5 = valore5;
    }

    // metodi getter
    public int getValore1() {
        return valore1;
    }
    public int getValore2() {
        return valore2;
    }
    public int getValore3() {
        return valore3;
    }
    public int getValore4() {
        return valore4;
    }
    public int getValore5() {
        return valore5;
    }
    // metodo per ottenere il nome della carta
    // questo metodo è stato aggiunto per ottenere il nome della carta
    // in modo da poterlo utilizzare in altre classi
    // come ad esempio nella classe Giocatore
    // in modo da poterlo utilizzare per confrontare le carte
    // e per stampare il nome della carta
    // in modo da poterlo utilizzare per stampare il nome della carta   
    public String getNome() {
        return nome;
    }
}