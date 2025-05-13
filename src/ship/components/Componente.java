package ship.components;

import ship.Nave;

public abstract class Componente {
    private Orientazione orientazione;
    private final Connettori[] connettori;
    private int x;
    private int y;

    public Componente(Connettori[] connettori) {
        this.orientazione = Orientazione.NORD;
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

    public abstract boolean posizionabile(Nave nave, int x, int y);
}
