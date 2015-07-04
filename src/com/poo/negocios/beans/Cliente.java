package com.poo.negocios.beans;

import java.io.Serializable;

import com.poo.execoes.CPFInvalidoExeception;
import java.util.ArrayList;

public class Cliente extends Pessoa implements Serializable {

	// atributos

	private String cnh;
	private String datPrimeiraHab;
	private String vencHab;
	private String profissao;
	private String escolaridade;
	private ArrayList <Automovel> automovel;
	private ArrayList <Contrato> contrato;
	private static int numeroDeClientes = 1;

	// construtor

	public Cliente(String nome, String cpf, String rg, String datEmissao,
			String orgaoEmissao, String sexo, String telefone,
			String estadoCivil, Endereco endereco,String cnh, String datPrimeiraHab,
			String vencHab, String profissao, String escolaridade) throws CPFInvalidoExeception {

		
		super(nome, cpf, rg,  datEmissao,
				 orgaoEmissao,  sexo,  telefone,
				estadoCivil,  endereco);
		this.setCnh(cnh);
		this.setDatPrimeiraHab(datPrimeiraHab);
		this.setVencHab(vencHab);
		this.setProfissao(profissao);
		this.setEscolaridade(escolaridade);
		this.automovel =  new ArrayList<>();
                contrato = new ArrayList<>();
		numeroDeClientes = numeroDeClientes + 1;
		numeroDeClientes = numeroDeClientes + 1;
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

	public Contrato getContrato(int nume) {
	    int index = 0;
            
            for(int i = 0;i < contrato.size();i++){
                if(nume == contrato.get(i).getNumeroContrato())
                    index = i;
            }
            
            return contrato.get(index);
	}

	public void setContrato(Contrato contrato) {
		if (contrato != null)
			this.contrato.add(contrato);
	}

	public ArrayList<Automovel> getAutomovel() {
		return automovel;
	}

	public void setAutomovel(Automovel automovel) {
		if (automovel != null)
			this.automovel.add(automovel);
	}

	@Override
	public String toString() {
		return  super.toString() + "\nCNH: " + cnh + " \nDATA DA PRIMEIRA HABILITACAO: "
				+ datPrimeiraHab + "  VENCIMENTO DA HABILITCAO: " + vencHab
				+ "\nPROFISSAO: " + profissao + "  ESCOLARIDADE: "
				+ escolaridade + "\n" + contrato;
				
	}

}
