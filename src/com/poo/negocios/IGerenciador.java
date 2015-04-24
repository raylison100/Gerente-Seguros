package com.poo.negocios;


import java.io.IOException;

import com.poo.execoes.CadatroPessoaExistenteExeception;
import com.poo.execoes.ProcuraPessoaInexistenteExeception;
import com.poo.negocios.beans.Cliente;
import com.poo.negocios.beans.Colaboradores;


public interface IGerenciador {

	public void cadatrarCliente(Colaboradores c) throws IOException, CadatroPessoaExistenteExeception, ProcuraPessoaInexistenteExeception;
	
	public void cadatrarFuncionario(Colaboradores f)throws IOException, CadatroPessoaExistenteExeception, ProcuraPessoaInexistenteExeception;
	
	public void exibiCliente() throws IOException;
	
	public void exibirFuncionario() throws IOException;
	
	public void excluirCliente(String nome) throws IOException, ProcuraPessoaInexistenteExeception;
	
	public void excluirFuncionario(String nome)throws IOException, ProcuraPessoaInexistenteExeception;
	
	public Colaboradores pesquisarCliente(String nome) throws ProcuraPessoaInexistenteExeception;
	
	public Colaboradores pesquisarFuncionario(String nome) throws ProcuraPessoaInexistenteExeception;
	
}
