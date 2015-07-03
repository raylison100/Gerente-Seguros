package com.poo.negocios;

import com.poo.execoes.CPFInvalidoExeception;

import java.io.IOException;

import com.poo.execoes.CPFCadastradoExeception;
import com.poo.execoes.CadatroAutoExistenteExeception;
import com.poo.execoes.CadatroContraExistenteExeception;
import com.poo.execoes.CadatroPessoaExistenteExeception;
import com.poo.execoes.CadatroSiniExistenteExeception;
import com.poo.execoes.ProcuraAutoInexistenteExeception;
import com.poo.execoes.ProcuraContraInexistenteExeception;
import com.poo.execoes.ProcuraPessoaInexistenteExeception;
import com.poo.execoes.ProcuraSiniInexistenteExeception;
import com.poo.execoes.SenhaIncorretaExeception;
import com.poo.negocios.beans.Automovel;
import com.poo.negocios.beans.Cliente;
import com.poo.negocios.beans.Contrato;
import com.poo.negocios.beans.Endereco;
import com.poo.negocios.beans.Funcionario;
import com.poo.negocios.beans.Pessoa;
import com.poo.negocios.beans.Sinistro;

public class GerenciadorFachada implements IGerenciador {

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

	public void cadatrarCliente(String nome, String cpf, String rg, String datEmissao,
			String orgaoEmissao, String sexo, String telefone,
			String estadoCivil,String cnh, String datPrimeiraHab,
			String vencHab, String profissao, String escolaridade,String logradouro, String bairro, String cep,
			String numero, String complemento, String cidade, String estado) throws IOException,
			CadatroPessoaExistenteExeception,
			CPFInvalidoExeception, CPFCadastradoExeception {
            
                Endereco endereco = new Endereco(logradouro, bairro, cep, numero, complemento, cidade, estado);
                Pessoa c =  new Cliente(nome, cpf, rg, datEmissao, orgaoEmissao, sexo, telefone, estadoCivil, endereco, cnh, datPrimeiraHab, vencHab, profissao, escolaridade);
                        
		this.pessoa.cadatrar(c);

	}

	public Pessoa[] exibiCliente() throws IOException {

		return this.pessoa.exibiClientes();
	}

	public Pessoa[] exibiFuncionario() throws IOException {

		return this.pessoa.exibiFuncionarios();
	}

	public void excluirPessoa(String nome) throws IOException,
			ProcuraPessoaInexistenteExeception {

		this.pessoa.descadatrar(nome);
	}

	public Pessoa pesquisarPessoa(String nome)
			throws ProcuraPessoaInexistenteExeception {

		Pessoa p = this.pessoa.acharPessoa(nome);
		return p;

	}
	


	public boolean validarSenhaF(String nome, char[] senha)
			throws ProcuraPessoaInexistenteExeception, SenhaIncorretaExeception {
		boolean confirmada = false;

		confirmada = this.vS.checarSenhaF(nome, senha);

		return confirmada;
	}

	public boolean validarSenhaS(String nome, char[] senha)
			throws ProcuraPessoaInexistenteExeception, SenhaIncorretaExeception {
		boolean confirmada = false;

		confirmada = this.vS.checarSenhaS(nome, senha);

		return confirmada;
	}

	public void cadatrarAuto(String marca, String modelo, String versao,String placa) throws IOException,
			CadatroAutoExistenteExeception, ProcuraAutoInexistenteExeception {

            Automovel a = new Automovel(marca, modelo,versao, placa);
		this.auto.cadatrar(a);

	}

	public Automovel[] exibiAuto() throws IOException {

		return this.auto.exibiAuto();
	}

	public void excluirAuto(String placa) throws IOException,
			ProcuraAutoInexistenteExeception {

		this.auto.descadatrar(placa);
	}

	public Automovel pesquisarAutomovel(String placa)
			throws ProcuraAutoInexistenteExeception {

		Automovel a = this.auto.acharAutomovel(placa);
		return a;

	}

	public void cadatrarSini(Sinistro s) throws IOException,
			CadatroSiniExistenteExeception, ProcuraSiniInexistenteExeception {

		this.sini.cadatrar(s);

	}

	public Sinistro[] exibiSini() throws IOException {

		return this.sini.exibiSinistros();
	}

	public void excluirSini(int numero) throws IOException,
			ProcuraSiniInexistenteExeception {

		this.sini.descadatrar(numero);
	}

	public Sinistro pesquisarSinistro(int numero)
			throws ProcuraSiniInexistenteExeception {

		Sinistro s = this.sini.acharSinistro(numero);
		return s;

	}

	public void cadatrarContra(Contrato c) throws IOException,
			CadatroContraExistenteExeception,
			ProcuraContraInexistenteExeception {

		this.contra.cadatrar(c);

	}

	public Contrato[] exibiContra() throws IOException {

		return this.contra.exibiContratos();
	}

	public void excluirContra(int numero) throws IOException,
			ProcuraContraInexistenteExeception {

		this.contra.descadatrar(numero);
	}

	public Contrato pesquisarContrato(int numero)
			throws ProcuraContraInexistenteExeception {

		Contrato c = this.contra.acharContrato(numero);
		return c;

	}
        
       public Automovel lastAutomovel(){
           
          return  auto.ultimoAuto();
       }

	

}
