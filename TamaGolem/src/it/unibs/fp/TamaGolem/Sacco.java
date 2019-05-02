package it.unibs.fp.TamaGolem;

public class Sacco {
	private Elementi[] elementiUtilizabili = Elementi.values(); 
	
	public Sacco() {
		elementiUtilizabili = new Elementi[TamaGolemMain.NUM_ELEMENTI];
	}
/*	
	public String getElementiUtilizzabili(int i){
		return elementiUtilizabili[i].name();
	}*/
}
