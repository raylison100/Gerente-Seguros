package com.poo.negocios.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;

import com.poo.execoes.CPFInvalidoExeception;

public class Pessoa implements Serializable {

	// atributos

	private Calendar calendar;
	private SimpleDateFormat formatter;
	private Date minhaDataEncapsulada;
	private String dataFormatada;

	

	protected String nome;
	protected String cpf;
	protected String rg;
	protected String datEmissao;
	protected String orgaoEmissao;
	protected String sexo;
	protected String telefone;
	protected String estadoCivil;
	protected Endereco endereco;
	

	public Pessoa() {

	}

	public Pessoa(String nome, String cpf, String rg, String datEmissao,
			String orgaoEmissao, String sexo, String telefone,
			String estadoCivil, Endereco endereco) throws CPFInvalidoExeception {

		
		this.setNome(nome);
		this.setCpf(cpf);
		this.setRg(rg);
		this.setDatEmissao(datEmissao);
		this.setOrgaoEmissao(orgaoEmissao);
		this.setSexo(sexo);
		this.setTelefone(telefone);
		this.setEstadoCivil(estadoCivil);
		this.setEndereco(endereco);
		this.calendar = Calendar.getInstance();
		this.formatter = new SimpleDateFormat("dd/MMM/YYYY    HH: mm: ss");
		this.minhaDataEncapsulada = calendar.getTime();
		this.dataFormatada = formatter.format(minhaDataEncapsulada);

	}

	// metodos

	public String getDataFormatada() {
		return dataFormatada;
	}
	
	

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

		if (this.validarCPF(cpf) == true)
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

	public boolean validarCPF(String numeroCPF) throws CPFInvalidoExeception {

		// considera-se erro CPF's formados por uma sequencia de numeros iguais
		if (numeroCPF.equals("00000000000") || numeroCPF.equals("11111111111")
				|| numeroCPF.equals("22222222222") || numeroCPF.equals("33333333333")
				|| numeroCPF.equals("44444444444") || numeroCPF.equals("55555555555")
				|| numeroCPF.equals("66666666666") || numeroCPF.equals("77777777777")
				|| numeroCPF.equals("88888888888") || numeroCPF.equals("99999999999")
				|| (numeroCPF.length() != 11))
			return (false);
		char dig10, dig11;
		int sm, i, r, num, peso;

		try {
			// Calculo do 1o. Digito Verificador
			sm = 0;
			peso = 10;
			for (i = 0; i < 9; i++) { //
				// converte o i-esimo caractere do CPF em um numero: por
				// exemplo,
				// transforma o caractere '0' no inteiro 0 (48 eh a
				// posicao de '0' na tabela ASCII)
				num = (int) (numeroCPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}
			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig10 = '0';
			else
				dig10 = (char) (r + 48);
			// converte no respectivo caractere numerico
			// Calculo do 2o. Digito Verificador
			sm = 0;
			peso = 11;
			for (i = 0; i < 10; i++) {
				num = (int) (numeroCPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}
			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig11 = '0';
			else
				dig11 = (char) (r + 48);
			// Verifica se os digitos calculados
			// conferem com os digitos informados.
			if ((dig10 == numeroCPF.charAt(9)) && (dig11 == numeroCPF.charAt(10)))
				return (true);
			else
				return (false);
		} catch (InputMismatchException erro) {
			return (false);
		}

	}

	@Override
	public String toString() {
		return "\n\n                      " + dataFormatada  +"\nNOME: " + nome + "\nCPF: " + cpf +
				"  RG: " + rg + "  DATA DE EMISSAO: " + datEmissao + "  ORGAO EMISSOR: "
				+ orgaoEmissao + "\nSEXO: " + sexo + "  TELEFONE: " + telefone
				+ "  ESTADO CIVL: " + estadoCivil + endereco;
	}

}
