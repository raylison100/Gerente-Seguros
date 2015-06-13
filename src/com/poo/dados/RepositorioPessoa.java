package com.poo.dados;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


import java.io.Serializable;
import com.poo.execoes.CadatroPessoaExistenteExeception;
import com.poo.execoes.ProcuraPessoaInexistenteExeception;
import com.poo.negocios.beans.Cliente;
import com.poo.negocios.beans.Funcionario;
import com.poo.negocios.beans.Pessoa;



public class RepositorioPessoa implements IRepositorio, Serializable{
	
	
	// atributos
	private Pessoa[] pessoa;
	private int proxima;

	private static RepositorioPessoa instance;

	// contrutor
	private RepositorioPessoa(int tamanho) {

		this.pessoa = new Pessoa[tamanho];
		this.proxima = 0;

	}

	// metodos

	public static IRepositorio getInstance() throws IOException {
		if (instance == null) {
			instance = abrirArquivo();
		}
		return instance;
	}

	private static RepositorioPessoa abrirArquivo() throws IOException {

		RepositorioPessoa instanciaLocal = null;
		File in = new File("ARQUIVOS\\CADASTRO CLIENTES\\cadastroclientes.bin");
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(in);
			ois = new ObjectInputStream(fis);
			Object o = ois.readObject();
			instanciaLocal = (RepositorioPessoa) o;
		} catch (Exception e) {
			
			instanciaLocal = new RepositorioPessoa(100);
			
		} finally {

			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {/* Silent exception */
				}
			}
		}

		return instanciaLocal;

	}

	public static void salvarArquivo() throws IOException {

		if (instance == null) {
			return;
		}

		File dir = new File("ARQUIVOS\\CADASTRO CLIENTES");
		dir.mkdirs();
		File out = new File(dir,"cadastroclientes.bin");
        
		if (!out.exists()){
			
			out.createNewFile();
        }
		
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;

		try {
			fos = new FileOutputStream(out);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(instance);

		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {/* Silent */
				}
			}
		}
	}

	public void cadastra(Pessoa c) throws IOException,
			CadatroPessoaExistenteExeception, ProcuraPessoaInexistenteExeception {

	    	if (c != null)
				if (this.existe(c.getNome())) {
					throw new CadatroPessoaExistenteExeception(
							c.getNome());
					
				} else {
					this.pessoa[proxima] = (Pessoa) c;
					this.proxima = this.proxima + 1;
				}
			if (this.proxima == this.pessoa.length) {

				this.duplicaArrayCliente();
			}
			salvarArquivo();
		
	}

	public Pessoa procurar(String nome)	throws ProcuraPessoaInexistenteExeception {

		int i = this.procurarIndice(nome);
		Pessoa resultado = null;
		if (i != this.proxima)
			resultado = this.pessoa[i];
		else throw new ProcuraPessoaInexistenteExeception(); 
		

		return resultado;

	}

	public boolean existe(String nome) throws ProcuraPessoaInexistenteExeception {

		boolean existe = false;
		int indice = this.procurarIndice(nome);
		if (indice != proxima) {

			existe = true;

		}

		return existe;
	}

	public void remover(String nome) throws IOException, ProcuraPessoaInexistenteExeception {

		int i = this.procurarIndice(nome);
		if (i != this.proxima) {
			this.pessoa[i] = this.pessoa[this.proxima - 1];
			this.pessoa[this.proxima - 1] = null;
			this.proxima = this.proxima - 1;
		}else throw new ProcuraPessoaInexistenteExeception(); 
		
		salvarArquivo();

	}

	private int procurarIndice(String nome){

		int i = 0;
		boolean achou = false;

		while ((!achou) && (i < this.proxima)) {
			if (nome.equals(this.pessoa[i].getNome())) {

				achou = true;

			} else {

				i = i + 1;
			}

		}
	
		return i;
	}

	private void duplicaArrayCliente() {

		if (this.pessoa != null && this.pessoa.length > 0) {
			Pessoa[] arrayDuplicado = new Pessoa[this.pessoa.length * 2];
			for (int i = 0; i < this.pessoa.length; i++) {

				arrayDuplicado[i] = this.pessoa[i];

			}

			this.pessoa = arrayDuplicado;
		}
	}

	public void imprimiClientes() {

		
		for (Pessoa c : pessoa) {

			if (c != null && (c instanceof Cliente) )
				System.out.println(c);
		}

	}
	
	public void imprimiFuncionarios(){
		 
		for (Pessoa f : pessoa) {
			if (f != null&& (f instanceof Funcionario))
				System.out.println(f);
		}
	}

}

