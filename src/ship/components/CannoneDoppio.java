package ship.components;

public class CannoneDoppio extends Cannone {

	public CannoneDoppio(Connettori[] connettori, int potenza) {
		super(connettori, potenza);
	}
	@Override
	public String toString() {
		return "Cannone Doppio";
	}
	public String toStringAbbreviato() {
		return "CD";
	}
}
