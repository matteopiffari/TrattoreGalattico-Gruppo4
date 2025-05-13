package ship.components;



public class SupportoVitale extends Cabina {
	
	public enum TipoSupporto{
		CANNONE, MOTORE
	}
	
	private TipoSupporto tipo; 
	
	public SupportoVitale(Connettori[] connettori, TipoSupporto tipo){
		super(connettori);
		this.tipo=tipo;
	}

}
