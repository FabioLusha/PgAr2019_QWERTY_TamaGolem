package it.unibs.fp.TamaGolem;

import it.unibs.fp.mylib.InputDati;

public class Sacco {

	private String[] elementiUtilizabili; 
	private int[] quantita; 
	
	public Sacco() {
		elementiUtilizabili = new String[TamaGolemMain.NUM_ELEMENTI];
		quantita = new int[TamaGolemMain.NUM_ELEMENTI];
		for(int i = 0; i < TamaGolemMain.NUM_ELEMENTI; i++) quantita[i] = TamaGolemMain.ELEMENTO_NEL_SACCO;
		System.arraycopy(TamaGolemMain.ELEMENTI, 0, elementiUtilizabili, 0, TamaGolemMain.NUM_ELEMENTI);
	}

	public String[] getElementiUtilizabili()
	{
		return elementiUtilizabili;
	}

	public void setElementiUtilizabili(String[] elementiUtilizabili) {
		this.elementiUtilizabili = elementiUtilizabili;
	}

	public int[] getQuantitaElementi() {
		return quantita;
	}

	public int getQuantitaElemento(int i) {
		return quantita[i];
	}

	public void setQuantitaElementi(int[] quantitaElementi) {
		this.quantita = quantitaElementi;
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("\n        PIETRE DISPONIBILI       \n");
		for(int i = 0; i < TamaGolemMain.NUM_ELEMENTI; i++) {
			if(quantita[i] > 0)
				str.append(i + " - " + elementiUtilizabili[i] + ", Pietre disponibili: " + quantita[i] + "\n");
		}
		
		return str.toString();
	}
	
	public void togliPietre(int indiceElemento, int quantitaDaTogliere) {
		this.quantita[indiceElemento] -= quantitaDaTogliere;
	}
	
	public int[] scegliePietre(Giocatore giocatore)
	{

		int pietreScelte = 0, quantitaPietrePerTipo = 0;
		int[] pietre = new int[TamaGolemMain.NUM_PIETRE];

		do
		{

			System.out.println("\n" + giocatore.getNome() + " scegli le tue pietre (puoi sceglierene ancora " + (TamaGolemMain.NUM_PIETRE - pietreScelte) + " )");
			System.out.println(toString());

			int t = InputDati.leggiIntero("Che elemento vuoi ?(indica l'indice)", 0, (quantita.length - 1));

			if (quantita[t] < (TamaGolemMain.NUM_PIETRE - pietreScelte))
				quantitaPietrePerTipo = InputDati.leggiIntero("Quante pietre di questo tipo vuoi prendere dal sacco? ", 0, quantita[t]);
			else
				quantitaPietrePerTipo = InputDati.leggiIntero("Quante pietre di questo tipo vuoi prendere dal sacco? ", 0, (TamaGolemMain.NUM_PIETRE - pietreScelte));

			togliPietre(t, quantitaPietrePerTipo);
			
			for (int j = 0 ; j < quantitaPietrePerTipo; j++)
				pietre[pietreScelte + j] = t;
			
			pietreScelte = pietreScelte + quantitaPietrePerTipo;

		}while(pietreScelte < TamaGolemMain.NUM_PIETRE);
		
		return pietre;
	}
	


}
