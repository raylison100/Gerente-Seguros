package com.poo.dados;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.poo.execoes.CadatroContraExistenteExeception;
import com.poo.execoes.CadatroSiniExistenteExeception;
import com.poo.execoes.ProcuraContraInexistenteExeception;
import com.poo.execoes.ProcuraSiniInexistenteExeception;
import com.poo.negocios.beans.Contrato;
import com.poo.negocios.beans.Sinistro;


public class RepositorioContrato implements IRepositorioContrato, Serializable{
	

	
	// atributos
	private Contrato[] contra;
	private int proxima;

	private static RepositorioContrato instance;

	// contrutor
	private RepositorioContrato(int tamanho) {

		this.contra = new Contrato[tamanho];
		this.proxima = 0;

	}

	// metodos

	public static IRepositorioContrato getInstance() throws IOException {
		if (instance == null) {
			instance = abrirArquivo();
		}
		return  instance;
	}

	private static RepositorioContrato abrirArquivo() throws IOException {

		RepositorioContrato instanciaLocal = null;
		File in = new File("ARQUIVOS\\CADASTRO CONTRATO\\cadastrocontra.bin");
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(in);
			ois = new ObjectInputStream(fis);
			Object o = ois.readObject();
			instanciaLocal = (RepositorioContrato) o;
		} catch (Exception e) {
			
			instanciaLocal = new RepositorioContrato(100);
			
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

		File dir = new File("ARQUIVOS\\CADASTRO CONTRATO");
		dir.mkdirs();
		File out = new File(dir,"cadastroauto.contra");
        
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
			if (numero.equals(this.contra[i].getNumeroContrato())) {

				achou = true;

			} else {

				i = i + 1;
			}

		}
	
		return i;
	}

	private void duplicaArrayCliente() {

		if (this.contra != null && this.contra.length > 0) {
		    Contrato[] arrayDuplicado = new Contrato[this.contra.length * 2];
			for (int i = 0; i < this.contra.length; i++) {

				arrayDuplicado[i] = this.contra[i];

			}

			this.contra = arrayDuplicado;
		}
	}


	public void cadastra(Contrato c) throws IOException,
			CadatroContraExistenteExeception,
			ProcuraContraInexistenteExeception {
		
		if (c != null)
			if (this.existe(c.getNumeroContrato())) {
				throw new CadatroContraExistenteExeception();
				
			} else {
				this.contra[proxima] = (Contrato) c;
				this.proxima = this.proxima + 1;
			}
		if (this.proxima == this.contra.length) {

			this.duplicaArrayCliente();
		}
		salvarArquivo();
		
		
	}

	
	public Contrato procurar(int numeroDoContrato)
			throws ProcuraContraInexistenteExeception {
		
		int i = this.procurarIndice(numeroDoContrato);
		Contrato resultado = null;
		if (i != this.proxima)
			resultado = this.contra[i];
		else
			throw new ProcuraContraInexistenteExeception();

		return resultado;
		
	}

	
	public boolean existe(int numeroDoContrato)
			throws ProcuraContraInexistenteExeception {
		boolean existe = false;
		int indice = this.procurarIndice(numeroDoContrato);
		if (indice != proxima) {

			existe = true;

		}

		return existe;
		
	}


	public void remover(int numeroDoContrao) throws IOException,
			ProcuraContraInexistenteExeception {
		
		int i = this.procurarIndice(numeroDoContrao);
		if (i != this.proxima) {
			this.contra[i] = this.contra[this.proxima - 1];
			this.contra[this.proxima - 1] = null;
			this.proxima = this.proxima - 1;
		} else
			throw new ProcuraContraInexistenteExeception();

		salvarArquivo();
		
		
	}

	
	public Contrato[] imprimiAuto() throws IOException {
		
		return contra;
	}
	

}
