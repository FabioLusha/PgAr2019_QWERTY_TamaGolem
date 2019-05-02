package it.unibs.fp.TamaGolem;

public class Giocatore {
	public static final int NUMGOLEM = 4;
	private String nome;
	private Golem[] golem = new Golem[NUMGOLEM];
	
	public Giocatore() {
		this.nome = "";
		this.golem = null;
	}
	public Giocatore(String nome, Golem[] golem) {
		super();
		this.nome = nome;
		this.golem = golem;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Golem[] getGolem() {
		return golem;
	}

	public void setGomlem(Golem[] golem) {
		this.golem = golem;
	}
}
