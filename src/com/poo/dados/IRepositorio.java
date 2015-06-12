package com.poo.dados;

import java.io.IOException;

import com.poo.execoes.CadatroPessoaExistenteExeception;
import com.poo.execoes.ProcuraPessoaInexistenteExeception;
import com.poo.negocios.beans.Pessoa;


public interface IRepositorio  {

	
	
	public void cadastra(Pessoa c)  throws IOException, CadatroPessoaExistenteExeception, ProcuraPessoaInexistenteExeception;
	public Pessoa procurar(String nome) throws ProcuraPessoaInexistenteExeception;
	public boolean existe(String nome) throws ProcuraPessoaInexistenteExeception;
	public void remover (String nome)  throws IOException, ProcuraPessoaInexistenteExeception;
	public void imprimiClientes() throws IOException;
	public void imprimiFuncionarios() throws IOException;
	
}
