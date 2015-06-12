package com.poo.negocios;



import java.io.IOException;

import com.poo.dados.IRepositorio;
import com.poo.dados.RepositorioPessoa;
import com.poo.execoes.CadatroPessoaExistenteExeception;
import com.poo.execoes.ProcuraPessoaInexistenteExeception;
import com.poo.negocios.beans.Cliente;
import com.poo.negocios.beans.Pessoa;

public class CadastroPessoa {

	private IRepositorio repositorio;
	
	public CadastroPessoa() {
		
		try {
			this.repositorio= RepositorioPessoa.getInstance();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	public void cadatrar (Pessoa c) throws IOException, CadatroPessoaExistenteExeception, ProcuraPessoaInexistenteExeception{
				    this.repositorio.cadastra(c); 
		
	}
	
	
	
	public void descadatrar(String nome) throws IOException, ProcuraPessoaInexistenteExeception{
		
		
			this.repositorio.remover(nome);
	
		
	}

	public void exibiClientes() throws IOException{
		
		this.repositorio.imprimiClientes();
	}
	
    public void exibiFuncionarios() throws IOException{
		
		this.repositorio.imprimiFuncionarios();
	}
	
	
	public Cliente acharCliente(String nome) throws ProcuraPessoaInexistenteExeception{
		
		Cliente c = (Cliente) this.repositorio.procurar(nome);
		return c;
	}
	
}
