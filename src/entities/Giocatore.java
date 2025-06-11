package entities;

import ship.Nave;
import ship.components.*;

public class Giocatore {
    private Nave nave;
    private String colore;
    private String nome;

    public Giocatore(String nome, String colore, int lvl) {
        this.colore = colore;
        this.nome = nome;
        this.nave = new Nave(lvl);
        Connettori conn[] = {Connettori.UNIVERSALE, Connettori.UNIVERSALE, Connettori.UNIVERSALE, Connettori.UNIVERSALE};
        Componente c=new CabinaCentrale(conn);
        nave.setComponente(6, 6, c);
      
        
    }

    public Nave getNave() {
        return nave;
    }

    public void setNave(Nave nave) {
        this.nave = nave;
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
