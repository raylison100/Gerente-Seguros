package com.poo.execoes;

public class ProcuraPessoaInexistenteExeception extends Exception{
	
	private String nome;
	
	public ProcuraPessoaInexistenteExeception(String nome){
		
		super("PESSOA NAO EXISTENTE!!!");
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
