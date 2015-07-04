package com.poo.dados;

import java.io.IOException;

import com.poo.execoes.CadatroContraExistenteExeception;
import com.poo.execoes.ProcuraContraInexistenteExeception;
import com.poo.negocios.beans.Contrato;



public interface IRepositorioContrato {
	
	public void cadastra(Contrato c)  throws IOException, CadatroContraExistenteExeception, ProcuraContraInexistenteExeception;
	public Contrato procurar(int numeroDoContrato) throws ProcuraContraInexistenteExeception;
	public boolean existe(int numeroDoContrato) throws ProcuraContraInexistenteExeception;
	public void remover (int numeroDoContrao)  throws IOException, ProcuraContraInexistenteExeception;
	public Contrato[] imprimiAuto() throws IOException;
        public Contrato ultimoAuto();


}
