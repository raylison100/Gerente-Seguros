package com.poo.negocios;


import java.io.IOException;

import com.poo.execoes.CadatroPessoaExistenteExeception;
import com.poo.execoes.ProcuraPessoaInexistenteExeception;
import com.poo.negocios.beans.Cliente;
import com.poo.negocios.beans.Pessoa;


public class GerenciadorFachada implements IGerenciador{

	private CadastroCliente cliente = new CadastroCliente();
	private CadatrosFuncionario funcionarios =  new CadatrosFuncionario();
	private static IGerenciador instance;
	
	
	private GerenciadorFachada() {
		
	}
	
	public static IGerenciador getInstance() {
        if (instance == null) {
            instance = new GerenciadorFachada();
        }
        return instance;
    }
	
	
	public void cadatrarCliente(Pessoa c) throws IOException, CadatroPessoaExistenteExeception, ProcuraPessoaInexistenteExeception{
		
		this.cliente.cadatrar(c);
		
	}
	
	public void cadatrarFuncionario(Pessoa f) throws IOException, CadatroPessoaExistenteExeception, ProcuraPessoaInexistenteExeception{
		
		this.funcionarios.cadatrar(f);
		
	}
	
	
	public void exibiCliente() throws IOException{
		
		this.cliente.exibi();
		
	}
	
	public void exibirFuncionario() throws IOException{
		
		this.funcionarios.exibi();
		
	}
	
	public void excluirCliente(String nome) throws IOException, ProcuraPessoaInexistenteExeception{
		
		this.cliente.descadatrar(nome);
	}
	
	public void excluirFuncionario(String nome) throws IOException, ProcuraPessoaInexistenteExeception{
		
		this.funcionarios.descadatrar(nome);
		
	}
	
	
	public Pessoa pesquisarCliente(String nome) throws ProcuraPessoaInexistenteExeception{
		
		Pessoa c = this.cliente.acharCliente(nome);
		return c;
		
	}
	
	public Pessoa pesquisarFuncionario(String nome) throws ProcuraPessoaInexistenteExeception{
		
		Pessoa c = this.funcionarios.acharFuncionario(nome);
		return c;
	}
	
	
}


