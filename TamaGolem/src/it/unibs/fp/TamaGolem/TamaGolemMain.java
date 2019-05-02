package it.unibs.fp.TamaGolem;

import it.unibs.fp.mylib.InputDati;


public class TamaGolemMain {

	public static final String[] ELEMENTI = {"FUOCO", "TERRA", "ACQUA", "VENTO", "ETERE", "FULMINE", 
			"LEGNO", "SUONO", "LUCE", "TENEBRE"};
	public static final int MAX_ELEMENTI = ELEMENTI.length;
	public static final int MIN_ELEMENTI = 3;

	
	public static final String MSG_INPUT_GEMME = "Con quanti elementi vuoi giocare? "
			+ "(Min " + TamaGolemMain.MIN_ELEMENTI + ", Max " + TamaGolemMain.MAX_ELEMENTI + "): ";
	
	public static final int NUM_ELEMENTI = inputNumeroGemmeUsare();
	
	
	public static final int NUM_PIETRE = ((NUM_ELEMENTI + 1)/3)+1;
	public static final int NUM_GOLEMEM = ((NUM_ELEMENTI -1 )*(NUM_ELEMENTI -2))/(2*NUM_PIETRE);
	public static final int ELEMENTO_NEL_SACCO = (2 * NUM_GOLEMEM * NUM_PIETRE)/NUM_ELEMENTI;
	public static final int PIETRE_NEL_SACCO = ELEMENTO_NEL_SACCO*NUM_ELEMENTI;
	

	public static int inputNumeroGemmeUsare() {
		int in = InputDati.leggiIntero(MSG_INPUT_GEMME, 0, TamaGolemMain.MAX_ELEMENTI);
		return in;
	}
	
	public static void main(String[] args) {
		
		Equilibrio tmp = new Equilibrio();
		System.out.println(tmp.mostraMatriceEquilibrio());
		System.out.println(NUM_ELEMENTI);
		
		Sacco s1 = new Sacco();

		
	}
	
	

}