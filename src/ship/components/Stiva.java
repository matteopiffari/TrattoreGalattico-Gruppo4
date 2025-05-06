package ship.components;

public class Stiva extends Componente{
	protected int merci[];
	
	public Stiva(Connettori[] connettori){
		super(connettori);
		merci=new int[(int) (Math.random() * 2) + 2];
	}

	public int[] getMerci() {
		return merci;
	}

	public boolean setMerci(int merce) {
		boolean aggiungi=false;
		if(merce>3) {
			return false;
		}
		for(int i=0; i<merci.length; i++) {
			if(merci[i]==0) {
				merci[i]=merce;
				aggiungi=true;
			}
		}
		return aggiungi;
	}

}
