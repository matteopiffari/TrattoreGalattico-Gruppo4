package ship.components;
//getter e setter 
public class Cabina extends Componente {

    private int equipaggio;
    
    public Cabina(Connettori[] connettori){
		super(connettori);
	}

    public int getEquipaggio() {
        return equipaggio;
    }

    public void setEquipaggio(int equipaggio) {
        this.equipaggio = equipaggio;
    }
}
