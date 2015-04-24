package com.poo.negocios.beans;

import java.io.Serializable;

public class Automoveis implements Serializable {
	
	//atributos
	
	private String marca;
	private String modelo;
	private String anoModelo;
	private String vercao;
	
	//construtor
	
	public Automoveis(String marca, String modelo, String anoModelo,
			String vercao) {
		
		this.setMarca(marca);
		this.setModelo(anoModelo);
		this.setAnoModelo(anoModelo);
		this.setVercao(vercao);
	}

	
	//metodos
	
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		if((marca != null)&&(marca.length()==0))
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		if((modelo != null)&&(modelo.length()==0))
		this.modelo = modelo;
	}

	public String getAnoModelo() {
		return anoModelo;
	}

	public void setAnoModelo(String anoModelo) {
		if((anoModelo != null)&&(anoModelo.length()==0))
		this.anoModelo = anoModelo;
	}

	
	public String getVercao() {
		return vercao;
	}


	public void setVercao(String vercao) {
		if((vercao != null)&&(vercao.length()==0))
		this.vercao = vercao;
	}


	@Override
	public String toString() {
		return "MARCA: " + marca + "  MODELO: " + modelo
				+ "\n ANO MODELO: " + anoModelo + "\nVERSAO" + vercao + "\n";
	}
	
	
	
	
		
	
	
	

}
