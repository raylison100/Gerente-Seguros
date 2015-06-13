package com.poo.negocios;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

import com.poo.dados.IRepositorio;
import com.poo.dados.RepositorioPessoa;
import com.poo.execoes.ProcuraPessoaInexistenteExeception;
import com.poo.execoes.SenhaIncorretaExeception;
import com.poo.negocios.beans.Funcionario;
import com.poo.negocios.beans.Pessoa;

public class ValidarSenha {
	
	private IRepositorio repositorio;
	
	public ValidarSenha(){
		try {
			this.repositorio = RepositorioPessoa.getInstance();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	
	
	public boolean checarSenhaF(String nome, char[] senha) throws ProcuraPessoaInexistenteExeception,SenhaIncorretaExeception{
		Pessoa p = this.repositorio.procurar(nome);
		boolean senhaConfirmada = false;
		
		if(p instanceof Funcionario){
			
			if(((Funcionario) p).getSenha().equals(senha))
				senhaConfirmada = true;
			else throw new SenhaIncorretaExeception();
			
			
		}else throw new ProcuraPessoaInexistenteExeception();
		
		
		return senhaConfirmada;
	}
	
	public boolean checarSenhaS(String nome, char[] senha) throws ProcuraPessoaInexistenteExeception,SenhaIncorretaExeception{
		String suporte = "admin";
		char[] senhaS = {'a','d','m','i','n'}; 
		boolean senhaConfirmada = false;
		
		if(suporte.equals(nome)){
			
			if(Arrays.equals(senhaS,senha))
				senhaConfirmada = true;
			else throw new SenhaIncorretaExeception();
			
			
		}else throw new ProcuraPessoaInexistenteExeception();
		
		
		return senhaConfirmada;
	}
	
}
