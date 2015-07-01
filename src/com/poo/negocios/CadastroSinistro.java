package com.poo.negocios;

import java.io.IOException;

import com.poo.dados.IRepositorioSinistro;
import com.poo.dados.RepositorioSinistro;
import com.poo.execoes.CadatroSiniExistenteExeception;
import com.poo.execoes.ProcuraSiniInexistenteExeception;
import com.poo.negocios.beans.Sinistro;



public class CadastroSinistro {
	private IRepositorioSinistro repositorio;

	public CadastroSinistro() {

		try {
			this.repositorio = RepositorioSinistro.getInstance();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void cadatrar(Sinistro s) throws IOException,
			CadatroSiniExistenteExeception,
			ProcuraSiniInexistenteExeception {
		this.repositorio.cadastra(s);

	}

	public void descadatrar(int num) throws IOException,
			ProcuraSiniInexistenteExeception{

		this.repositorio.remover(num);

	}

	public Sinistro[] exibiSinistros() throws IOException {

		return this.repositorio.imprimiAuto();
	}



	public Sinistro acharSinistro(int num)
			throws ProcuraSiniInexistenteExeception {

		Sinistro s = this.repositorio.procurar(num);
		return s;
	}

}

