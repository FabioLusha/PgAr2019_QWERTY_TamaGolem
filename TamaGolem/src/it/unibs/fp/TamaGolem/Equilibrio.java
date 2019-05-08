package it.unibs.fp.TamaGolem;

import java.util.Random;

public class Equilibrio {
	
	private int[][] matEquilibrio;
	private Random generatoreCasuale = new Random(System.currentTimeMillis());

	/**
	 * ALGORITMO PER LA GENERAZIONE DELL'EQUIILIBRIO
	 *Inizio col creare una martrice nxn (NUM_ELEMENTIxNUM_ELEMENTI), dove ogni riga e colonna rappresenta un elemento così possiamo 
	 * rappresentare l'interazione tra 2 elementi in ciascuna casella identificata dalla riga dell'elemento A e dalla colonna dell'elemento B. 
	 * Come da consegna l'interazione tra elementi dello stesso tipo è nulla, pertanto la matrice presenta una diagonale di zeri. 
	 *
	 *
	 * 1) Abbiamo assunto che se 2 elementi A - B hanno interazione x, allora l'interazione inversa B - A è di valore -x. Questa è 
	 * l'idea di base fondamentale per la creazione della matrice. Infatti la matrice viene riempita come se fosse una traingolare con 
	 * elementi non nulli solo nella triangolare superiore (diagonale esclusa):
	 *
	 *    a b c d
	 * a  0 x y z
	 * b  0 0 k j
	 * c  0 0 0 i
	 * d  0 0 0 0
	 *
	 * 2) già così potremmo dedurre tutte le interazioni tra tutti gli elementi ma risulterbbe un po' scomodo; infatti per avere 
	 * l'interazione a - b basta che accedo alla elemento con indice di riga 0 (a) e indice di colonna 1 (b) ([a][b]), se mi servisse 
	 * l'interazione inversa b - a, la cosa ovvia da fare sarebbe accedere all'elemento [b][a], ma questo risulta vuoto, pertanto, per 
	 * l'assunzione al punto 1, dovrei accedere all'elemento [a][b] e cambiarlo di segno. Per evitare questo controllo in più ho deciso
	 *  che durante l'inizializzazione della matrice quando viene generata l'interazione tra 2 elementi a - b di valore x in posizione
	 *  [a][b], nella posizione [b][a] viene inserito il valore di -x e alla fine ottenere questo:
	 *
	 *     a  b  c  d
	 * a   0  x  y  z
	 * b  -x  0  k  j
	 * c  -y -k  0  i
	 * d  -z -j -i  0
	 *
	 * in questo modo posso sapere l'interazione tra qualsiasi 2 elementi conoscendo l'indice di riga del primo e l'indice di 
	 * colonna del secondo. Questo metodo di implementare la matrice mi risulterà utile successivamente, per la generazione dei
	 * valori casuali di interazione rispettando tutte le condizioni richieste dall'esercizio.
	 *
	 * 3)GENERAZIONE DEI VALORI DI INTERAZIONE PSEUDO-CASUALI
	 * Innanzitutto creo un oggetto Random il quale servirà per la generazione dei numeri casuali. A questo punto genero 2 valori casuali:
	 * il primo è il valore di interazione tra 2 elementi (ref1), il secondo (ref2) serve per definire il segno del primo. Se non cambiassi il segno
	 * risulterebbe che il primo elemento avrebbe interazione positiva (cioè danneggia) tutti gli altri elementi (tranne l'ultimo).
	 * Dato che la somma delle interazioni deve fare 0 riempio l'ultimo elemento della riga con la somma di tutti i precedenti elementi della
	 * riga cambiata di segno (ref3). Ovviamente la somma deve essere ri-inizializzata 0.
	 * Nessun elemento però deve eccedere il tetto della vita massima del TamaGolem e quindi nemmeno la somma, perciò applico un controllo che faccia
	 *  a tendere la somma in un intorno di 0. Implemento questo controllo nel punto ref4. Controllo se il segno della somma delle interazioni precedenti 
	 * coincide con quello della nuova interazione. In caso positvo cambio il segno della nuova interazione.
	 * Questo però non basta per evitare che un'interazione superi il livello di vita iniziale del TamaGolem, bisogna implementare un ulteriore 
	 * controllo (ref5): devo verificare che la somma degli elementi precedentemente generti + la nuova iterazione generata non superi la vita massima
	 *  del tamagolem. Se questa condizione risulta falsa (cioè somma+interazione maggore vitaMax)  allora diminuisco il valore di interazione del 10%
	 *  (se il valore è positivo, aumento se è negativo) della vita massima del TamaGolem finchè somma+interazione risulta minore vita massima Tamagolem. 
	 *  Però devo stare attento che dopo queste operazioni l'interazione non risulti nulla, perciò la implemetno come un ulteriore condizione per rimanre 
	 *  all'interno del ciclo. Tutto il discorso sopra vale anche per le colonne, in particolare la colonna dove viene inerito il nuovo dato, in quanto 
	 *  una colonna rappresenta, anche se con i segni invertiti, le interazioni di un elemento con tutti gli altri.
	 *
	 * infne se siamo nella penultima colonna e la somma della somma precedente con il valore di questa interazione risulta 0 bisogna intervenire per evitarlo (ref6)
	 */
	public Equilibrio() 
	{
		matEquilibrio = new int[TamaGolemMain.numElementi][TamaGolemMain.numElementi];
		int  k, i, j, somma, sommaVerticale, interazione, segno;
		
			
		for(i = 0; i < TamaGolemMain.numElementi -1 ; i++)
		{
			somma = 0;
			
				for(j = 0; j < TamaGolemMain.numElementi-1; j++) 
				{
					somma += matEquilibrio[i][j];
					sommaVerticale = 0;
					if( j > i)
					{
						for(k = 0; k < TamaGolemMain.numElementi; k++) {
							sommaVerticale += matEquilibrio[k][j];
						}
						//ref1
						interazione = generatoreCasuale.nextInt(Golem.VITA_INIZIALE) + 1;
						//ref2
						segno = generatoreCasuale.nextInt(Golem.VITA_INIZIALE) +1;
						if(segno % 2 != 0)
							interazione = -interazione;
						//ref4
						if((somma > 0 && interazione > 0) | ( somma < 0 && interazione < 0))
							interazione = -interazione;
							
						//ref5
						if( Math.abs(somma + interazione) > Golem.VITA_INIZIALE || Math.abs(sommaVerticale + interazione) > Golem.VITA_INIZIALE)
						{
							do 
							{
								if(interazione > 0)
									interazione -= (Golem.VITA_INIZIALE*0.1);
								else
									interazione += (Golem.VITA_INIZIALE*0.1);
							}while( Math.abs(somma + interazione) >= Golem.VITA_INIZIALE || Math.abs(sommaVerticale + interazione)  >= Golem.VITA_INIZIALE || interazione == 0);
						}

						//ref6
						if(j == TamaGolemMain.numElementi -2 && somma == 0)
						{
							if(interazione < 0)
								do interazione += 1; while(interazione == 0);
							if(interazione > 0)
								do interazione -= 1; while(interazione == 0);

						}

							
						matEquilibrio[i][j] = interazione;
						matEquilibrio[j][i] = -matEquilibrio[i][j];

						somma += interazione;
					}
				}
				//ref3
				matEquilibrio[i][j] = -somma;
				matEquilibrio[j][i] = -matEquilibrio[i][j];
		}
	}

