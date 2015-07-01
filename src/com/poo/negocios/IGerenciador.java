package com.poo.negocios;

import java.io.IOException;

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

	public void cadatrarCliente(Pessoa c) throws IOException,
			CadatroPessoaExistenteExeception,
			ProcuraPessoaInexistenteExeception;

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

	public void cadatrarAuto(Automovel a) throws IOException,
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

}
