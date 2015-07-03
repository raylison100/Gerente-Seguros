package com.poo.dados;

import com.poo.execoes.CPFCadastradoExeception;
import java.io.IOException;

import com.poo.execoes.CadatroPessoaExistenteExeception;
import com.poo.execoes.ProcuraPessoaInexistenteExeception;
import com.poo.negocios.beans.Cliente;
import com.poo.negocios.beans.Funcionario;
import com.poo.negocios.beans.Pessoa;


public interface IRepositorioPessoa  {

	
	
	public void cadastra(Pessoa c)  throws IOException, CadatroPessoaExistenteExeception, CPFCadastradoExeception;
	public Pessoa procurar(String nome) throws ProcuraPessoaInexistenteExeception;
	public boolean existe(String nome);
	public void remover (String nome)  throws IOException, ProcuraPessoaInexistenteExeception;
	public Pessoa[] imprimiClientes() throws IOException;
	public Pessoa[] imprimiFuncionarios() throws IOException;
	
}
