package it.unibs.fp.TamaGolem;

import java.util.Random;;

public class Equilibrio {
	
	int[][] matEquilibrio;
	Random generatoreCasuale = new Random(System.currentTimeMillis());
	
	public Equilibrio() {
		matEquilibrio = new int[Gemma.NUMGEMME][Gemma.NUMGEMME];
		
		for(int i = 0; i < Gemma.NUMGEMME; i++) {
			int somma = 0;
			for(int j = 0; j < Gemma.NUMGEMME; j++) {
				matEquilibrio[i][j] = 1;
			}
		}
	}
	
	
}
