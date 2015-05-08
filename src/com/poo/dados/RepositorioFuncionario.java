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
import com.poo.negocios.beans.Pessoa;
import com.poo.negocios.beans.Funcionario;

public class RepositorioFuncionario implements IRepositorio, Serializable{

	// atributos
	private Funcionario[] funcionario;
	private int proxima;

	private static RepositorioFuncionario instanceF;

	// contrutor
	private RepositorioFuncionario(int tamanho) {

		this.funcionario = new Funcionario[tamanho];
		this.proxima = 0;

	}

	// metodos

	public static IRepositorio getInstance() throws IOException {
		if (instanceF == null) {
			instanceF = abrirArquivo();
		}
		return instanceF;
	}

	private static RepositorioFuncionario abrirArquivo() throws IOException {

		RepositorioFuncionario instanciaLocal = null;
		File in = new File("ARQUIVOS\\CADASTRO FUNCIONARIOS\\cadastroFuncionarios.bin");
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(in);
			ois = new ObjectInputStream(fis);
			Object o = ois.readObject();
			instanciaLocal = (RepositorioFuncionario) o;
		} catch (Exception e) {
			
			instanciaLocal = new RepositorioFuncionario(100);
			
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

		if (instanceF == null) {
			return;
		}
		
		File dir = new File("ARQUIVOS\\CADASTRO FUNCIONARIOS");
		dir.mkdirs();
		File out = new File(dir,"cadastroFuncionaios.bin");
		
		if (!out.exists())
			out.createNewFile();
		
		
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;

		try {
			fos = new FileOutputStream(out);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(instanceF);
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

	public void cadastra(Pessoa f) throws IOException,
			CadatroPessoaExistenteExeception, ProcuraPessoaInexistenteExeception {

		if (f instanceof Funcionario)
			if (f != null) {
				if (this.existe(f.getNome())) {
					
					throw new CadatroPessoaExistenteExeception(
							f.getNome());
					
				} else {

					this.funcionario[proxima] = (Funcionario) f;
					this.proxima = this.proxima + 1;
				}
				if (this.proxima == this.funcionario.length) {

					this.duplicaArrayCliente();
				}
				salvarArquivo();
			}
	}

	public Funcionario procurar(String nome) throws ProcuraPessoaInexistenteExeception {

		int i = this.procurarIndice(nome);
		Funcionario resultado = null;
		if (i != this.proxima) 

			resultado = this.funcionario[i];


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

			this.funcionario[i] = this.funcionario[this.proxima - 1];
			this.funcionario[this.proxima - 1] = null;
			this.proxima = this.proxima - 1;

		}	
		
		salvarArquivo();

	}

	private int procurarIndice(String nome) throws ProcuraPessoaInexistenteExeception {

		int i = 0;
		boolean achou = false;

		while ((!achou) && (i < this.proxima)) {
			if (nome.equals(this.funcionario[i].getNome())) {

				achou = true;

			} else {

				i = i + 1;
			}

		}

		if(i == this.proxima)
			throw new ProcuraPessoaInexistenteExeception(nome);
		
		return i;
	}

	private void duplicaArrayCliente() {

		if (this.funcionario != null && this.funcionario.length > 0) {
			Funcionario[] arrayDuplicado = new Funcionario[this.funcionario.length * 2];
			for (int i = 0; i < this.funcionario.length; i++) {

				arrayDuplicado[i] = this.funcionario[i];

			}

			this.funcionario = arrayDuplicado;
		}
	}

	public void imprimi() {

		for (Funcionario f : funcionario) {

			if (f != null)
				System.out.println(f);
		}

	}

}