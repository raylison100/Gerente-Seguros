package com.poo.negocios.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Contrato implements Serializable {

	// atributos

	private String dataInicial; // fazer calculo do calendario do computador e
								// dizer dias restantes para termino do
								// contrato.
	private String dataFinal; // somatorio do plano com a data inicial do
							  // contrato.
	private int numeroCliente;
	private int numeroContrato;
	private int categoria;
	private String duracao;
	private String anotatoes;
	private float valor;
	private Automovel automovel;
	private static int numContratosFeitos = 1;

	// construtor

	// fazer o calculo da data final com base na data de inicio e na duracao
	// *obs modificar depois
	public Contrato(String dataInicial, String dataFinal, String duracao,
			float valor, String anotatoes, int categoria, int numeroCliente) {

		this.setDataInicial(dataInicial);
		this.setDataFinal(dataFinal);
		this.setDuracao(duracao);
		this.setValor(valor);
		this.setAnotatoes(anotatoes);
		this.setNumeroCliente(numeroCliente);
		this.setCategoria(categoria);
		this.setNumeroContrato();
		numContratosFeitos = numContratosFeitos + 1;
	}

	// metodos

	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	public int getNumeroCliente() {
		return numeroCliente;
	}

	public void setNumeroCliente(int numeroCliente) {
		this.numeroCliente = numeroCliente;
	}

	public int getNumeroContrato() {
		return numeroContrato;
	}

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
	
	private int setNumeroContrato(){
		SimpleDateFormat anoC = new SimpleDateFormat("yyyy"); 
		int ano = Integer.valueOf(anoC.format(new Date()));
		return ano+this.categoria+this.numeroCliente;
	}
	
	public Automovel getAutomevel() {
		return automovel;
	}

	public void setAutomevel(Automovel automovel) {
		if(automovel != null)
		this.automovel = automovel;
	}

	
	@Override
	public String toString() {
		return "\nINICIO: " + dataInicial + "  TERMINO: " + dataFinal
				+ "  DURACAO: " + duracao + "\nANOTAOES: " + anotatoes
				+ "\nVALOR" + valor;
	}

	
}
