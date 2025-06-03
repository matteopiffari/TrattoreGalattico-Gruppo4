package ship.components;

public class BatteriaTripla extends Batteria {

	public BatteriaTripla(Connettori[] connettori, int carica) {
		super(connettori, carica);
	}
	@Override
	public String toString() {
		return "Batteria Tripla";
	}
	public String toStringAbbreviato() {
		return "BT";
	}
}
