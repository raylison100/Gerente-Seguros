package com.poo.negocios.beans;

import java.io.Serializable;

public class Contratos implements Serializable {

	// atributos

	private String dataInicial; // fazer calculo do calendario do computador e
								// dizer dias restantes para termino do
								// contrato.
	private String dataFinal; // somatorio do plano com a data inicial do
								// contrato.
	private String duracao;
	private String anotatoes;
	private float valor;
	// private Automoveis veiculo;
	private static int numContratosFeitos = 1;

	// construtor

	// fazer o calculo da data final com base na data de inicio e na duracao
	// *obs modificar depois
	public Contratos(String dataInicial, String dataFinal, String duracao,
			float valor, String anotatoes) {

		this.setDataInicial(dataInicial);
		this.setDataFinal(dataFinal);
		this.setDuracao(duracao);
		this.setValor(valor);
		this.setAnotatoes(anotatoes);
		numContratosFeitos = numContratosFeitos + 1;
	}

	// metodos

	public String getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(String dataInicial) {
		if (dataInicial != null)
			this.dataInicial = dataInicial;
	}

	public String getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(String dataFinal) {
		if (dataFinal != null)
			this.dataFinal = dataFinal;
	}

	public String getDuracao() {
		return duracao;
	}

	public void setDuracao(String duracao) {
		if (duracao != null)
			this.duracao = duracao;
	}

	public String getAnotatoes() {
		return anotatoes;
	}

	public void setAnotatoes(String anotatoes) {
		if (anotatoes != null)
			this.anotatoes = anotatoes;
	}

	public static int getNumContratosFeitos() {
		return numContratosFeitos;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		if (valor < 0)
			this.valor = valor;
	}

	@Override
	public String toString() {
		return "\nINICIO: " + dataInicial + "  TERMINO: " + dataFinal
				+ "  DURACAO: " + duracao + "\nANOTAOES: " + anotatoes
				+ "\nVALOR" + valor;
	}

}
