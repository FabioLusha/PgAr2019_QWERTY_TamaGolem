package it.unibs.fp.TamaGolem;

public class Giocatore {

	private String nome;
	/**
	 * Il numero di golem a disposizione dell'utente viene salvata in una vaiabile intera che viene modificata ogni volta che è necessario
	 */
	private int numGolemDisponibili;
	

	public Giocatore(String nome) {
		this.nome = nome;
		this.numGolemDisponibili = TamaGolemMain.numGolem;
	}

	public Giocatore() {
		this("XXX");
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNumGolemDisponibili() {
		return numGolemDisponibili;
	}

	public void setNumGolemDisponibili(int nuovoVal){
		this.numGolemDisponibili = nuovoVal;
	}

	public void diminuisciNumGolem(){
		this.numGolemDisponibili = this.numGolemDisponibili - 1;
	}
}
