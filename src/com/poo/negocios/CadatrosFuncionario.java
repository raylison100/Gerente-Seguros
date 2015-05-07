package com.poo.negocios;

import java.io.IOException;

import com.poo.dados.IRepositorio;
import com.poo.dados.RepositorioFuncionario;
import com.poo.execoes.CadatroPessoaExistenteExeception;
import com.poo.execoes.ProcuraPessoaInexistenteExeception;
import com.poo.negocios.beans.Pessoa;

public class CadatrosFuncionario {

	private IRepositorio repositorio;

	public CadatrosFuncionario() {

		try {
			this.repositorio = RepositorioFuncionario.getInstance();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void cadatrar(Pessoa f) throws IOException, CadatroPessoaExistenteExeception, ProcuraPessoaInexistenteExeception {

		this.repositorio.cadastra(f);

	}

	public void descadatrar(String nome) throws IOException, ProcuraPessoaInexistenteExeception {

		this.repositorio.remover(nome);

	}

	public void exibi() throws IOException {

		repositorio.imprimi();
	}

	public Pessoa acharFuncionario(String nome) throws ProcuraPessoaInexistenteExeception {

		Pessoa c = this.repositorio.procurar(nome);
		return c;
	}

}
