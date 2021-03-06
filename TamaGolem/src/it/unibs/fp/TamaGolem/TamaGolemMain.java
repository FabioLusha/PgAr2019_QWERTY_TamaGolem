package it.unibs.fp.TamaGolem;

import it.unibs.fp.mylib.InputDati;


public class TamaGolemMain {

	public static final String MSG_SET_PIETRE_UGUALI = "\nATTENZIONE! Hai inserito le sttesse pietre del tuo avverssario nel suo stesso ordine";
	public static final String MSG_MOSTRA_EQUILIBRIO = "\nQuesto era l'equilibrio per questa partita:";
	public static final String MSG_PIETRE_MAX_IN_GOLEM = "Numero di peitre con cui un golem può essere equipaggiato: ";
	public static final String MSG_GOLEM_DISPONIBILI = "\nGolem disponibili a testa: ";
	public static final String MSG_TURNO_GIOCATORE = "\n******È IL TURNO DI %s *******\n";
	public static final String MSG_GOLEM_DANNO = "Il golem di %s sceglie %s, quello di %s sceglie %s, %s infligge punti danno";
	public static final String MSG_NUOVA_PARTITA = "Si vuole giocare un altra partita? ( 0 = no, 1 = sì)";
	public static final String MSG_PARTITA_CONCLUSA = "\nLa partita si è conclusa, il vincitore è: %s";
	public static final String MSG_GOLEM_MORTO = "\n***** %s il tuo golem è morto! Evocane un altro (te ne rimangono ancora %d)\n";
	public static final String MSG_INPUT_NOME_GIOCATORE = "Inserire il nome del %s giocatore: ";
	
	public static final String[] ELEMENTI = {"FUOCO", "TERRA", "ACQUA", "VENTO", "GHIACCIO", "FULMINE", "NATURA", "SUONO", "LUCE", "TENEBRE"};
	public static final int MAX_ELEMENTI = ELEMENTI.length;
	public static final int MIN_ELEMENTI = 3;
	public static final String MSG_INPUT_GEMME = "Con quanti elementi vuoi giocare? " + "(Min " + TamaGolemMain.MIN_ELEMENTI + ", Max " + TamaGolemMain.MAX_ELEMENTI + "): ";
	
	public static int numElementi;// = inputNumeroGemmeUsare();
	public static  int numPietre;// = ((NUM_ELEMENTI + 1)/3)+1;
	public static  int numGolem; // = ((NUM_ELEMENTI -1 )*(NUM_ELEMENTI -2))/(2*NUM_PIETRE);
	public static  int pietreNelSacco;// = (2 * NUM_GOLEM * NUM_PIETRE);
	public static  int elementoNelSacco;// = (PIETRE_NEL_SACCO/NUM_ELEMENTI);
	
	

	public static int inputNumeroGemmeUsare() {
		int in = InputDati.leggiIntero(MSG_INPUT_GEMME, MIN_ELEMENTI, TamaGolemMain.MAX_ELEMENTI );
		return in;
	}
	
	public static void main(String[] args) {
		Giocatore giocatore1 = new Giocatore(InputDati.leggiStringa(String.format(MSG_INPUT_NOME_GIOCATORE, "primo")));
		Giocatore giocatore2 = new Giocatore(InputDati.leggiStringa(String.format(MSG_INPUT_NOME_GIOCATORE, "secondo")));;
		//golem1 appartiene al giocatore1
		Golem golem1;
		//golem2 appartiene al giocatore2
		Golem golem2;

		
		int nuovaPartita = 0;
		
		do {
			numElementi = inputNumeroGemmeUsare();
			numPietre = ((numElementi + 1)/3)+1;
			numGolem = ((numElementi -1 )*(numElementi -2))/(2*numPietre);
			pietreNelSacco = (2 * numGolem * numPietre);
			elementoNelSacco = (pietreNelSacco/numElementi);
			giocatore1.setNumGolemDisponibili(numGolem);
			giocatore2.setNumGolemDisponibili(numGolem);

			System.out.println(MSG_GOLEM_DISPONIBILI + numGolem);
			System.out.println(MSG_PIETRE_MAX_IN_GOLEM + numPietre);

			Equilibrio equilibrio = new Equilibrio();
			Sacco sacco = new Sacco();
			System.out.println(String.format(MSG_TURNO_GIOCATORE, giocatore1.getNome().toUpperCase()));
			golem1 = new Golem(giocatore1, sacco);
			System.out.println(String.format(MSG_TURNO_GIOCATORE, giocatore2.getNome().toUpperCase()));
			golem2 = new Golem(giocatore2, sacco);
			
			battaglia(giocatore1, giocatore2, golem1, golem2, equilibrio, sacco);
			
			if(giocatore1.getNumGolemDisponibili() <= 0) {
				System.out.println(String.format(MSG_PARTITA_CONCLUSA, giocatore2.getNome()));
			}
			else
				System.out.println(String.format(MSG_PARTITA_CONCLUSA, giocatore1.getNome()));
			
			System.out.println(MSG_MOSTRA_EQUILIBRIO);
			System.out.println(equilibrio.mostraEquilibrio());
			
			nuovaPartita = InputDati.leggiIntero(MSG_NUOVA_PARTITA);
		}while(nuovaPartita == 1);
	}
	
