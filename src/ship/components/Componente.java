package ship.components;

public class Componente {
    private Orientazione orientazione;
    private final Connettori[] connettori;
    private int x;
    private int y;

    public Componente(Connettori[] connettori) {
        this.orientazione = Orientazione.SOPRA;
        this.connettori = connettori;
    }

    // #region Getters and Setters
    // #region Posizione

    public int[] getPosizione() {
        return new int[] { x, y };
    }

    public void setPosizione(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // #endregion

    // #region Orientazione

    public Orientazione getOrientazione() {
        return orientazione;
    }

    public void setOrientazione(Orientazione orientazione) {
        this.orientazione = orientazione;
    }

    // #endregion

    // #region Connettori

    public Connettori[] getConnettori() {
        return connettori;
    }

    public Connettori getConnettore(Orientazione orientazione) {
        return connettori[orientazione.ordinal()];
    }

    // #endregion
    // #endregion
}
