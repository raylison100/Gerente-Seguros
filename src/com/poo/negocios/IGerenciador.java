package com.poo.negocios;


import java.io.IOException;

import com.poo.execoes.CadatroPessoaExistenteExeception;
import com.poo.execoes.ProcuraPessoaInexistenteExeception;
import com.poo.negocios.beans.Pessoa;


public interface IGerenciador {

	public void cadatrarCliente(Pessoa c) throws IOException, CadatroPessoaExistenteExeception, ProcuraPessoaInexistenteExeception;	
	public void exibiCliente() throws IOException;
	public void exibiFuncionario() throws IOException;
	public void excluirPessoa(String nome) throws IOException, ProcuraPessoaInexistenteExeception;
	public Pessoa pesquisarPessoa(String nome) throws ProcuraPessoaInexistenteExeception;
		
}
