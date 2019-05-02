package it.unibs.fp.TamaGolem;

import java.util.Random;;

public class Equilibrio {
	
	int[][] matEquilibrio;
	Random generatoreCasuale = new Random(System.currentTimeMillis());
	
	public Equilibrio() 
	{
		matEquilibrio = new int[TamaGolemMain.NUM_ELEMENTI][TamaGolemMain.NUM_ELEMENTI];
		int  k, i, j, somma =0, sommaVerticale = 0, rnd, segno;
		
			
		for(i = 0; i < TamaGolemMain.NUM_ELEMENTI -1 ; i++)
		{
			somma = 0;
			
				for(j = 0; j < TamaGolemMain.NUM_ELEMENTI-1; j++) 
				{
					somma += matEquilibrio[i][j];
					sommaVerticale = 0;
					if( j > i) {
						for(k = 0; k < TamaGolemMain.NUM_ELEMENTI; k++) {
							sommaVerticale += matEquilibrio[k][j];
						}
						rnd = generatoreCasuale.nextInt(Golem.VITA_INIZIALE) + 1;
						segno = generatoreCasuale.nextInt(Golem.VITA_INIZIALE) +1;
						if(segno % 2 != 0)
							rnd = -rnd;
						if((somma > 0 && rnd > 0) | ( somma < 0 && rnd < 0))
							rnd = -rnd;
							
							
						if( Math.abs(somma + rnd) > Golem.VITA_INIZIALE || Math.abs(sommaVerticale + rnd) > Golem.VITA_INIZIALE) {
							do 
							{
								if(rnd > 0)
									rnd -= (Golem.VITA_INIZIALE*0.1);
								else
									rnd += (Golem.VITA_INIZIALE*0.1);
							}while( Math.abs(somma + rnd) >= Golem.VITA_INIZIALE || Math.abs(sommaVerticale + rnd)  >= Golem.VITA_INIZIALE);
						}
						
						/**
						 * se siamo nella penultima colonna e la somma con il valore per questa posizione da 0
						 * bisogna intervenire per evitarlo
						 */
						if(j == TamaGolemMain.NUM_ELEMENTI -2 && somma == 0) 
							rnd += 1;
							
						matEquilibrio[i][j] = rnd;
						matEquilibrio[j][i] = -matEquilibrio[i][j];
						somma += rnd;
						}
				}
				matEquilibrio[i][j] = -somma;
				matEquilibrio[j][i] = -matEquilibrio[i][j];
		}
	}
		
			
	
	public String mostraMatriceEquilibrio() {
		StringBuffer equilibrio = new StringBuffer();
		for(int i = 0; i < TamaGolemMain.NUM_ELEMENTI; i ++) {
			for(int j = 0; j < TamaGolemMain.NUM_ELEMENTI; j++) {
				equilibrio.append(String.format("%4d ", matEquilibrio[i][j]));
			}
			equilibrio.append("\n");
		}
		String str = equilibrio.toString();
		return str;
	}
}
	
	
