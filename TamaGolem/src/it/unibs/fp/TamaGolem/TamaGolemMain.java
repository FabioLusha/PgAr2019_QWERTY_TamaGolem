package it.unibs.fp.TamaGolem;

import it.unibs.fp.mylib.InputDati;


public class TamaGolemMain {

	private static final String MSG_GOLEM_DANNO = "Il golem di %s sceglie %s, quello di %s sceglie %s, %s infligge %d punti di danno";
	public static final String MSG_NUOVA_PARTITA = "Si vuole giocare un altra partita? ( 0 = no, 1 = sì)";
	public static final String MSG_PARTITA_CONCLUSA = "La partita si è conclusa, il vincitore è: %s";
	public static final String MSG_GOLEM_MORTO = "\n%s il tuo golem è morto! Evocane un altro\n";
	public static final String MSG_INPUT_NOME_GIOCATORE = "Inserire il nome del %s giocatore: ";
	
	public static final String[] ELEMENTI = {"FUOCO", "TERRA", "ACQUA", "VENTO", "ETERE", "FULMINE", 
											 "LEGNO", "SUONO", "LUCE", "TENEBRE"};
	public static final int MAX_ELEMENTI = ELEMENTI.length;
	public static final int MIN_ELEMENTI = 3;
	public static final String MSG_INPUT_GEMME = "Con quanti elementi vuoi giocare? "
			+ "(Min " + TamaGolemMain.MIN_ELEMENTI + ", Max " + TamaGolemMain.MAX_ELEMENTI + "): ";
	
	public static final int NUM_ELEMENTI = inputNumeroGemmeUsare();
	
	public static final int NUM_PIETRE = Math.round(((float)(NUM_ELEMENTI + 1)/3)+1);
	public static final int NUM_GOLEM = Math.round(((float)(NUM_ELEMENTI -1 )*(NUM_ELEMENTI -2))/(2*NUM_PIETRE));
	public static final int ELEMENTO_NEL_SACCO = Math.round((float)(2 * NUM_GOLEM * NUM_PIETRE)/NUM_ELEMENTI);
	public static final int PIETRE_NEL_SACCO = Math.round(((float)(2 * NUM_GOLEM * NUM_PIETRE)/NUM_ELEMENTI)*NUM_ELEMENTI);
	

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
		
		int scelta = 0;
		
		do {
			
			Equilibrio equilibrio = new Equilibrio();
			System.out.println(equilibrio.mostraMatriceEquilibrio());
			Sacco sacco = new Sacco();
			golem1 = new Golem(sacco.scegliePietre(giocatore1));
			golem2 = new Golem(sacco.scegliePietre(giocatore2));
			
			while(giocatore1.getNumGolemDisponibili() > 0 && giocatore2.getNumGolemDisponibili() > 0)
			{
				
				while(golem1.getLivVita() > 0 && golem2.getLivVita() > 0) 
				{
					int interazione = equilibrio.getInterazione(golem1.getPietraAttuale(), golem2.getPietraAttuale());
					if( interazione> 0) {
						System.out.println(String.format(MSG_GOLEM_DANNO, giocatore1.getNome(), ELEMENTI[golem1.getPietraAttuale()], giocatore2.getNome(), ELEMENTI[golem1.getPietraAttuale()],giocatore1.getNome(), interazione ));
						golem2.diminuisciVita(interazione);
						}
					else {
						System.out.println(String.format(MSG_GOLEM_DANNO, giocatore1.getNome(), ELEMENTI[golem1.getPietraAttuale()],giocatore2.getNome(), ELEMENTI[golem1.getPietraAttuale()],giocatore2.getNome(), interazione ));
						golem1.diminuisciVita(interazione);
					}
					golem1.pietraSuccessiva();
					golem2.pietraSuccessiva();
				}
				
				if(golem1.getLivVita() <= 0) {
					System.out.println(String.format(MSG_GOLEM_MORTO, giocatore1.getNome()));
					golem1 = new Golem(sacco.scegliePietre(giocatore1));
					giocatore1.diminuisciNumGolem();
				}
				else if(golem2.getLivVita() <= 0) {
					System.out.println(String.format(MSG_GOLEM_MORTO, giocatore2.getNome()));
					golem2 = new Golem(sacco.scegliePietre(giocatore2));
					giocatore2.diminuisciNumGolem();
				}
			}
			if(giocatore1.getNumGolemDisponibili() <= 0) {
				System.out.println(String.format(MSG_PARTITA_CONCLUSA, giocatore2.getNome()));
			}
			else
				System.out.println(String.format(MSG_PARTITA_CONCLUSA, giocatore1.getNome()));
			
			scelta = InputDati.leggiIntero(MSG_NUOVA_PARTITA);
		}while(scelta == 0);

	}

}