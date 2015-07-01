package com.poo.dados;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.poo.execoes.CadatroAutoExistenteExeception;
import com.poo.execoes.CadatroPessoaExistenteExeception;
import com.poo.execoes.ProcuraAutoInexistenteExeception;
import com.poo.execoes.ProcuraPessoaInexistenteExeception;
import com.poo.negocios.beans.Automovel;
import com.poo.negocios.beans.Cliente;
import com.poo.negocios.beans.Funcionario;

public class RepositorioAutomovel implements IRepositorioAutomovel,
		Serializable {

	// atributos
	private Automovel[] auto;
	private int proxima;

	private static RepositorioAutomovel instance;

	// contrutor
	private RepositorioAutomovel(int tamanho) {

		this.auto = new Automovel[tamanho];
		this.proxima = 0;

	}

	// metodos

	public static IRepositorioAutomovel getInstance() throws IOException {
		if (instance == null) {
			instance = abrirArquivo();
		}
		return instance;
	}

	private static RepositorioAutomovel abrirArquivo() throws IOException {

		RepositorioAutomovel instanciaLocal = null;
		File in = new File("ARQUIVOS\\CADASTRO AUTOMOVEL\\cadastroauto.bin");
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(in);
			ois = new ObjectInputStream(fis);
			Object o = ois.readObject();
			instanciaLocal = (RepositorioAutomovel) o;
		} catch (Exception e) {

			instanciaLocal = new RepositorioAutomovel(100);

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

		File dir = new File("ARQUIVOS\\CADASTRO AUTOMOVEL");
		dir.mkdirs();
		File out = new File(dir, "cadastroauto.bin");

		if (!out.exists()) {

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

	private int procurarIndice(String placa) {

		int i = 0;
		boolean achou = false;

		while ((!achou) && (i < this.proxima)) {
			if (placa.equals(this.auto[i].getPlaca())) {

				achou = true;

			} else {

				i = i + 1;
			}

		}

		return i;
	}

	private void duplicaArrayCliente() {

		if (this.auto != null && this.auto.length > 0) {
			Automovel[] arrayDuplicado = new Automovel[this.auto.length * 2];
			for (int i = 0; i < this.auto.length; i++) {

				arrayDuplicado[i] = this.auto[i];

			}

			this.auto = arrayDuplicado;
		}
	}

	public void cadastra(Automovel a) throws IOException,
			CadatroAutoExistenteExeception, ProcuraAutoInexistenteExeception {
		if (a != null)
			if (this.existe(a.getPlaca())) {
				throw new CadatroAutoExistenteExeception();

			} else {
				this.auto[proxima] = (Automovel) a;
				this.proxima = this.proxima + 1;
			}
		if (this.proxima == this.auto.length) {

			this.duplicaArrayCliente();
		}
		salvarArquivo();

	}

	public Automovel procurar(String placa)
			throws ProcuraAutoInexistenteExeception {
		int i = this.procurarIndice(placa);
		Automovel resultado = null;
		if (i != this.proxima)
			resultado = this.auto[i];
		else
			throw new ProcuraAutoInexistenteExeception();

		return resultado;
	}

	public boolean existe(String placa) throws ProcuraAutoInexistenteExeception {
		boolean existe = false;
		int indice = this.procurarIndice(placa);
		if (indice != proxima) {

			existe = true;

		}

		return existe;
	}

	public void remover(String placa) throws IOException,
			ProcuraAutoInexistenteExeception {
		int i = this.procurarIndice(placa);
		if (i != this.proxima) {
			this.auto[i] = this.auto[this.proxima - 1];
			this.auto[this.proxima - 1] = null;
			this.proxima = this.proxima - 1;
		} else
			throw new ProcuraAutoInexistenteExeception();

		salvarArquivo();
	}

	public Automovel[] imprimiAuto() throws IOException {

		return auto;
	}

}
