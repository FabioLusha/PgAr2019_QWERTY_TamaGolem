package it.unibs.fp.TamaGolem;

import it.unibs.fp.mylib.InputDati;

public class Battaglia {
	Giocatore giocatore1;
	Giocatore giocatore2;
	Golem golem1;
	Golem golem2;
	Sacco s1;
	
	public Battaglia() {
		s1 = new Sacco();
		giocatore1 = new Giocatore();
		giocatore2 = new Giocatore();
	}
	
	public int[] scegliePietre() {
		int i = 0, p = 0, r;
		int[] pietre = new int[TamaGolemMain.NUM_PIETRE];
		 do{
			System.out.println(s1.toString());
			int t = InputDati.leggiIntero("Che elemento vuoi ?(indica l'indice)", 0, (s1.getQuantitaElementi().length - 1));
			if(s1.getQuantitaElementi()[t] < (TamaGolemMain.NUM_PIETRE - p)){
				r = InputDati.leggiIntero("Quante pietre di questo tipo vuoi prendere dal sacco? ", 0, s1.getQuantitaElementi()[t]);
			} else {
				r = InputDati.leggiIntero("Quante pietre di questo tipo vuoi prendere dal sacco? ", 0, (TamaGolemMain.NUM_PIETRE - p));
				}
			p = p + r;
			s1.togliPietre(t, r);
			for(int j = 0; j < r; j++) {
				pietre[j] = t;
			}
			
			}while(i < TamaGolemMain.NUM_PIETRE);
		 return pietre;
	}
	
	

	
}
