package com.poo.dados;

import java.io.IOException;

import com.poo.execoes.CadatroPessoaExistenteExeception;
import com.poo.execoes.ProcuraPessoaInexistenteExeception;
import com.poo.negocios.beans.Colaboradores;


public interface IRepositorio  {

	
	
	public void cadastra(Colaboradores c)  throws IOException, CadatroPessoaExistenteExeception, ProcuraPessoaInexistenteExeception;
	public Colaboradores procurar(String nome) throws ProcuraPessoaInexistenteExeception;
	public boolean existe(String nome) throws ProcuraPessoaInexistenteExeception;
	public void remover (String nome)  throws IOException, ProcuraPessoaInexistenteExeception;
	public void imprimi() throws IOException;
	
}
