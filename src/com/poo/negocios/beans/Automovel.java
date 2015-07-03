package com.poo.negocios.beans;

import java.io.Serializable;
import java.util.ArrayList;

public class Automovel implements Serializable {
	
	//atributos
	
	private String marca;
	private String modelo;	
	private String vercao;
	private ArrayList<Sinistro> sinistro;
	private String placa;
	
	//construtor
	
	public Automovel(String marca, String modelo, 
			String vercao,String placa ){
		
		this.setMarca(marca);
		this.setModelo(modelo);
		
		this.setVercao(vercao);
		this.setPlaca(placa);
                sinistro = new ArrayList<>();
	}

	
	//metodos
	
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		if(marca != null)
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		if(modelo != null)
		this.modelo = modelo;
	}

	
	public String getVercao() {
		return vercao;
	}


	public void setVercao(String vercao) {
		if(vercao != null)
		this.vercao = vercao;
	}
	
	public ArrayList<Sinistro> getSinistro() {
		return sinistro;
	}


	public void setSinistro(Sinistro sinistro) {
		if(sinistro != null)
		this.sinistro.add(sinistro);
	}


	@Override
	public String toString() {
		return "\n\nMARCA: " + marca + "MODELO: " + modelo
				+ "\n"  + "VERSAO: " + vercao + "   Placa: " + placa +"\n";
	}


	public String getPlaca() {
		return placa;
	}


	public void setPlaca(String placa) {
		this.placa = placa;
	}


	
}
