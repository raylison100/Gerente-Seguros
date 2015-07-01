package com.poo.dados;

import java.io.IOException;

import com.poo.execoes.CadatroAutoExistenteExeception;
import com.poo.execoes.ProcuraAutoInexistenteExeception;
import com.poo.negocios.beans.Automovel;


public interface IRepositorioAutomovel {
	
	public void cadastra(Automovel A)  throws IOException, CadatroAutoExistenteExeception, ProcuraAutoInexistenteExeception;
	public Automovel procurar(String placa) throws ProcuraAutoInexistenteExeception;
	public boolean existe(String placa) throws ProcuraAutoInexistenteExeception;
	public void remover (String placa)  throws IOException, ProcuraAutoInexistenteExeception;
	public Automovel[] imprimiAuto() throws IOException;


}
