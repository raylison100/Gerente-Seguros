package com.poo.negocios.beans;

import java.io.Serializable;



public class Funcionario extends Pessoa implements Serializable{

	// atributos

	private String cargo;
	private static int numeroFuncionarios = 0;
	

	// construtor

	public Funcionario(String cargo) {
		
		this.setCargo(cargo);
		numeroFuncionarios = numeroFuncionarios + 1;
		
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

	

}
