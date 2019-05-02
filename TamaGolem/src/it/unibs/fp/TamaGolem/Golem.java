package it.unibs.fp.TamaGolem;

public class Golem {
	public static final int VITA_INIZIALE = 100;
	
	private int livVita = VITA_INIZIALE;
	private Elementi[] gemme = new Elementi[TamaGolemMain.NUM_ELEMENTI];
	
	public Golem(int livVita, Elementi[] gemme) {
		super();
		this.livVita = livVita;
		this.gemme = gemme;
	}
	public int getLivVita() {
		return livVita;
	}
	public void setLivVita(int livVita) {
		this.livVita = livVita;
	}
	public Elementi[] getGemme() {
		return gemme;
	}
	public void setGemme(Elementi[] gemme) {
		this.gemme = gemme;
	}


}
