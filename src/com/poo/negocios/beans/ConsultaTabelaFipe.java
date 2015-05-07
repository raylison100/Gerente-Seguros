package com.poo.negocios.beans;


public class ConsultaTabelaFipe {

	//atributos
	
	private Automovel automovel;
	private float  precoMedio;
	private String dataConsultaTabela;
	private String anoAtual;
	
	//construtor
	
	public ConsultaTabelaFipe(Automovel automovel){
		
		this.setAutomovel(automovel);
		
	}

	//metodos
	
	public Automovel getAutomovel() {
		return automovel;
	}

	public void setAutomovel(Automovel automovel) {
		this.automovel = automovel;
	}

	public float getPrecoMedio() {
		return precoMedio;
	}

	public void setPrecoMedio(float precoMedio) {
		this.precoMedio = precoMedio;
	}

	public String getDataConsultaTabela() {
		return dataConsultaTabela;
	}

	public void setDataConsultaTabela(String dataConsultaTabela) {
		this.dataConsultaTabela = dataConsultaTabela;
	}

	public String getAnoAtual() {
		return anoAtual;
	}

	public void setAnoAtual(String anoAtual) {
		this.anoAtual = anoAtual;
	}

	@Override
	public String toString() {
		return "ConsultaTabelaFipe [automovel=" + automovel + ", precoMedio="
				+ precoMedio + ", dataConsultaTabela=" + dataConsultaTabela
				+ ", anoAtual=" + anoAtual + "]";
	}
	
	
	
}
