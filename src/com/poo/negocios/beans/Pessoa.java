package com.poo.negocios.beans;

import java.io.Serializable;

import com.poo.execoes.CPFInvalidoExeception;



public class Pessoa implements Serializable{

	// atributos
	protected String nome;
	protected String cpf;
	protected String rg;
	protected String datEmissao;
	protected String orgaoEmissao;
	protected String sexo;
	protected String telefone;
	protected String estadoCivil;
	protected Endereco endereco;
	protected Calendario dataCadastro;
	
	
	
	public Pessoa(){
		
	}
	
	public Pessoa(String nome, String cpf, String rg, String datEmissao,
			String orgaoEmissao, String sexo, String telefone,
			String estadoCivil, Endereco endereco)throws CPFInvalidoExeception {
	
		this.dataCadastro = new Calendario();
		this.setNome(nome);
		this.setCpf(cpf);
		this.setRg(rg);
		this.setDatEmissao(datEmissao);
		this.setOrgaoEmissao(orgaoEmissao);
		this.setSexo(sexo);
		this.setTelefone(telefone);
		this.setEstadoCivil(estadoCivil);
		this.setEndereco(endereco);
		
	}
	
	
	public Calendario getDataCadastro() {
		return dataCadastro;
	}


	public void setDataCadastro(Calendario dataCadastro) {
		this.dataCadastro = dataCadastro;
	}


	// metodos

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			if ((nome != null))
				this.nome = nome;
		}

		public String getCpf() {
			return cpf;
		}

		public void setCpf(String cpf) throws CPFInvalidoExeception {

			if (ValidadorCpf.validar(cpf) == true)
				this.cpf = cpf;
			else
				throw new CPFInvalidoExeception();
		}

		public String getRg() {
			return rg;
		}

		// verifica validacoes
		public void setRg(String rg) {
			if (rg != null)
				this.rg = rg;
		}

		public String getDatEmissao() {
			return datEmissao;
		}

		public void setDatEmissao(String datEmissao) {
			if (datEmissao != null)
				this.datEmissao = datEmissao;
		}

		public String getOrgaoEmissao() {
			return orgaoEmissao;
		}

		public void setOrgaoEmissao(String orgaoEmissao) {
			if (orgaoEmissao != null)
				this.orgaoEmissao = orgaoEmissao;
		}

		public String getSexo() {
			return sexo;
		}

		public void setSexo(String sexo) {
			if (sexo != null)
				this.sexo = sexo;
		}

		public String getTelefone() {
			return telefone;
		}

		public void setTelefone(String telefone) {
			if (telefone != null)
				this.telefone = telefone;
		}

		public String getEstadoCivil() {
			return estadoCivil;
		}

		public void setEstadoCivil(String estadoCivil) {
			if (estadoCivil != null)

				this.estadoCivil = estadoCivil;
		}

		public Endereco getEndereco() {
			return endereco;
		}

		public void setEndereco(Endereco endereco) {
			if (endereco != null)
				this.endereco = endereco;
		}

		@Override
		public String toString() {
			return "NOME: " + nome + "\nCPF: " + cpf + "  RG: " + rg
					+ "  DATA DE EMISSAO: " + datEmissao + "  ORGAO EMISSOR: "
					+ orgaoEmissao + "\nSEXO: " + sexo + "  TELEFONE: " + telefone
					+ "  ESTADO CIVL: " + estadoCivil + endereco;
		}
	
}










