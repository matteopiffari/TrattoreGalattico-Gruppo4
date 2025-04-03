package ship.components;

public class Component {
    private Side orientation;
    private final Connectors[] connectors;
    private int x;
    private int y;

    public Component(Connectors[] connectors) {
        this.orientation = Side.UP;
        this.connectors = connectors;
    }

    // #region Getters and Setters
    // #region Position

    public int[] getPosition() {
        return new int[] { x, y };
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // #endregion

    // #region Orientation

    public Side getOrientation() {
        return orientation;
    }

    public void setOrientation(Side orientation) {
        this.orientation = orientation;
    }

    // #endregion

    // #region Connectors

    public Connectors[] getConnectors() {
        return connectors;
    }

    public Connectors getConnector(Side orientation) {
        return connectors[orientation.ordinal()];
    }

    // #endregion
    // #endregion
}
