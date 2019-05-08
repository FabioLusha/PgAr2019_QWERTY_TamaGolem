package it.unibs.fp.TamaGolem;

import it.unibs.fp.mylib.InputDati;


public class Golem {
	
	public static final int VITA_INIZIALE = 100;

	private int livVita;
	/**
	 * elementi con cui vine equipaggiato un golem, in ogni cella dell'array c'è l'indice di un elemento
	 */
	private int[] pietre;// = new int[TamaGolemMain.NUM_PIETRE];
	private int posizionePietraAttuale;

	public static final String MSG_SCEGLIE_PIETRE = " scegli le tue pietre (puoi sceglierene ancora ";

	public static final String MSG_QUALE_ELEMENTO = "Che elemento vuoi ?(indica l'indice)";

	public static final String WARNING_ELEMENTO_NON_DISPONIBILE = "Questo elemento non è più disponibile!";

	public static final String MSG_QUANTE_PIETRE_VUOI = "Quante pietre di questo tipo vuoi prendere dal sacco? ";
	
	public Golem(Giocatore giocatore, Sacco sacco) {
		this.livVita = VITA_INIZIALE;
		this.pietre = scegliePietre(giocatore, sacco);
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
	 * Visto che le pietre si ripetono ciclicamente il modulo risulta la migliore scelta di spostarsi tra le pietre
	 */
	public void pietraSuccessiva(){
		this.posizionePietraAttuale = (this.posizionePietraAttuale+1) % TamaGolemMain.numPietre;
	}
	
	public void diminuisciVita(int quanto) {
		this.livVita -= Math.abs(quanto);
	}
	
	/**
	 * Il ciclo continua finchè la vita di uno dei golem non golem scende sotto 0 (compreso). Prendiamo l'interazione e la valutiamo, se è poitiva vuol dire
	 * che il golem1 (quello che invoca il metodo) danneggia il golme2 quindi diminuisce la vita di quest'ultimo, se l'interazione fosse negativa vale
	 * il viceversa. Nel caso l'interazione fosse nulla cioò implica che i 2 golem hanno scagliato pietre dello stesso tipo.
	 * Dopo aver scagliato la prima pietrasi passa alla succesiva finchè uno dei 2 non muore. Da notare che con il metodo pietraSuccessiva() modifico un
	 * attributo (posizionePietraAttuale) dell'oggetto Golem e che pertanto nel caso della morte di uno dei due la posizionePietraAttuale dell'altro diviene la succesiva
	 * a quella che ha causato la morte dell'avversario.
	 * @param giocatore1 il giocatore a cui appartiene il golem che richiama il metodo
	 * @param giocatore2 il giocatore avversario
	 * @param golem2 che deve scontrarsi col golem che richiama il metodo
	 * @param equilibrio per determinare le interazioni
	 * @return una variabile di tipo Giocatore che punta al giocatore perdente
	 */
	public Giocatore scontro(Giocatore giocatore1, Giocatore giocatore2, Golem golem2, Equilibrio equilibrio) {
		while(this.livVita > 0 && golem2.getLivVita() > 0) 
		{
			int interazione = equilibrio.getInterazione(this.getPietraAttuale(), golem2.getPietraAttuale());
			if( interazione == 0) {
				System.out.println(String.format(TamaGolemMain.MSG_GOLEM_DANNO, giocatore1.getNome(), TamaGolemMain.ELEMENTI[this.getPietraAttuale()], giocatore2.getNome(), TamaGolemMain.ELEMENTI[this.getPietraAttuale()], "NESSUNO"));
				golem2.diminuisciVita(interazione);
			}
			
			else if( interazione > 0) {
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

	/**
	 * Nell'input chiediamo all'utente di scgliere il tipo di Elemento che vuole insieme alla quantità. Ci avvaliamo di una variabile pietreScelete
	 * che tiene conto del numero di pietre già scelte così da poter ripetere l'operazione finchè il set di pietre per il tamagolem non viene riempito.
	 * @param giocatore al quale golem saranno associate le gemme
	 * @param sacco nel quale ci sono gli elementi disponibili
	 * @return un array con le pietre scelte
	 */
	public int[] scegliePietre(Giocatore giocatore, Sacco sacco)
	{
	
		int pietreScelte = 0, quantitaPietrePerTipo = 0;
		int[] pietre = new int[TamaGolemMain.numPietre];
	
		do
		{
			System.out.println(giocatore.getNome() + Golem.MSG_SCEGLIE_PIETRE + (TamaGolemMain.numPietre - pietreScelte) + " )");
			System.out.println(sacco.toString());
	
			int t = InputDati.leggiIntero(Golem.MSG_QUALE_ELEMENTO, 0, (sacco.getQuantitaElementi().length - 1));
			if(sacco.getQuantitaElemento(t) == 0)
				System.out.println(Golem.WARNING_ELEMENTO_NON_DISPONIBILE);
			else {
				if (sacco.getQuantitaElemento(t) < (TamaGolemMain.numPietre - pietreScelte))
					quantitaPietrePerTipo = InputDati.leggiIntero(Golem.MSG_QUANTE_PIETRE_VUOI, 0, sacco.getQuantitaElemento(t));
				else
					quantitaPietrePerTipo = InputDati.leggiIntero(Golem.MSG_QUANTE_PIETRE_VUOI, 0, (TamaGolemMain.numPietre - pietreScelte));
	
				sacco.togliPietre(t, quantitaPietrePerTipo);
				
				for (int j = 0 ; j < quantitaPietrePerTipo; j++)
					pietre[pietreScelte + j] = t;
			
				pietreScelte = pietreScelte + quantitaPietrePerTipo;
			}
		}while(pietreScelte < TamaGolemMain.numPietre);
		
		return pietre;
	}
	
}
