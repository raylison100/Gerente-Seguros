package com.poo.negocios.beans;

import java.io.Serializable;



public class Funcionarios extends Colaboradores implements Serializable{

	// atributos

	private String cargo;
	private static int numeroFuncionarios = 0;
	

	// construtor

	public Funcionarios(Pessoa pessoa, String cargo) {
		super(pessoa);
		this.setCargo(cargo);
		numeroFuncionarios = numeroFuncionarios + 1;
		
	}
	
	public Funcionarios(String senha){
		
		this.cadatraSenha(senha);
		
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
		return  pessoa + "Cargo" + cargo;
	}

	

}
