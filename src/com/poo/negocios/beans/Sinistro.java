package com.poo.negocios.beans;



public class Sinistro {
	
	private String tipo; // parcial ou integral
	private String causa; // incendio, roubo, colisao, enchente, outros.
	private boolean outros; // no caso e outras causas.
	private String outraCausas;
	private String ocorencia;
	private static int  numeroSinistro = 0000;
	private int sinistroAtual;
	
	
	public static int getNumeroSinistro() {
		return numeroSinistro;
	}

	public static void setNumeroSinistro(int numeroSinistro) {
		Sinistro.numeroSinistro = numeroSinistro;
	}

	public int getSinistroAtual() {
		return sinistroAtual;
	}

	public Sinistro(String tipo, String causa, Boolean outros, String ocorencia) {
		
	    setTipo(tipo);
		setCausa(causa);
		setOutros(outros);
		setOcorencia(ocorencia);
		sinistroAtual = numeroSinistro;
		numeroSinistro = numeroSinistro + 1;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		if(tipo != null)
		this.tipo = tipo;
	}

	public String getCausa() {
		return causa;
	}

	public void setCausa(String causa) {
		if(causa != null)
		this.causa = causa;
	}

	
	public void setOutros(Boolean outros) {
		if(outros == null)
		this.outros = false;
		else this.outros = true;
	}

	public String getOcorencia() {
		return ocorencia;
	}

	public void setOcorencia(String ocorencia) {
		if(ocorencia != null)
		this.ocorencia = ocorencia;
		
	}
	
		
	public void setOutraCausas(String outraCausas) {
		if(outraCausas != null)
		this.outraCausas = outraCausas;
	}

	public void descricao(String descricao){
		if(outros == true)
			setOutraCausas(descricao);
		else setOutraCausas("Nao");
			
	}


	public String toString() {
		return "Sinitro :" +sinistroAtual+"\n" + "Tipo: " + tipo +  "  Causa: " + causa + "  Outros: "
				+ outraCausas + "\nOcorencia: " + ocorencia;
	}
	
	
	
	
	

}
