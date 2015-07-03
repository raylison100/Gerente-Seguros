package com.poo.negocios;

import com.poo.execoes.CPFInvalidoExeception;

import java.io.IOException;

import com.poo.execoes.CPFCadastradoExeception;
import com.poo.execoes.CadatroAutoExistenteExeception;
import com.poo.execoes.CadatroContraExistenteExeception;
import com.poo.execoes.CadatroPessoaExistenteExeception;
import com.poo.execoes.CadatroSiniExistenteExeception;
import com.poo.execoes.ProcuraAutoInexistenteExeception;
import com.poo.execoes.ProcuraContraInexistenteExeception;
import com.poo.execoes.ProcuraPessoaInexistenteExeception;
import com.poo.execoes.ProcuraSiniInexistenteExeception;
import com.poo.execoes.SenhaIncorretaExeception;
import com.poo.negocios.beans.Automovel;
import com.poo.negocios.beans.Contrato;
import com.poo.negocios.beans.Pessoa;
import com.poo.negocios.beans.Sinistro;

public interface IGerenciador {

	public void cadatrarCliente(String nome, String cpf, String rg, String datEmissao,
			String orgaoEmissao, String sexo, String telefone,
			String estadoCivil,String cnh, String datPrimeiraHab,
			String vencHab, String profissao, String escolaridade,String logradouro, String bairro, String cep,
			String numero, String complemento, String cidade, String estado) throws IOException,
			CadatroPessoaExistenteExeception,
			CPFInvalidoExeception, CPFCadastradoExeception;

	public Pessoa[] exibiCliente() throws IOException;

	public Pessoa[] exibiFuncionario() throws IOException;

	public void excluirPessoa(String nome) throws IOException,
			ProcuraPessoaInexistenteExeception;

	public Pessoa pesquisarPessoa(String nome)
			throws ProcuraPessoaInexistenteExeception;

	public boolean validarSenhaF(String nome, char[] senha)
			throws ProcuraPessoaInexistenteExeception, SenhaIncorretaExeception;

	public boolean validarSenhaS(String nome, char[] senha)
			throws ProcuraPessoaInexistenteExeception, SenhaIncorretaExeception;

	public void cadatrarAuto(String marca, String modelo, String versao,String placa) throws IOException,
			CadatroAutoExistenteExeception, ProcuraAutoInexistenteExeception;

	public Automovel[] exibiAuto() throws IOException;

	public void excluirAuto(String placa) throws IOException,
			ProcuraAutoInexistenteExeception;

	public Automovel pesquisarAutomovel(String placa)
			throws ProcuraAutoInexistenteExeception;

	public void cadatrarSini(Sinistro s) throws IOException,
			CadatroSiniExistenteExeception, ProcuraSiniInexistenteExeception;

	public Sinistro[] exibiSini() throws IOException;

	public void excluirSini(int numero) throws IOException,
			ProcuraSiniInexistenteExeception;

	public Sinistro pesquisarSinistro(int numero)
			throws ProcuraSiniInexistenteExeception;

	public void cadatrarContra(Contrato c) throws IOException,
			CadatroContraExistenteExeception,
			ProcuraContraInexistenteExeception;

	public Contrato[] exibiContra() throws IOException;

	public void excluirContra(int numero) throws IOException,
			ProcuraContraInexistenteExeception;

	public Contrato pesquisarContrato(int numero)
			throws ProcuraContraInexistenteExeception;

    public Automovel lastAutomovel();

}
