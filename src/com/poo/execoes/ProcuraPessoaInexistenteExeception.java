package com.poo.execoes;

public class ProcuraPessoaInexistenteExeception extends Exception{
	
	private String nome;
	
	public ProcuraPessoaInexistenteExeception(){
		
		super("PESSOA NAO EXISTENTE!!!");
		
	}
	
}
