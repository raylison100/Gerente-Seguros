package com.poo.negocios;


import java.io.IOException;

import com.poo.execoes.CadatroAutoExistenteExeception;
import com.poo.execoes.CadatroContraExistenteExeception;
import com.poo.execoes.CadatroPessoaExistenteExeception;
import com.poo.execoes.ProcuraAutoInexistenteExeception;
import com.poo.execoes.ProcuraPessoaInexistenteExeception;
import com.poo.execoes.SenhaIncorretaExeception;
import com.poo.negocios.beans.Automovel;
import com.poo.negocios.beans.Pessoa;


public class GerenciadorFachada implements IGerenciador{

	private CadastroPessoa pessoa = new CadastroPessoa();
	private CadastroAuto auto = new CadastroAuto();
	private CadastroContrato contra = new CadastroContrato();
	private CadastroSinistro sini = new CadastroSinistro();
	private ValidarSenha vS = new ValidarSenha();
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
	
	public boolean validarSenhaF(String nome, char[] senha) throws ProcuraPessoaInexistenteExeception, SenhaIncorretaExeception{
		boolean confirmada = false;
		
		confirmada = this.vS.checarSenhaF(nome, senha);
		
		return confirmada;
	}
	
	public boolean validarSenhaS(String nome, char[] senha) throws ProcuraPessoaInexistenteExeception, SenhaIncorretaExeception{
		boolean confirmada = false;
		
		confirmada = this.vS.checarSenhaS(nome, senha);
		
		return confirmada;
	}
	
	
public void cadatrarAuto(Automovel a) throws IOException, CadatroAutoExistenteExeception, ProcuraAutoInexistenteExeception{
		
		this.auto.cadatrar(a);
		
	}
		
	public void exibiAuto() throws IOException{
		
		this.auto.
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
		
}


