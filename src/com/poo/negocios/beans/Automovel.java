package com.poo.negocios.beans;

import java.io.Serializable;

public class Automovel implements Serializable {
	
	//atributos
	
	private String marca;
	private String modelo;
	private String anoModelo;
	private String vercao;
	private Sinistro sinistro;
	private String placa;
	
	//construtor
	
	public Automovel(String marca, String modelo, String anoModelo,
			String vercao,String placa ){
		
		this.setMarca(marca);
		this.setModelo(anoModelo);
		this.setAnoModelo(anoModelo);
		this.setVercao(vercao);
		this.setPlaca(placa);
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

	public String getAnoModelo() {
		return anoModelo;
	}

	public void setAnoModelo(String anoModelo) {
		if(anoModelo != null)
		this.anoModelo = anoModelo;
	}

	
	public String getVercao() {
		return vercao;
	}


	public void setVercao(String vercao) {
		if(vercao != null)
		this.vercao = vercao;
	}
	
	public Sinistro getSinistro() {
		return sinistro;
	}


	public void setSinistro(Sinistro sinistro) {
		if(sinistro != null)
		this.sinistro = sinistro;
	}


	@Override
	public String toString() {
		return "\n\nMARCA: " + marca + "MODELO: " + modelo
				+ "\nANO MODELO: " + anoModelo + "  VERSAO: " + vercao + "   Placa: " + placa +"\n";
	}


	public String getPlaca() {
		return placa;
	}


	public void setPlaca(String placa) {
		this.placa = placa;
	}


	
}
