package com.poo.negocios;

import java.io.IOException;

import com.poo.dados.IRepositorioPessoa;
import com.poo.dados.RepositorioPessoa;
import com.poo.execoes.CPFCadastradoExeception;
import com.poo.execoes.CadatroPessoaExistenteExeception;
import com.poo.execoes.ProcuraPessoaInexistenteExeception;
import com.poo.execoes.SenhaIncorretaExeception;
import com.poo.negocios.beans.Cliente;
import com.poo.negocios.beans.Funcionario;
import com.poo.negocios.beans.Pessoa;

public class CadastroPessoa {

	private IRepositorioPessoa repositorio;

	public CadastroPessoa() {

		try {
			this.repositorio = RepositorioPessoa.getInstance();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void cadatrar(Pessoa c) throws IOException,
			CadatroPessoaExistenteExeception, CPFCadastradoExeception{
		this.repositorio.cadastra(c);

	}

	public void descadatrar(String nome) throws IOException,
			ProcuraPessoaInexistenteExeception {

		this.repositorio.remover(nome);

	}

	public Pessoa[] exibiClientes() throws IOException {

		return this.repositorio.imprimiClientes();
	}

	public Pessoa[] exibiFuncionarios() throws IOException {

		return this.repositorio.imprimiFuncionarios();
	}

	public Pessoa acharPessoa(String nome)
			throws ProcuraPessoaInexistenteExeception {

		Pessoa p = this.repositorio.procurar(nome);
		return p;
	}
	

	
	

}
