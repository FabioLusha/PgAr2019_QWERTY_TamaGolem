package it.unibs.fp.TamaGolem;

public class Golem {
	
	public static final int VITA_INIZIALE = 100;
	
	private int livVita = VITA_INIZIALE;
<<<<<<< HEAD
	private String[] tipiPietra = new String[TamaGolemMain.NUM_PIETRE];
	
	public Golem(int livVita, String[] tipiPietra) {
		super();
		this.livVita = livVita;
		this.tipiPietra = tipiPietra;
=======
	private String[] pietre = new String[TamaGolemMain.NUM_ELEMENTI];
	
	public Golem(int livVita, String[] gemme) {
		super();
		this.livVita = livVita;
		this.pietre = gemme;
>>>>>>> 09824f3aea153ba1b14b72d7cf6b3967297d511d
	}

	public int getLivVita() {
		return livVita;
	}

	public void setLivVita(int livVita) {
		this.livVita = livVita;
	}
<<<<<<< HEAD

	public String[] getTipiPietra() {
		return tipiPietra;
	}

	public void setTipiPietra(String[] tipiPietra) {
		this.tipiPietra = tipiPietra;
=======
	public String[] getGemme() {
		return pietre;
	}
	public void setGemme(String[] gemme) {
		this.pietre = gemme;
>>>>>>> 09824f3aea153ba1b14b72d7cf6b3967297d511d
	}
	
	


}
