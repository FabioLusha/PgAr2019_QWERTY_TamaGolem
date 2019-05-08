package it.unibs.fp.TamaGolem;

import java.util.Random;

/**
 * Classe finalizzata a gestire la presenza e la quantità di elementi che saranno necessari per equipaggiare i golem
 * @author QWERTY
 *
 */
public class Sacco {

	/**
	 * l'array per ogni elemento da 0 a n (rappresentato dalla posizione nel vettore) contiene il numero di pietre presenti per ciascun elemento
	 */
	private int[] quantitaDiOgniElemento; 
	
	/**
	 * il costruttore inizilizza l'array quantitaDiOgniElemento con i reletivi valori però, visto che la diponibilià di un elemento
	 *  nel sacco e dato dal rappoto dalle pietre disponibili con il numero di elementi diponibili.
	 * La divisione tra interi avviene senza arrotondamento pertanto può risultare che il prodotto del numero di pietre di un elemento nel
	 * sacco per il numero di elementi sia minore del numero di pietre totali. Per ovviare a questo problema, aggiungo nel sacco delle pietre
	 * estratte casualmente finchè non si pareggiano i conti (ref1).
	 */
	public Sacco() {
		Random randomGenerator = new Random();
		quantitaDiOgniElemento = new int[TamaGolemMain.numElementi];
		for(int i = 0; i < TamaGolemMain.numElementi; i++) 
			quantitaDiOgniElemento[i] = TamaGolemMain.elementoNelSacco;
		//ref1
		for(int i = 0; i < (TamaGolemMain.pietreNelSacco - (TamaGolemMain.numElementi * TamaGolemMain.elementoNelSacco)); i++) {
			int tmp = randomGenerator.nextInt(TamaGolemMain.numElementi);
			quantitaDiOgniElemento[tmp]++;
		}
	}

	public int[] getQuantitaElementi() {
		return quantitaDiOgniElemento;
	}

	public int getQuantitaElemento(int i) {
		return quantitaDiOgniElemento[i];
	}

	public void setQuantitaElementi(int[] quantitaElementi) {
		this.quantitaDiOgniElemento = quantitaElementi;
	}
	
	/**
	 * Creo una stringa incollonata con l'indice del Elemento, il nome e il numero di pietre di questo elemento disponibile.
	 * x - NOME_ELEMENTO, Pietre disponibile: y
	 */
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("\n        PIETRE DISPONIBILI       \n");
		for(int i = 0; i < TamaGolemMain.numElementi; i++) {
			if(quantitaDiOgniElemento[i] > 0)
				str.append(i + " - " + TamaGolemMain.ELEMENTI[i] + ", Pietre disponibili: " + quantitaDiOgniElemento[i] + "\n");
		}
		
		return str.toString();
	}
	
	/**
	 * Diminuisce la quantità di elemento nel sacco in base alla scelta dell'utene
	 * @param indiceElemento l'indice dell'elemento da togliere
	 * @param quantitaDaTogliere la quantità dell'elemento da togliere
	 */
	public void togliPietre(int indiceElemento, int quantitaDaTogliere) {
		this.quantitaDiOgniElemento[indiceElemento] -= quantitaDaTogliere;
	}
	
	/**
	 * Ottengo l'array di piete da reinserire, lo ciclo e per ogni elemento in pietreDaReinserie (pietreDaReinserie[i])
	 *  aumento la quantità dell'elemento del sacco di uno.
	 * @param pietreDaReinserire (le pietre che devono essere reinserite nel sacco)
	 */
	public void rimettiPietreNelSacco(int[] pietreDaReinserire) {
		for(int i = 0; i < pietreDaReinserire.length; i++) 
			quantitaDiOgniElemento[pietreDaReinserire[i]]++;
		
	}
	


}
