package it.unibs.fp.TamaGolem;

public class Golem {
	
	public static final int VITA_INIZIALE = 100;
	
	private int livVita;
	private int[] pietre;// = new int[TamaGolemMain.NUM_PIETRE];
	private int posizionePietraAttuale;
	
	public Golem(int[] pietre) {
		this.livVita = VITA_INIZIALE;
		this.pietre = pietre;
		this.posizionePietraAttuale = 0;
	}

	public int getLivVita() {
		return livVita;
	}


	public int[] getPietre() {
		return pietre;
	}

	public void setPietre(int[] pietre) {
		this.pietre = pietre;
	}

	public int getPietraAttuale() {
		return pietre[posizionePietraAttuale];
	}

	/**
	 * Visto che le pietre si ripetono ciclicamente il modulo risulta
	 */
	public void pietraSuccessiva(){
		this.posizionePietraAttuale = (this.posizionePietraAttuale+1) % TamaGolemMain.NUM_PIETRE;
	}
	
	public void diminuisciVita(int quanto) {
		this.livVita -= Math.abs(quanto);
	}
}
