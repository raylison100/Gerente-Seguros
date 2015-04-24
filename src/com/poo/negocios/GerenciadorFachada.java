package com.poo.negocios;


import java.io.IOException;

import com.poo.execoes.CadatroPessoaExistenteExeception;
import com.poo.execoes.ProcuraPessoaInexistenteExeception;
import com.poo.negocios.beans.Cliente;
import com.poo.negocios.beans.Colaboradores;


public class GerenciadorFachada implements IGerenciador{

	private CadastroCliente cliente = new CadastroCliente();
	private CadatrosFuncionarios funcionarios =  new CadatrosFuncionarios();
	
	private static IGerenciador instance;
	
	private GerenciadorFachada() {
		
	}
	
	public static IGerenciador getInstance() {
        if (instance == null) {
            instance = new GerenciadorFachada();
        }
        return instance;
    }
	
	
	public void cadatrarCliente(Colaboradores c) throws IOException, CadatroPessoaExistenteExeception, ProcuraPessoaInexistenteExeception{
		
		this.cliente.cadatrar(c);
		
	}
	
	public void cadatrarFuncionario(Colaboradores f) throws IOException, CadatroPessoaExistenteExeception, ProcuraPessoaInexistenteExeception{
		
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
	
	
	public Colaboradores pesquisarCliente(String nome) throws ProcuraPessoaInexistenteExeception{
		
		Colaboradores c = this.cliente.acharCliente(nome);
		return c;
		
	}
	
	public Colaboradores pesquisarFuncionario(String nome) throws ProcuraPessoaInexistenteExeception{
		
		Colaboradores c = this.funcionarios.acharFuncionario(nome);
		return c;
	}
	
	
}


