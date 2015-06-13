package com.poo.negocios;

import java.io.IOException;

import com.poo.dados.IRepositorio;
import com.poo.dados.RepositorioPessoa;
import com.poo.execoes.CadatroPessoaExistenteExeception;
import com.poo.execoes.ProcuraPessoaInexistenteExeception;
import com.poo.execoes.SenhaIncorretaExeception;
import com.poo.negocios.beans.Funcionario;
import com.poo.negocios.beans.Pessoa;

public class CadastroPessoa {

	private IRepositorio repositorio;

	public CadastroPessoa() {

		try {
			this.repositorio = RepositorioPessoa.getInstance();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void cadatrar(Pessoa c) throws IOException,
			CadatroPessoaExistenteExeception,
			ProcuraPessoaInexistenteExeception {
		this.repositorio.cadastra(c);

	}

	public void descadatrar(String nome) throws IOException,
			ProcuraPessoaInexistenteExeception {

		this.repositorio.remover(nome);

	}

	public void exibiClientes() throws IOException {

		this.repositorio.imprimiClientes();
	}

	public void exibiFuncionarios() throws IOException {

		this.repositorio.imprimiFuncionarios();
	}

	public Pessoa acharPessoa(String nome)
			throws ProcuraPessoaInexistenteExeception {

		Pessoa p = this.repositorio.procurar(nome);
		return p;
	}
	
	
	public boolean checarSenhaF(String nome, char[] senha) throws ProcuraPessoaInexistenteExeception,SenhaIncorretaExeception{
		Pessoa p = this.repositorio.procurar(nome);
		boolean senhaConfirmada = false;
		
		if(p instanceof Funcionario){
			
			if(((Funcionario) p).getSenha().equals(senha))
				senhaConfirmada = true;
			else throw new SenhaIncorretaExeception();
			
			
		}else throw new ProcuraPessoaInexistenteExeception();
		
		
		return senhaConfirmada;
	}

}
