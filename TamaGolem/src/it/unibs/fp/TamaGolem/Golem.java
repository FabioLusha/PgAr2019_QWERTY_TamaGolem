package it.unibs.fp.TamaGolem;

public class Golem {
	public static final int VITA_INIZIALE = 100;
	
	private int livVita = VITA_INIZIALE;
	private Gemma[] gemme = new Gemma[Gemma.NUMGEMME];
	
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
