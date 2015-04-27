package com.poo.dados;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


import java.io.Serializable;
import java.util.concurrent.ThreadPoolExecutor;

import com.poo.execoes.CadatroPessoaExistenteExeception;
import com.poo.execoes.ProcuraPessoaInexistenteExeception;
import com.poo.negocios.beans.Cliente;
import com.poo.negocios.beans.Colaboradores;
import com.poo.negocios.beans.Pessoa;

public class RepositorioClientes implements IRepositorio, Serializable{

	// atributos
	private Cliente[] clientes;
	private int proxima;

	private static RepositorioClientes instance;

	// contrutor
	private RepositorioClientes(int tamanho) {

		this.clientes = new Cliente[tamanho];
		this.proxima = 0;

	}

	// metodos

	public static IRepositorio getInstance() throws IOException {
		if (instance == null) {
			instance = abrirArquivo();
		}
		return instance;
	}

	private static RepositorioClientes abrirArquivo() throws IOException {

		RepositorioClientes instanciaLocal = null;
		File in = new File("ARQUIVOS\\CADASTRO CLIENTES\\cadastroclientes.bin");
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(in);
			ois = new ObjectInputStream(fis);
			Object o = ois.readObject();
			instanciaLocal = (RepositorioClientes) o;
		} catch (Exception e) {
			
			instanciaLocal = new RepositorioClientes(100);
			
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

	public void cadastra(Colaboradores c) throws IOException,
			CadatroPessoaExistenteExeception, ProcuraPessoaInexistenteExeception {

		if (c instanceof Cliente) {
			if (c != null)
				if (this.existe(c.getPessoa().getNome())) {
					throw new CadatroPessoaExistenteExeception(
							c.getPessoa().getNome());
					
				} else {
					this.clientes[proxima] = (Cliente) c;
					this.proxima = this.proxima + 1;
				}
			if (this.proxima == this.clientes.length) {

				this.duplicaArrayCliente();
			}
			salvarArquivo();
		}
	}

	public Cliente procurar(String nome)
			throws ProcuraPessoaInexistenteExeception {

		int i = this.procurarIndice(nome);
		Cliente resultado = null;
		if (i != this.proxima)
			resultado = this.clientes[i];

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
			this.clientes[i] = this.clientes[this.proxima - 1];
			this.clientes[this.proxima - 1] = null;
			this.proxima = this.proxima - 1;
		}
		salvarArquivo();

	}

	private int procurarIndice(String nome){

		int i = 0;
		boolean achou = false;

		while ((!achou) && (i < this.proxima)) {
			if (nome.equals(this.clientes[i].getPessoa().getNome())) {

				achou = true;

			} else {

				i = i + 1;
			}

		}
	
		if(i == this.proxima)
			return i;
		return i;
	}

	private void duplicaArrayCliente() {

		if (this.clientes != null && this.clientes.length > 0) {
			Cliente[] arrayDuplicado = new Cliente[this.clientes.length * 2];
			for (int i = 0; i < this.clientes.length; i++) {

				arrayDuplicado[i] = this.clientes[i];

			}

			this.clientes = arrayDuplicado;
		}
	}

	public void imprimi() {

		for (Cliente c : clientes) {

			if (c != null)
				System.out.println(c);
		}

	}

}

