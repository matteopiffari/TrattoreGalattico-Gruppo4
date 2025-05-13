package ship.components;

public class StivaSpeciale extends Stiva {
	
	public StivaSpeciale(Connettori[] connettori){
		super(connettori);
		merci= new int[(int) (Math.random() * 2) + 1];
	}
	
	@Override
	public boolean setMerci(int merce) {
		boolean aggiungi=false;
		for(int i=0; i<merci.length; i++) {
			if(merci[i]==0) {
				merci[i]=merce;
				aggiungi=true;
			}
		}
		return aggiungi;
	}

}
