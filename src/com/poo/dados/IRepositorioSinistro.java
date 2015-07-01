package com.poo.dados;

import java.io.IOException;

import com.poo.execoes.CadatroSiniExistenteExeception;
import com.poo.execoes.ProcuraSiniInexistenteExeception;
import com.poo.negocios.beans.Sinistro;



public interface IRepositorioSinistro {
	
	public void cadastra(Sinistro s)  throws IOException, CadatroSiniExistenteExeception, ProcuraSiniInexistenteExeception;
	public Sinistro procurar(int numeroDoSini) throws ProcuraSiniInexistenteExeception;
	public boolean existe(int numeroDoSini) throws ProcuraSiniInexistenteExeception;
	public void remover (int numeroDoSini)  throws IOException, ProcuraSiniInexistenteExeception;
	public Sinistro[] imprimiAuto() throws IOException;


}