	/**
	 * si esegue un loop finché il numero di golem a disposizione di uno dei 2 giocatori non arriva a 0. Viene creata la variabile perdente di tipo Giocatore
	 * che viene inizializzata dal metodo scontro(...) e quindi punta al giocatore risultante sconfitto dallo scontro. Al perdente viene diminuito il
	 * numero di golme disponibile di 1 grazie al metodo .diminuisciNumGolem(). Successivamente, se il numero di golm del perdente fosse maggiore di 0
	 * questo può procedere con l'evocazione di un nuovo golem altrimenti si esce dal ciclo.
	 * @param giocatore1 uno dei 2 giocatori
	 * @param giocatore2 il giocatore avversario
	 * @param golem1 il golem del primo giocatore
	 * @param golem2 il golem dell'avversario
	 * @param equilibrio della partita
	 * @param sacco da qui estrarre gli elementi
	 */
	public static void battaglia(Giocatore giocatore1, Giocatore giocatore2, Golem golem1, Golem golem2, Equilibrio equilibrio, Sacco sacco) {
		while(giocatore1.getNumGolemDisponibili() > 0 && giocatore2.getNumGolemDisponibili() > 0)
		{
			Giocatore perdente = golem1.scontro(giocatore1, giocatore2, golem2, equilibrio);
			//perdente è una variabile che punta all'oggetto giocatore1 o giocatore2
			perdente.diminuisciNumGolem();
			
			if(perdente.getNumGolemDisponibili() > 0) 
			{
				System.out.println(String.format(MSG_GOLEM_MORTO, perdente.getNome(), perdente.getNumGolemDisponibili()));
				//controllo quale giocatore punta perdente
				if(perdente == giocatore1) {
					golem1 = evocaGolem(golem2, sacco, giocatore1);
				}
				else {
					//continuo
					golem2 = evocaGolem(golem1, sacco, giocatore2);
				}
			}
		
		}
	}
	
	/**
	 * Creo un nuovo oggetto di tipo golem controllando che non abbiamo lo stesso set di pietre nello stesso ordine e nel caso lo fossero
	 * richiamo il metodo per annullare l'operazione e rimettere le pietre nel sacco
	 * @param golemAvversario il golem avversario (per confrontare il set di pietre)
	 * @param sacco da dove si scelgono le pietre
	 * @param giocatorePossessore a cui è assegnato il golem
	 * @return la variabile che punta al neo-oggetto Golem creato nel metodo
	 */
	public static Golem evocaGolem(Golem golemAvversario, Sacco sacco, Giocatore giocatorePossessore) {
		Golem golemDaEvocare;
		do {
		golemDaEvocare = new Golem(giocatorePossessore, sacco);
		if(golemDaEvocare.arrayPietreUgualiAncheNellOrdine(golemAvversario)) {
			sacco.rimettiPietreNelSacco(golemDaEvocare.getPietre());
			System.out.println(MSG_SET_PIETRE_UGUALI);
		}
		}while(golemDaEvocare.arrayPietreUgualiAncheNellOrdine(golemAvversario));
		return golemDaEvocare;
	}
}
