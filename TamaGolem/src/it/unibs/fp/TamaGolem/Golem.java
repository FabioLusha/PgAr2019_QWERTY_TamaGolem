package it.unibs.fp.TamaGolem;

public class Golem {
	public static final int VITA_INIZIALE = 100;
	
	private int livVita = VITA_INIZIALE;
	private String[] pietre = new String[TamaGolemMain.NUM_ELEMENTI];
	
	public Golem(int livVita, String[] gemme) {
		super();
		this.livVita = livVita;
		this.pietre = gemme;
	}
	public int getLivVita() {
		return livVita;
	}
	public void setLivVita(int livVita) {
		this.livVita = livVita;
	}
	public String[] getGemme() {
		return pietre;
	}
	public void setGemme(String[] gemme) {
		this.pietre = gemme;
	}


}
