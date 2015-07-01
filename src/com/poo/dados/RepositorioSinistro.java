package com.poo.dados;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


import com.poo.execoes.CadatroSiniExistenteExeception;

import com.poo.execoes.ProcuraSiniInexistenteExeception;

import com.poo.negocios.beans.Sinistro;

public class RepositorioSinistro implements IRepositorioSinistro,Serializable{

	
	
	// atributos
	private Sinistro[] sini;
	private int proxima;

	private static RepositorioSinistro instance;

	// contrutor
	private RepositorioSinistro(int tamanho) {

		this.sini = new Sinistro[tamanho];
		this.proxima = 0;

	}

	// metodos

	public static IRepositorioSinistro getInstance() throws IOException {
		if (instance == null) {
			instance = abrirArquivo();
		}
		return  instance;
	}

	private static RepositorioSinistro abrirArquivo() throws IOException {

		RepositorioSinistro instanciaLocal = null;
		File in = new File("ARQUIVOS\\CADASTRO SINISTRO\\cadastrosini.bin");
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(in);
			ois = new ObjectInputStream(fis);
			Object o = ois.readObject();
			instanciaLocal = (RepositorioSinistro) o;
		} catch (Exception e) {
			
			instanciaLocal = new RepositorioSinistro(100);
			
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

		File dir = new File("ARQUIVOS\\CADASTRO SINISTRO");
		dir.mkdirs();
		File out = new File(dir,"cadastroauto.sini");
        
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



	private int procurarIndice(int num){
        Integer numero = num;
		int i = 0;
		boolean achou = false;

		while ((!achou) && (i < this.proxima)) {
			if (numero.equals(this.sini[i].getSinistroAtual())) {

				achou = true;

			} else {

				i = i + 1;
			}

		}
	
		return i;
	}

	private void duplicaArrayCliente() {

		if (this.sini != null && this.sini.length > 0) {
		    Sinistro[] arrayDuplicado = new Sinistro[this.sini.length * 2];
			for (int i = 0; i < this.sini.length; i++) {

				arrayDuplicado[i] = this.sini[i];

			}

			this.sini = arrayDuplicado;
		}
	}
	
	
	public void cadastra(Sinistro s) throws IOException,
			CadatroSiniExistenteExeception, ProcuraSiniInexistenteExeception {
		if (s != null)
			if (this.existe(s.getSinistroAtual())) {
				throw new CadatroSiniExistenteExeception();
				
			} else {
				this.sini[proxima] = (Sinistro) s;
				this.proxima = this.proxima + 1;
			}
		if (this.proxima == this.sini.length) {

			this.duplicaArrayCliente();
		}
		salvarArquivo();
		
	}

	
	public Sinistro procurar(int numeroDoSini)
			throws ProcuraSiniInexistenteExeception {
		int i = this.procurarIndice(numeroDoSini);
		Sinistro resultado = null;
		if (i != this.proxima)
			resultado = this.sini[i];
		else
			throw new ProcuraSiniInexistenteExeception();

		return resultado;
	}

	
	public boolean existe(int numeroDoSini)
			throws ProcuraSiniInexistenteExeception {
		boolean existe = false;
		int indice = this.procurarIndice(numeroDoSini);
		if (indice != proxima) {

			existe = true;

		}

		return existe;
	}

	
	public void remover(int numeroDoSini) throws IOException,
			ProcuraSiniInexistenteExeception {
		int i = this.procurarIndice(numeroDoSini);
		if (i != this.proxima) {
			this.sini[i] = this.sini[this.proxima - 1];
			this.sini[this.proxima - 1] = null;
			this.proxima = this.proxima - 1;
		} else
			throw new ProcuraSiniInexistenteExeception();

		salvarArquivo();
	}

	
	public Sinistro[] imprimiAuto() throws IOException {
	
		return sini;
	}
	
	
}
