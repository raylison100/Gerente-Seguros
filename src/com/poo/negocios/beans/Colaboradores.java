package com.poo.negocios.beans;

import java.io.Serializable;



public class Colaboradores implements Serializable{

	protected Pessoa pessoa;
	protected Calendario dataCadastro;
	protected String senha;
	
	
	public Colaboradores(){
		
	}
	
	public Colaboradores(Pessoa pessoa) {
	
		this.setPessoa(pessoa);
		this.dataCadastro = new Calendario();
		
	}
	
	


	public Pessoa getPessoa() {
		return pessoa;
	}


	public void setPessoa(Pessoa pessoa) {
		if(pessoa != null)
			this.pessoa = pessoa;
		
	}


	public Calendario getDataCadastro() {
		return dataCadastro;
	}


	public void setDataCadastro(Calendario dataCadastro) {
		this.dataCadastro = dataCadastro;
	}


	public String getSenha() {
		
		return senha;
	}


	public void cadatraSenha(String senha){
	    if(senha != null)
		this.senha = senha;	
		
	}

	@Override
	public String toString() {
		return  pessoa + "\n\n";
				
	}
	
	
	

}
