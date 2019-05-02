package it.unibs.fp.TamaGolem;

public class Sacco {
	private String[] elementiUtilizabili;
	private int[] quantita;
	
	
	public Sacco() {
		elementiUtilizabili = new String[TamaGolemMain.NUM_ELEMENTI];
		
		quantita = new int[TamaGolemMain.NUM_ELEMENTI];
		for(int i = 0; i < TamaGolemMain.NUM_ELEMENTI; i++) 
			quantita[i] = TamaGolemMain.ELEMENTO_NEL_SACCO;
		
		System.arraycopy(TamaGolemMain.ELEMENTI, 0, elementiUtilizabili, 0, TamaGolemMain.NUM_ELEMENTI);
	}
	
	public String toString() {
		String str = new String();
		
		for(int i = 0; i < TamaGolemMain.NUM_ELEMENTI; i++) {
			str = "\n" + i + " - " + elementiUtilizabili[i] + ", Pietre disponibili: " + quantita[i];
		}
		
		return str;
	}

	public String[] getElementiUtilizabili() {
		return elementiUtilizabili;
	}

	public void setElementiUtilizabili(String[] elementiUtilizabili) {
		this.elementiUtilizabili = elementiUtilizabili;
	}

	public int[] getQuantita() {
		return quantita;
	}

	public void setQuantita(int[] quantita) {
		this.quantita = quantita;
	}
	
	public void togliPietre(int indiceElemento, int quantitaDaTogliere) {
		this.quantita[indiceElemento] -= quantitaDaTogliere;
	}
	
	

}
