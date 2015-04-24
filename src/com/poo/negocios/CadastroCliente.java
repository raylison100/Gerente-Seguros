package com.poo.negocios;



import java.io.IOException;

import com.poo.dados.IRepositorio;
import com.poo.dados.RepositorioClientes;
import com.poo.execoes.CadatroPessoaExistenteExeception;
import com.poo.execoes.ProcuraPessoaInexistenteExeception;
import com.poo.negocios.beans.Cliente;
import com.poo.negocios.beans.Colaboradores;

public class CadastroCliente {

	private IRepositorio repositorio;
	
	public CadastroCliente() {
		
		try {
			this.repositorio= RepositorioClientes.getInstance();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	public void cadatrar (Colaboradores c) throws IOException, CadatroPessoaExistenteExeception, ProcuraPessoaInexistenteExeception{
			
	    this.repositorio.cadastra(c); 
		
	}
	
	
	
	public void descadatrar(String nome) throws IOException, ProcuraPessoaInexistenteExeception{
		
		
			this.repositorio.remover(nome);
	
		
	}

	public void exibi() throws IOException{
		
		this.repositorio.imprimi();
	}
	
	public Cliente acharCliente(String nome) throws ProcuraPessoaInexistenteExeception{
		
		Cliente c = (Cliente) this.repositorio.procurar(nome);
		return c;
	}
	
}
