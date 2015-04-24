package com.poo.negocios.beans;

import java.io.Serializable;

public class Cliente extends Colaboradores implements Serializable {

	// atributos

	private String cnh;
	private String datPrimeiraHab;
	private String vencHab;
	private String profissao;
	private String escolaridade;
	private Automoveis automovel;
	private Contratos contrato;
	private static int numeroDeClientes = 1;

	// construtor

	public Cliente(Pessoa pessoa, String cnh, String datPrimeiraHab,
			String vencHab, String profissao, String escolaridade,
			Automoveis automovel) {

		super(pessoa);
		this.setCnh(cnh);
		this.setDatPrimeiraHab(datPrimeiraHab);
		this.setVencHab(vencHab);
		this.setProfissao(profissao);
		this.setEscolaridade(escolaridade);
		this.setAutomovel(automovel);
		numeroDeClientes = numeroDeClientes + 1;
	}

	public Cliente(String senhas) {

		this.senha = senhas;

	}

	public Cliente(Contratos contrato) {

		this.setContrato(contrato);

	}

	// metodos

	public String getCnh() {
		return cnh;
	}

	public void setCnh(String cnh) {
		if (cnh != null)
			this.cnh = cnh;
	}

	public String getDatPrimeiraHab() {
		return datPrimeiraHab;
	}

	public void setDatPrimeiraHab(String datPrimeiraHab) {
		if (datPrimeiraHab != null)
			this.datPrimeiraHab = datPrimeiraHab;
	}

	public String getVencHab() {
		return vencHab;
	}

	public void setVencHab(String vencHab) {
		if (vencHab != null)
			this.vencHab = vencHab;
	}

	public String getProfissao() {

		return profissao;
	}

	public void setProfissao(String profissao) {
		if (profissao != null)
			this.profissao = profissao;
	}

	public String getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(String escolaridade) {
		if (escolaridade != null)
			this.escolaridade = escolaridade;
	}

	public static int getNumeroDeClientes() {

		return numeroDeClientes;
	}

	public Contratos getContrato() {
		return contrato;
	}

	public void setContrato(Contratos contrato) {
		if (contrato != null)
			this.contrato = contrato;
	}

	public Automoveis getAutomovel() {
		return automovel;
	}

	public void setAutomovel(Automoveis automovel) {
		if (automovel != null)
			this.automovel = automovel;
	}

	@Override
	public String toString() {
		return pessoa + "\nCNH: " + cnh + " \nDATA DA PRIMEIRA HABILITACAO: "
				+ datPrimeiraHab + "  VENCIMENTO DA HABILITCAO: " + vencHab
				+ "\nPROFISSAO: " + profissao + "  ESCOLARIDADE: "
				+ escolaridade + "\nVeiculo:" + automovel + contrato
				+ dataCadastro;
	}

}