	public int[][] getMatEquilibrio() {
		return matEquilibrio;
	}

	public int getInterazione(int indexRiga, int indexCol){
		return matEquilibrio[indexRiga][indexCol];
	}
	
	/**
	 * mostra i valori di interazione in forma matriciale
	 * @return una stringa che contiene la matrice di equilibrio
	 */
	public String mostraMatriceEquilibrio() {
		StringBuilder equilibrio = new StringBuilder();
		for(int i = 0; i < TamaGolemMain.numElementi; i ++) {
			for(int j = 0; j < TamaGolemMain.numElementi; j++) {
				equilibrio.append(String.format("%4d ", matEquilibrio[i][j]));
			}
			equilibrio.append("\n");
		}
		return equilibrio.toString();

	}
	
	/**
	 * mostra le varie interazione in modo più leggibile
	 * @return una stringa che contiene il nome dei 2 elementi e la loro interazione
	 */
	public String mostraEquilibrio() {
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < TamaGolemMain.numElementi; i++) {
			for(int j = 0; j < TamaGolemMain.numElementi; j++) {
				if(matEquilibrio[i][j] > 0)
					str.append(TamaGolemMain.ELEMENTI[i] + " dannegia -> " + TamaGolemMain.ELEMENTI[j] + "; punti danno: "+ matEquilibrio[i][j]+"\n");
			}
		}
			
		return str.toString();
	}
}
	
	
