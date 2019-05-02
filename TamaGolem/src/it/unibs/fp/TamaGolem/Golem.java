package it.unibs.fp.TamaGolem;

public class Golem {
	
	public static final int VITA_INIZIALE = 100;
	
	private int livVita = VITA_INIZIALE;
	private String[] tipiPietra = new String[TamaGolemMain.NUM_PIETRE];
	
	public Golem(int livVita, String[] tipiPietra) {
		super();
		this.livVita = livVita;
		this.tipiPietra = tipiPietra;
	}

	public int getLivVita() {
		return livVita;
	}

	public void setLivVita(int livVita) {
		this.livVita = livVita;
	}

	public String[] getTipiPietra() {
		return tipiPietra;
	}

	public void setTipiPietra(String[] tipiPietra) {
		this.tipiPietra = tipiPietra;
	}
	
	


}
