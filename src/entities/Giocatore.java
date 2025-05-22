package entities;

import ship.Nave;

public class Giocatore {
    private Nave nave;
    private int soldi;
    private String colore;
    private String nome;

    public Giocatore(String nome, String colore){
        this.colore=colore;
        this.nome=nome;
        this.nave=new Nave();
    }

    public Nave getNave() {
        return nave;
    }

    public void setNave(Nave nave) {
        this.nave = nave;
    }

    public int getSoldi() {
        return soldi;
    }

    public void setSoldi(int soldi) {
        this.soldi = soldi;
    }

    public String getColore() {
        return colore;
    }

    public void setColore(String colore) {
        this.colore = colore;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
