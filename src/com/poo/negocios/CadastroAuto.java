package com.poo.negocios;

import java.io.IOException;

import com.poo.dados.IRepositorioAutomovel;
import com.poo.dados.RepositorioAutomovel;
import com.poo.execoes.CadatroAutoExistenteExeception;
import com.poo.execoes.ProcuraAutoInexistenteExeception;
import com.poo.negocios.beans.Automovel;



public class CadastroAuto {

	private IRepositorioAutomovel repositorio;

	public CadastroAuto() {

		try {
			this.repositorio = RepositorioAutomovel.getInstance();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void cadatrar(Automovel a) throws IOException,
			CadatroAutoExistenteExeception,
			ProcuraAutoInexistenteExeception {
		this.repositorio.cadastra(a);

	}

	public void descadatrar(String placa) throws IOException,
			ProcuraAutoInexistenteExeception{

		this.repositorio.remover(placa);

	}

	public Automovel[] exibiAuto() throws IOException {

		return this.repositorio.imprimiAuto();
	}



	public Automovel acharAutomovel(String placa)
			throws ProcuraAutoInexistenteExeception {

		Automovel a = this.repositorio.procurar(placa);
		return a;
	}
}
