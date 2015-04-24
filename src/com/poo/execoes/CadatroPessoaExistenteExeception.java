package com.poo.execoes;

public class CadatroPessoaExistenteExeception extends Exception {
	
	private String nome;
	public CadatroPessoaExistenteExeception(String nome){
		
		super("  " + nome + "\n" + "CLIENTE JA CADASTRADO!!!");
		this.setNome(nome);
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		if(nome!= null)
		this.nome = nome;
	}

}
