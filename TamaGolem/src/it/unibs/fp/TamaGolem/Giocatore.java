package it.unibs.fp.TamaGolem;

public class Giocatore {

	private String nome;
	private int numGolemDisponibili;
	

	public Giocatore(String nome) {
		this.nome = nome;
		this.numGolemDisponibili = TamaGolemMain.NUM_GOLEM;
	}

	public Giocatore() {
		this("");

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
		this.numGolemDisponibili--;
	}
}
