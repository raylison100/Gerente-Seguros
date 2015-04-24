package com.poo.negocios;

import java.io.IOException;

import com.poo.dados.IRepositorio;
import com.poo.dados.RepositorioFuncionarios;
import com.poo.execoes.CadatroPessoaExistenteExeception;
import com.poo.execoes.ProcuraPessoaInexistenteExeception;
import com.poo.negocios.beans.Colaboradores;

public class CadatrosFuncionarios {

	private IRepositorio repositorio;

	public CadatrosFuncionarios() {

		try {
			this.repositorio = RepositorioFuncionarios.getInstance();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void cadatrar(Colaboradores f) throws IOException, CadatroPessoaExistenteExeception, ProcuraPessoaInexistenteExeception {

		this.repositorio.cadastra(f);

	}

	public void descadatrar(String nome) throws IOException, ProcuraPessoaInexistenteExeception {

		this.repositorio.remover(nome);

	}

	public void exibi() throws IOException {

		repositorio.imprimi();
	}

	public Colaboradores acharFuncionario(String nome) throws ProcuraPessoaInexistenteExeception {

		Colaboradores c = this.repositorio.procurar(nome);
		return c;
	}

}
