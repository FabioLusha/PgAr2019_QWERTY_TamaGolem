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
		this.posizionePietraAttuale = (this.posizionePietraAttuale+1) % TamaGolemMain.numPietre;
	}
	
	public void diminuisciVita(int quanto) {
		this.livVita -= Math.abs(quanto);
	}
	
	/**
	 * @param giocatore1 il giocatore a cui appartiene il golem che richiam il metodo
	 * @param giocatore2 il giocatore avversario
	 * @param golem2 che deve scontrarsi col golem che richiama il metodo
	 * @param equilibrio per determinare le interazioni
	 * @return una variabile di tipo Giocatore che punta il giocatore perdente
	 */
	public Giocatore scontro(Giocatore giocatore1, Giocatore giocatore2, Golem golem2, Equilibrio equilibrio) {
		while(this.livVita > 0 && golem2.getLivVita() > 0) 
		{
			int interazione = equilibrio.getInterazione(this.getPietraAttuale(), golem2.getPietraAttuale());
			if( interazione == 0) {
				System.out.println(String.format(TamaGolemMain.MSG_GOLEM_DANNO, giocatore1.getNome(), TamaGolemMain.ELEMENTI[this.getPietraAttuale()], giocatore2.getNome(), TamaGolemMain.ELEMENTI[this.getPietraAttuale()], "NESSUNO"));
				golem2.diminuisciVita(interazione);
			}
			
			else if( interazione> 0) {
				System.out.println(String.format(TamaGolemMain.MSG_GOLEM_DANNO, giocatore1.getNome(), TamaGolemMain.ELEMENTI[this.getPietraAttuale()], giocatore2.getNome(), TamaGolemMain.ELEMENTI[golem2.getPietraAttuale()],giocatore1.getNome()));
				golem2.diminuisciVita(interazione);
				}
			else {
				System.out.println(String.format(TamaGolemMain.MSG_GOLEM_DANNO, giocatore1.getNome(), TamaGolemMain.ELEMENTI[this.getPietraAttuale()],giocatore2.getNome(), TamaGolemMain.ELEMENTI[golem2.getPietraAttuale()],giocatore2.getNome()));
				this.diminuisciVita(interazione);
			}
			this.pietraSuccessiva();
			golem2.pietraSuccessiva();
		}
		
		if(this.livVita <= 0)
			return giocatore1;
		else
			return giocatore2;
	}
	
	public boolean arrayPietreUgualiAncheNellOrdine(Golem golem2) {
		int[] pietreGolem2 = golem2.getPietre();
		for(int i = 0, j = golem2.posizionePietraAttuale; i < TamaGolemMain.numPietre; i++, j++) {
			j = j % TamaGolemMain.numPietre;
			if(pietre[i] != pietreGolem2[j])
				return false;
		}
		return true;
	}
	
}
