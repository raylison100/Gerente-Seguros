package com.poo.negocios;


import java.io.IOException;

import com.poo.execoes.CadatroPessoaExistenteExeception;
import com.poo.execoes.ProcuraPessoaInexistenteExeception;
import com.poo.execoes.SenhaIncorretaExeception;
import com.poo.negocios.beans.Pessoa;


public class GerenciadorFachada implements IGerenciador{

	private CadastroPessoa pessoa = new CadastroPessoa();
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
		
		this.pessoa.cadatrar(c);
		
	}
		
	public void exibiCliente() throws IOException{
		
		this.pessoa.exibiClientes();
	}
	
    public void exibiFuncionario() throws IOException{
 		
		this.pessoa.exibiFuncionarios();
	}
		
	public void excluirPessoa(String nome) throws IOException, ProcuraPessoaInexistenteExeception{
		
		this.pessoa.descadatrar(nome);
	}
		
	public Pessoa pesquisarPessoa(String nome) throws ProcuraPessoaInexistenteExeception{
		
		Pessoa c = this.pessoa.acharPessoa(nome);
		return c;
		
	}
	
	public boolean validarSenha(String nome, char[] senha) throws ProcuraPessoaInexistenteExeception, SenhaIncorretaExeception{
		boolean confirmada = false;
		
		confirmada = this.pessoa.checarSenhaF(nome, senha);
		
		return confirmada;
	}
		
}


