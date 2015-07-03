package com.poo.negocios.beans;

import java.io.Serializable;



public class Funcionario extends Pessoa implements Serializable{

	// atributos

	private String cargo;
	private static int numeroFuncionarios = 0;
	private char[] senha;
	
	

	// construtor

	public Funcionario(String cargo, char[] senha) {
		
		this.setCargo(cargo);
		numeroFuncionarios = numeroFuncionarios + 1;
		this.setSenha(senha);
		
		
	}
	
	
	// metodos

	

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		if(cargo != null)
		this.cargo = cargo;
	}
	
	

	public static int getNumeroFuncionarios() {
		return numeroFuncionarios;
	}

	@Override
	public String toString() {
		return  this.toString() + "Cargo" + cargo;
	}


	public char[] getSenha() {
		return senha;
	}


	public void setSenha(char[] senha) {
		if(senha != null && senha.length < 8 && senha.length >= 5)
		this.senha = senha;
	}

}
