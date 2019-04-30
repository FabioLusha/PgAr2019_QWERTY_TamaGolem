package it.unibs.fp.TamaGolem;

public class Golem {
	
	public static final int NUMGEMME = 5;
	private int livVita = 100;
	private Gemma[] gemme = new Gemma[NUMGEMME];
	
	public Golem(int livVita, Gemma[] gemme) {
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
	public Gemma[] getGemme() {
		return gemme;
	}
	public void setGemme(Gemma[] gemme) {
		this.gemme = gemme;
	}


}
