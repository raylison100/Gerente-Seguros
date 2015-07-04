package com.poo.negocios;

import java.io.IOException;


import com.poo.dados.IRepositorioContrato;

import com.poo.dados.RepositorioContrato;

import com.poo.execoes.CadatroContraExistenteExeception;

import com.poo.execoes.ProcuraContraInexistenteExeception;


import com.poo.negocios.beans.Contrato;

public class CadastroContrato {
	private IRepositorioContrato repositorio;

	public CadastroContrato() {

		try {
			this.repositorio = RepositorioContrato.getInstance();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void cadatrar(Contrato c) throws IOException,
			CadatroContraExistenteExeception,
			ProcuraContraInexistenteExeception {
		this.repositorio.cadastra(c);

	}

	public void descadatrar(int num) throws IOException,
			ProcuraContraInexistenteExeception{

		this.repositorio.remover(num);

	}

	public Contrato[] exibiContratos() throws IOException {

		return this.repositorio.imprimiAuto();
	}



	public Contrato acharContrato(int num)
			throws ProcuraContraInexistenteExeception{

		Contrato c = this.repositorio.procurar(num);
		return c;
	}
        
        public Contrato ultimoContra(){
            
           return this.repositorio.ultimoAuto();
        }

		

}
