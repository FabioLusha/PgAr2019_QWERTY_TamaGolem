package it.unibs.fp.TamaGolem;

public class Golem {
	
	public static final int VITA_INIZIALE = 100;
	
	private int livVita = VITA_INIZIALE;
	private int[] tipiPietra = new int[TamaGolemMain.NUM_PIETRE];
	
	public Golem(int[] tipiPietra) {
		this.tipiPietra = tipiPietra;
	}

	public int getLivVita() {
		return livVita;
	}

	public void setLivVita(int livVita) {
		this.livVita = livVita;
	}

	public int[] getTipiPietra() {
		return tipiPietra;
	}

	public void setTipiPietra(int[] tipiPietra) {
		this.tipiPietra = tipiPietra;
	}

	
	


}
