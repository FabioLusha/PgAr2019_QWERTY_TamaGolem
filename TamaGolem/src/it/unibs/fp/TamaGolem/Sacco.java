package it.unibs.fp.TamaGolem;

public class Sacco {
	private String[] elementiUtilizabili; 
	private int[] quantitaElementi; 
	
	public Sacco() {
		elementiUtilizabili = new String[TamaGolemMain.NUM_ELEMENTI];
		quantitaElementi = new int[TamaGolemMain.NUM_ELEMENTI];
		for(int i = 0; i < TamaGolemMain.NUM_ELEMENTI; i++) quantitaElementi[i] = TamaGolemMain.ELEMENTO_NEL_SACCO;
		System.arraycopy(TamaGolemMain.ELEMENTI, 0, elementiUtilizabili, 0, TamaGolemMain.NUM_ELEMENTI);
	}

	public String[] getElementiUtilizabili() {
		return elementiUtilizabili;
	}

	public void setElementiUtilizabili(String[] elementiUtilizabili) {
		this.elementiUtilizabili = elementiUtilizabili;
	}

	public int[] getQuantitaElementi() {
		return quantitaElementi;
	}

	public void setQuantitaElementi(int[] quantitaElementi) {
		this.quantitaElementi = quantitaElementi;
	}
	
}
