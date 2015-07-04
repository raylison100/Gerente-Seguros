package com.poo.negocios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Serializable;
import java.nio.CharBuffer;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.*;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.TokenParser;
import org.apache.http.util.EntityUtils;
import org.apache.commons.lang.StringEscapeUtils;

public class DadosFipe implements Serializable{
	private static String link = "http://fipeapi.appspot.com/api/1/";
	private int marcasCarros;
	private int marcasMotos;
	private int marcasCaminhoes;

	//CONSTRUTOR DA CLASSE
	public DadosFipe(){
		
	}
	//METODO CHAMA TODOS OS METODOS QUE BAIXAM DADOS
	//BAIXA TODOS OS DADOS NECESSARIOS PARA MANIPULAÇÃO DA TABELA FIPE
	public void atualizarTabelaFipe() throws IOException{
			this.baixarDadosCarros();
			this.baixarDadosMotos();
			this.baixarDadosCaminhoes();
			this.baixarDadosCarrosPorMarca();
			this.baixarDadosMotosPorMarca();
			this.baixarDadosCaminhoesPorMarca();
			
			String tipo1 = "carros";
			String tipo2 = "motos";
			String tipo3 = "caminhoes";
			
			String[] marcas = this.listarMarcas(tipo1);
			for(int i = 0; i < marcas.length; i++){
				String[] modelos = this.listarModelosPorMarcaETipo(tipo1, marcas[i]);
				for(int j = 0; j< modelos.length; j++){
					this.buscarModelos(tipo1, marcas[i]);
				}
			}
			String[] marcas2 = this.listarMarcas(tipo2);
			for(int i = 0; i < marcas2.length; i++){
				String[] modelos = this.listarModelosPorMarcaETipo(tipo2, marcas2[i]);
				for(int j = 0; j< modelos.length; j++){
					this.buscarModelos(tipo2, marcas2[i]);
				}
			}
			String[] marcas3 = this.listarMarcas(tipo3);
			for(int i = 0; i < marcas3.length; i++){
				String[] modelos = this.listarModelosPorMarcaETipo(tipo3, marcas3[i]);
				for(int j = 0; j< modelos.length; j++){
					this.buscarModelos(tipo3, marcas3[i]);
				}
			}
			this.baixaTodosAsVersoes();
		}

	//METODO PRIVADO CRIADO PARA SER UTILIZADO APENAS NESSA CLASSE E TIRAR OS ESPAÇOS DAS FRASES
	private String converterPalavra(String palavra){
		String padrao = "\\s", res = null;
		Pattern regPat = Pattern.compile(padrao);
		Matcher matcher = regPat.matcher(palavra);
		res = matcher.replaceAll("_").replace("\\", "_");
		return res;
	}
	private String converterPalavra1(String palavra){
		String res = palavra.replace("/", "");
		return res;
	}
	
	//METODOS PARA BAIXAR DADOS
	//BAIXA A LISTAGEM DE TODAS AS MARCAS EXISTENTES PARA O TIPO DE VEICULO CARRO
	public String baixarDadosCarros() throws IOException{
		String retorno = null, temp = null, aux = null;
		int contador = 0;
		File carros = new File("DADOS\\CARROS_MARCAS.txt");
		if(!(carros.exists())){
			FileWriter carrosWriter = new FileWriter(carros);
			PrintWriter gravarCarros = new PrintWriter(carrosWriter);
			try{
				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpGet httpPost = new HttpGet(link+"carros/marcas.json");
				HttpResponse response = httpClient.execute(httpPost);
				HttpEntity entity = response.getEntity();
				String body = EntityUtils.toString(entity);
				StringTokenizer st = new StringTokenizer(body, "}", true);
				while(st.hasMoreTokens()){
					temp = st.nextToken();
					if(temp.contains("id")){
						contador++;
						if(contador>=2){
							break;
						}
					}
				}
				Integer numero = contador;
				this.marcasCarros = contador;
				aux = Integer.toString(numero);
				carrosWriter.write("{\"contador\" = \""+aux+"\"}\n");
				StringTokenizer st1 = new StringTokenizer(body, "}", true);
				for(int i = 0 ; i <=2 ; i++){
					temp = StringEscapeUtils.unescapeJava(st1.nextToken());   				
					carrosWriter.write(temp+"\n");
				}
				retorno =  "Arquivo de carros baixados!!";
			}
			catch(Exception e){
				e.printStackTrace();
			}
			gravarCarros.close();
			carrosWriter.close();
		}else{
			return "Arquivo de carros já existe!!";
		}

		return retorno;
	}
	//BAIXA A LISTAGEM DE TODAS AS MARCAS EXISTENTES PARA O VEICULO DE TIPO MOTO
	public String baixarDadosMotos() throws IOException{
		String retorno = null, temp = null, aux = null;
		int contador = 0;
		File motos = new File("DADOS\\MOTOS_MARCAS.txt");
		if(!(motos.exists())){
			FileWriter motosWriter = new FileWriter(motos);
			PrintWriter gravarMotos = new PrintWriter(motosWriter);
			try{
				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpGet httpPost = new HttpGet(link+"motos/marcas.json");
				HttpResponse response = httpClient.execute(httpPost);
				HttpEntity entity = response.getEntity();
				String body = EntityUtils.toString(entity);
				StringTokenizer st = new StringTokenizer(body, "}", true);
				while(st.hasMoreTokens()){
					temp = st.nextToken();
					if(temp.contains("id")){
						contador++;
						if(contador>=2){
							break;
						}
					}
				}
				Integer numero = contador;
				this.marcasMotos = contador;
				aux = Integer.toString(numero);
				motosWriter.write("{\"contador\" = \""+aux+"\"}\n");
				StringTokenizer st1 = new StringTokenizer(body, "}", true);
//				while(st1.hasMoreTokens()){
				for(int i = 0; i <= 2 ; i++){
					temp = StringEscapeUtils.unescapeJava(st1.nextToken());   				
					motosWriter.write(temp+"\n");
				}
				retorno = "Arquivo de motos baixados com sucesso!!";
			}
			catch(Exception e){
				e.printStackTrace();
			}
			gravarMotos.close();
			motosWriter.close();
		}else{
			return "Arquivo de motos já existe!!";
		}
		return retorno;
	}
	//BAIXA A LISTAGEM DE TODAS AS MARCAS EXISTENTES PARA O VEICULO DO TIPO CAMINHAO
	public String baixarDadosCaminhoes() throws IOException{
		String retorno = null, temp = null, aux = null;
		int contador = 0;
		File caminhoes = new File("DADOS\\CAMINHOES_MARCAS.txt");
		if(!(caminhoes.exists())){
			FileWriter caminhoesWriter = new FileWriter(caminhoes);
			BufferedWriter gravarCaminhoes = new BufferedWriter(caminhoesWriter);
			try{
				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpGet httpPost = new HttpGet(link+"caminhoes/marcas.json");
				HttpResponse response = httpClient.execute(httpPost);
				HttpEntity entity = response.getEntity();
				String body = EntityUtils.toString(entity);
				StringTokenizer st = new StringTokenizer(body, "}", true);
				while(st.hasMoreTokens()){
					temp = st.nextToken();
					if(temp.contains("id")){
						contador++;
						if(contador>=2){
							break;
						}
					}
				}
				Integer numero = contador;
				this.marcasCaminhoes = contador;
				aux = Integer.toString(numero);
				gravarCaminhoes.write("{\"contador\" = \""+aux+"\"}\n");
				StringTokenizer st1 = new StringTokenizer(body, "}", true);
//				while(st1.hasMoreTokens()){
				for(int i = 0; i <= 2; i++){
					temp = StringEscapeUtils.unescapeJava(st1.nextToken());  				
					gravarCaminhoes.write(temp+"\n");
				}
				retorno = "Arquivo de caminhoes baixados com sucesso!!";
			}
			catch(Exception e){
				e.printStackTrace();
			}
			gravarCaminhoes.close();	
			caminhoesWriter.close();
		}else{
			return "Arquivo de caminhoes já existe!!";
		}

		return retorno;
	}
	//BAIXA TODOS OS VEICULOS DO TIPO CARRO SEPARADOS POR MARCA EM ARQUIVOS DIFERENTES
	public String baixarDadosCarrosPorMarca() throws IOException{
			String retorno = null, temp = null, aux = null, marca = null;
			String[] listaDeCarros = new String[this.quantidadeDeMarcas("carros")];
			String diretorio = "DADOS\\CARROS\\";
			File a = new File(diretorio);
			a.mkdirs();
			listaDeCarros = this.listarMarcas("CARROS");
			for(int i = 0; i < listaDeCarros.length; i++){
				marca = listaDeCarros[i];
				int contador = 0;
				int marcaId = this.buscaPorMarca("CARROS", marca);
				File carros = new File(diretorio+marca+".txt");
				if(!(carros.exists())){
					FileWriter carrosWriter = new FileWriter(carros);
					BufferedWriter gravarCarros = new BufferedWriter(carrosWriter);
					try{
						DefaultHttpClient httpClient = new DefaultHttpClient();
						HttpGet httpPost = new HttpGet(link+"carros/veiculos/"+marcaId+".json");
						HttpResponse response = httpClient.execute(httpPost);
						HttpEntity entity = response.getEntity();
						String body = EntityUtils.toString(entity);
						StringTokenizer st = new StringTokenizer(body, "}", true);
						while(st.hasMoreTokens()){
							temp = st.nextToken();
							if(temp.contains("id")){
								contador++;
								if(contador>=2){
									break;
								}
							}
						}
						Integer numero = contador;
						this.marcasCarros = contador;
						aux = Integer.toString(numero);
						gravarCarros.write("{\"contador\" = \""+aux+"\"}\n");
						StringTokenizer st1 = new StringTokenizer(body, "}", true);
//						while(st1.hasMoreTokens()){
						for(int k = 0; k <= 2; k++){
							temp = st1.nextToken();   				
							gravarCarros.write(StringEscapeUtils.unescapeJava(this.converterPalavra1(temp))+"\n");
						}
						retorno = "Arquivo de carros baixados com sucesso!!";
					}
					catch(Exception e){
						e.printStackTrace();
					}
					gravarCarros.close();	
					carrosWriter.close();
				}else{
					return "Arquivo de carros já existe!!";
				}
			}
			return retorno;

		}
	//BAIXA TODOS OS VEICULOS DO TIPO MOTO SEPARADOS POR MARCA EM ARQUIVOS DIFERENTES
	public String baixarDadosMotosPorMarca() throws IOException{
			String retorno = null, temp = null, aux = null, marca = null;
			String[] listaDeMotos = new String[this.quantidadeDeMarcas("MOTOS")];
			listaDeMotos = this.listarMarcas("MOTOS");
			String diretorio = "DADOS\\MOTOS\\";
			File a = new File(diretorio);
			a.mkdirs();
			for(int i = 0; i < listaDeMotos.length; i++){
				marca = listaDeMotos[i];
				int contador = 0;
				int marcaId = this.buscaPorMarca("MOTOS", marca);
				File motos = new File(diretorio+marca+".txt");
				if(!(motos.exists())){
					FileWriter motosWriter = new FileWriter(motos);
					BufferedWriter gravarMotos = new BufferedWriter(motosWriter);
					try{
						DefaultHttpClient httpClient = new DefaultHttpClient();
						HttpGet httpPost = new HttpGet(link+"motos/veiculos/"+marcaId+".json");
						HttpResponse response = httpClient.execute(httpPost);
						HttpEntity entity = response.getEntity();
						String body = EntityUtils.toString(entity);
						StringTokenizer st = new StringTokenizer(body, "}", true);
						while(st.hasMoreTokens()){
							temp = st.nextToken();
							if(temp.contains("id")){
								contador++;
								if(contador>=2){
									break;
								}
							}
						}
						Integer numero = contador;
						this.marcasMotos = contador;
						aux = Integer.toString(numero);
						gravarMotos.write("{\"contador\" = \""+aux+"\"}\n");
						StringTokenizer st1 = new StringTokenizer(body, "}", true);
//						while(st1.hasMoreTokens()){
						for(int k = 0; k<=2;k++){
							temp = st1.nextToken();   				
							gravarMotos.write(StringEscapeUtils.unescapeJava(this.converterPalavra1(temp))+"\n");
						}
						retorno = "Arquivo de motos baixados com sucesso!!";
					}
					catch(Exception e){
						e.printStackTrace();
					}
					gravarMotos.close();	
					motosWriter.close();
				}else{
					return "Arquivo de motos já existe!!";
				}
			}
			return retorno;

		}
	//BAIXA TODOS OS VEICULOS DO TIPO CAMINHOES SEPARADOS POR MARCA EM ARQUIVOS DIFERENTES
	public String baixarDadosCaminhoesPorMarca() throws IOException{
			String retorno = null, temp = null, aux = null, marca = null;
			String[] listaDeCaminhoes = new String[this.quantidadeDeMarcas("CAMINHOES")];
			listaDeCaminhoes = this.listarMarcas("CAMINHOES");
			String diretorio = "DADOS\\CAMINHOES\\";
			File a = new File(diretorio);
			a.mkdirs();
			for(int i = 0; i < listaDeCaminhoes.length; i++){
			marca = listaDeCaminhoes[i];
			int contador = 0;
			int marcaId = this.buscaPorMarca("CAMINHOES", marca);
			File caminhoes = new File(diretorio+marca+".txt");
			if(!(caminhoes.exists())){
				FileWriter caminhoesWriter = new FileWriter(caminhoes);
				BufferedWriter gravarCaminhoes = new BufferedWriter(caminhoesWriter);
				try{
					DefaultHttpClient httpClient = new DefaultHttpClient();
					HttpGet httpPost = new HttpGet(link+"caminhoes/veiculos/"+marcaId+".json");
					HttpResponse response = httpClient.execute(httpPost);
					HttpEntity entity = response.getEntity();
					String body = EntityUtils.toString(entity);
					StringTokenizer st = new StringTokenizer(body, "}", true);
					while(st.hasMoreTokens()){
						temp = st.nextToken();
						if(temp.contains("id")){
							contador++;
							if(contador>=2){
								break;
							}
						}
					}
					Integer numero = contador;
					this.marcasCaminhoes = contador;
					aux = Integer.toString(numero);
					gravarCaminhoes.write("{\"contador\" = \""+aux+"\"}\n");
					StringTokenizer st1 = new StringTokenizer(body, "}", true);
//					while(st1.hasMoreTokens()){
					for(int k = 0; k<=2; k++){
						temp = st1.nextToken();   				
						gravarCaminhoes.write(StringEscapeUtils.unescapeJava(this.converterPalavra1(temp))+"\n");
					}
					retorno = "Arquivo de caminhoes baixados com sucesso!!";
				}
				catch(Exception e){
					e.printStackTrace();
				}
				gravarCaminhoes.close();	
				caminhoesWriter.close();
			}else{
				return "Arquivo de caminhoes já existe!!";
			}
			}
			return retorno;

		}
	//BAIXA OS ARQUIVOS DE TODAS AS VERSOES
	public void baixaTodosAsVersoes() throws IOException{
		String tipo1 = "carros";
		String tipo2 = "motos";
		String tipo3 = "caminhoes";
		String[] listaMarcas_tipo1 = new String[this.quantidadeDeMarcas(tipo1)];
		String[] listaMarcas_tipo2 = new String[this.quantidadeDeMarcas(tipo2)];
		String[] listaMarcas_tipo3 = new String[this.quantidadeDeMarcas(tipo3)];
		listaMarcas_tipo1 = this.listarMarcas(tipo1);
		listaMarcas_tipo2 = this.listarMarcas(tipo2);
		listaMarcas_tipo3 = this.listarMarcas(tipo3);
		for(int i = 0; i < listaMarcas_tipo1.length; i++){
			String[] modelos = new String[this.quantidadeDeModelos(tipo1, listaMarcas_tipo1[i])];
			modelos = this.listarModelosPorMarcaETipo(tipo1, listaMarcas_tipo1[i]);
			for(int j = 0; j < modelos.length; j++){
				String[] versoes = new String[this.totalDeVersoes(tipo1, listaMarcas_tipo1[i], modelos[j])];
				versoes = this.listarVersoes(tipo1, listaMarcas_tipo1[i], modelos[i]);
				for(int k = 0; k < versoes.length; k++){
					String diretorio = "DADOS\\"+tipo1+"\\"+listaMarcas_tipo1[i]+"\\"+this.converterPalavra(modelos[j])+"\\";
					File arq = new File(diretorio);
					arq.mkdirs();
					File arqVersao = new File(diretorio+versoes[k]+".txt");
					String temp;
					if(!(arqVersao.exists())){
						FileWriter versaoWriter = new FileWriter(arqVersao);
						PrintWriter gravarVersao = new PrintWriter(versaoWriter);
						String linkFinal = link+tipo1+"/veiculo/"+this.buscaPorMarca(tipo1, listaMarcas_tipo1[i]).toString()+"/"+this.buscarIDModelo(tipo1, listaMarcas_tipo1[i], modelos[j])+"/"+this.buscarIDVersao(tipo1, listaMarcas_tipo1[i], modelos[j], versoes[k])+".json";
						try{
							DefaultHttpClient httpClient = new DefaultHttpClient();
							HttpGet httpPost = new HttpGet(linkFinal);
							HttpResponse response = httpClient.execute(httpPost);
							HttpEntity entity = response.getEntity();
							String body = EntityUtils.toString(entity);
							versaoWriter.write(linkFinal+"\n");
							versaoWriter.write(body);
						}catch(Exception e){
							e.printStackTrace();
						}
						versaoWriter.close();
						gravarVersao.close();
					}
				}
			}
		}
		
		for(int i = 0; i < listaMarcas_tipo2.length; i++){
			String[] modelos = new String[this.quantidadeDeModelos(tipo2, listaMarcas_tipo2[i])];
			modelos = this.listarModelosPorMarcaETipo(tipo2, listaMarcas_tipo2[i]);
			for(int j = 0; j < modelos.length; j++){
				String[] versoes = new String[this.totalDeVersoes(tipo2, listaMarcas_tipo2[i], modelos[j])];
				versoes = this.listarVersoes(tipo2, listaMarcas_tipo2[i], modelos[i]);
				for(int k = 0; k < versoes.length; k++){
					String diretorio = "DADOS\\"+tipo2+"\\"+listaMarcas_tipo2[i]+"\\"+this.converterPalavra(modelos[j])+"\\";
					File arq = new File(diretorio);
					arq.mkdirs();
					File arqVersao = new File(diretorio+versoes[k]+".txt");
					String temp;
					if(!(arqVersao.exists())){
						FileWriter versaoWriter = new FileWriter(arqVersao);
						PrintWriter gravarVersao = new PrintWriter(versaoWriter);
						String linkFinal = link+tipo2+"/veiculo/"+this.buscaPorMarca(tipo2, listaMarcas_tipo2[i]).toString()+"/"+this.buscarIDModelo(tipo2, listaMarcas_tipo2[i], modelos[j])+"/"+this.buscarIDVersao(tipo2, listaMarcas_tipo2[i], modelos[j], versoes[k])+".json";
						try{
							DefaultHttpClient httpClient = new DefaultHttpClient();
							HttpGet httpPost = new HttpGet(linkFinal);
							HttpResponse response = httpClient.execute(httpPost);
							HttpEntity entity = response.getEntity();
							String body = EntityUtils.toString(entity);
							versaoWriter.write(linkFinal+"\n");
							versaoWriter.write(body);
						}catch(Exception e){
							e.printStackTrace();
						}
						versaoWriter.close();
						gravarVersao.close();
					}
				}
			}
		}
		
		for(int i = 0; i < listaMarcas_tipo3.length; i++){
			String[] modelos = new String[this.quantidadeDeModelos(tipo3, listaMarcas_tipo3[i])];
			modelos = this.listarModelosPorMarcaETipo(tipo3, listaMarcas_tipo3[i]);
			for(int j = 0; j < modelos.length; j++){
				String[] versoes = new String[this.totalDeVersoes(tipo3, listaMarcas_tipo3[i], modelos[j])];
				versoes = this.listarVersoes(tipo3, listaMarcas_tipo3[i], modelos[i]);
				for(int k = 0; k < versoes.length; k++){
					String diretorio = "DADOS\\"+tipo3+"\\"+listaMarcas_tipo3[i]+"\\"+this.converterPalavra(modelos[j])+"\\";
					File arq = new File(diretorio);
					arq.mkdirs();
					File arqVersao = new File(diretorio+versoes[k]+".txt");
					String temp;
					if(!(arqVersao.exists())){
						FileWriter versaoWriter = new FileWriter(arqVersao);
						PrintWriter gravarVersao = new PrintWriter(versaoWriter);
						String linkFinal = link+tipo3+"/veiculo/"+this.buscaPorMarca(tipo3, listaMarcas_tipo3[i]).toString()+"/"+this.buscarIDModelo(tipo3, listaMarcas_tipo3[i], modelos[j])+"/"+this.buscarIDVersao(tipo3, listaMarcas_tipo3[i], modelos[j], versoes[k])+".json";
						try{
							DefaultHttpClient httpClient = new DefaultHttpClient();
							HttpGet httpPost = new HttpGet(linkFinal);
							HttpResponse response = httpClient.execute(httpPost);
							HttpEntity entity = response.getEntity();
							String body = EntityUtils.toString(entity);
							versaoWriter.write(linkFinal+"\n");
							versaoWriter.write(body);
						}catch(Exception e){
							e.printStackTrace();
						}
						versaoWriter.close();
						gravarVersao.close();
					}
				}
			}
		}
	}
	//BAIXA TODOS OS MODELOS DA MARCA SELECIONADA
	public String buscarModelos(String tipo, String marca) throws IOException{
			tipo = tipo.toUpperCase();
			marca = marca.toUpperCase();
			String retorno = null;
			Integer marcaSelecionada = 0;
			String temp = null;
			String[] listaModelos = listarModelosPorMarcaETipo(tipo, marca);
			for(int i = 0; i < listaModelos.length; i++){
				int contador = 0;
				String aux = null;
				File arq = new File("DADOS\\"+tipo+"\\"+marca+".txt");
				arq.mkdir();
				try{
					FileReader marcas = new FileReader(arq);
					BufferedReader marcasLer = new BufferedReader(marcas);
					String linha;
					while(marcasLer.ready()){
						linha = marcasLer.readLine();
						if(linha.contains(listaModelos[i])){
							StringTokenizer st = new StringTokenizer(linha, "\": ");
							while(st.hasMoreTokens()){
								temp = st.nextToken();
								if(temp.contains("id")){
									aux = st.nextToken();
								}
							}
						}
					}
					marcasLer.close();
					marcas.close();
				}catch(Exception o){
					o.printStackTrace();
				}

				File modelo = new File("DADOS\\"+tipo+"\\"+marca+"\\");
				modelo.mkdir();
				File modelos = new File("DADOS\\"+tipo+"\\"+marca+"\\"+StringEscapeUtils.unescapeJava(listaModelos[i])+".txt");
				tipo = tipo.toLowerCase();
				if(!(modelos.exists())){

					FileWriter modelosWriter = new FileWriter(modelos);
					BufferedWriter gravarModelos = new BufferedWriter(modelosWriter);
					try{
						DefaultHttpClient httpClient = new DefaultHttpClient();
						HttpGet httpPost = new HttpGet(link+tipo+"/veiculo/"+this.buscaPorMarca(tipo, marca)+"/"+aux+".json");
						HttpResponse response = httpClient.execute(httpPost);
						HttpEntity entity = response.getEntity();
						String body = EntityUtils.toString(entity);
						StringTokenizer st1 = new StringTokenizer(body, "}", true);
//						while(st1.hasMoreTokens()){
						for(int k = 0; k<=3;k++){
							temp = st1.nextToken();   				
							gravarModelos.write(this.converterPalavra1(temp)+"\n");
						}
						StringTokenizer st = new StringTokenizer(body, "}", true);
						while(st.hasMoreTokens()){
							temp = st.nextToken();
							if(temp.contains("id")){
								contador++;
								if(contador>=3){
									break;
								}
							}
						}
						Integer numero = contador;
						aux = Integer.toString(numero);
						modelosWriter.write("{\"contador\" = \""+aux+"\"}\n");
						retorno = "Arquivo de caminhoes baixados com sucesso!!";
					}
					catch(Exception e){
						e.printStackTrace();
					}
					gravarModelos.close();	
					modelosWriter.close();
				}
			}
			return retorno;      
		}

	//METODOS PARA LISTAGEM DE DADOS
	//LISTA TODAS AS MARCAS DE UM DETERMINADO TIPO DE VEICULO
	public String[] listarMarcas(String tipo) throws FileNotFoundException{
		int tamanho = this.quantidadeDeMarcas(tipo);
		String[] listaMarcas = new String[tamanho];
		String tipo1 = tipo.toUpperCase();
		String aux = null;
		int posicao = 0;
		File arq = new File("DADOS\\"+tipo1+"_MARCAS.txt");
		try{
			FileReader marcas = new FileReader(arq);
			BufferedReader marcasLer = new BufferedReader(marcas);
			String linha;
			while(marcasLer.ready()){
				linha = marcasLer.readLine();
				if(linha.contains("name")){
					String temp = null;
					StringTokenizer st = new StringTokenizer(linha, "\",:");
					while(st.hasMoreTokens()){
						st.nextToken();
						st.nextToken();
						st.nextToken();
						temp = st.nextToken();
						if(posicao <= tamanho){
							listaMarcas[posicao] = StringEscapeUtils.unescapeJava(temp);
							posicao++;
							break;
						}
					}
				}
			}
			marcasLer.close();
			marcas.close();
		}catch(Exception o){
			o.printStackTrace();
		}
		return listaMarcas;        
	}
	//RETORNA UM ARRAY DE STRING COM TODOS OS MODELOS DE UMA DETERMINADA MARCA
	public String[] listarModelosPorMarcaETipo(String tipo, String marca){
				tipo = tipo.toUpperCase();
				marca = marca.toUpperCase();
				String diretorio = "DADOS\\"+tipo+"\\";
				String diretorioAux = diretorio+marca+"\\";
				int tamanho = this.quantidadeDeModelos(tipo, marca);
				String[] listaModelos = new String[tamanho];
				String aux = null;
				int posicao = 0;
				File arq = new File(diretorio+marca+".txt");
				
				try{
					FileReader modelos = new FileReader(arq);
					BufferedReader modelosLer = new BufferedReader(modelos);
					String linha;
					while(modelosLer.ready()){
						linha = modelosLer.readLine();
						if(linha.contains("name")){
							String temp = null;
							StringTokenizer st = new StringTokenizer(linha, "\",:");
							while(st.hasMoreTokens()){
								temp =st.nextToken();
								if(temp.equals("name")){
									st.nextToken();
									temp = st.nextToken();
									if(posicao <= tamanho){
										if(temp.contains("/")){
											StringTokenizer modeloSt = new StringTokenizer(temp, "/");
											while(modeloSt.hasMoreTokens()){
												int i = modeloSt.countTokens();
												for(int j = 1; j <= i; j++){
													temp = modeloSt.nextToken();
													if(j == i){
														listaModelos[posicao] = StringEscapeUtils.unescapeJava(temp);;
														posicao++;
														break;
													}
													
												}
											}
										}else{
											listaModelos[posicao] = temp;
											posicao++;
											break;
										}
									}
								}
							}
						}
					}
					modelosLer.close();
					modelos.close();
				}catch(Exception o){
					o.printStackTrace();
				}
				return listaModelos;        
			}
	//LISTA TODAS AS VERSOES DE UM MODELO ESPECIFICO
	public String[] listarVersoes(String tipo, String marca, String modelo){
			int tamanho = this.totalDeVersoes(tipo, marca, modelo);
			String[] listaVersoes = new String[tamanho];
			tipo = tipo.toUpperCase();
			marca = marca.toUpperCase();
			String aux = null;
			int posicao = 0;
			String diretorio = "DADOS\\"+tipo+"\\"+marca+"\\"+modelo+".txt";
			File arq = new File(diretorio);
			try{
				FileReader marcas = new FileReader(arq);
				BufferedReader marcasLer = new BufferedReader(marcas);
				String linha;
				while(marcasLer.ready()){
					linha = marcasLer.readLine();
					if(linha.contains("name")){
						String temp = null;
						StringTokenizer st = new StringTokenizer(linha, "\",: ");
						while(st.hasMoreTokens()){
							temp = st.nextToken();
							if(temp.equals("name")){
								temp = st.nextToken();
								temp+= " "+st.nextToken();
								if(posicao <= tamanho){
									listaVersoes[posicao] = StringEscapeUtils.unescapeJava(temp);
									posicao++;
									break;
								}
							}		
						}
					}
				}
				marcasLer.close();
				marcas.close();
			}catch(Exception o){
				o.printStackTrace();
			}
			
			
			
			
			
			return listaVersoes;
		}	
	
	//METODOS PARA CONTAGEM DE DADOS
	//QUANTIDADES DE MARCAS EXISTENTES EM UM DETERMINADO TIPO DE VEICULO
	public Integer quantidadeDeMarcas(String tipo){
		Integer resultado = null;
		String tipo1 = tipo.toUpperCase();
		String aux = null;
		File arq = new File("DADOS\\"+tipo1+"_MARCAS.txt");
		try{
			FileReader marcas = new FileReader(arq);
			BufferedReader marcasLer = new BufferedReader(marcas);
			String linha;
			while(marcasLer.ready()){
				linha = marcasLer.readLine();
				if(linha.contains("contador")){
					String temp = null;
					StringTokenizer st = new StringTokenizer(linha, "\"");
					st.nextToken();
					st.nextToken();
					st.nextToken();
					aux = st.nextToken();
					
				}
				break;
			}
			marcasLer.close();
			marcas.close();
		}catch(Exception o){
			o.printStackTrace();
		}
		resultado = Integer.parseInt(aux);
		
		return resultado;
	}
	//RETORNA TOTAL DE MODELOS DE UMA MARCA (RECEBE COMO ENTRADA UM TIPO E UMA MARCA
	public Integer totalDeModelos(String tipo, String marca) throws IOException{
			Integer contador = 0;
			String aux = null;
			File arq = new File("DADOS\\"+tipo+"\\"+marca+".txt");
			try{
				FileReader versoes = new FileReader(arq);
				BufferedReader versoesLer = new BufferedReader(versoes);
				String linha;
				while(versoesLer.ready()){
					linha = versoesLer.readLine();
					if(linha.contains("contador")){
						String temp = null;
						StringTokenizer st = new StringTokenizer(linha, "\"");
						st.nextToken();
						st.nextToken();
						st.nextToken();
						aux = st.nextToken();
						}
					break;
				}
				versoesLer.close();
				versoes.close();
			}catch(Exception o){
				o.printStackTrace();
			}
			contador = Integer.parseInt(aux);
			
			return contador;
		}
	//CONTA QUANTOS MODELOS EXISTEM DE DETERMINADA MARCA
	public Integer quantidadeDeModelos(String tipo, String marca){
			Integer resultado = null;
			marca = marca.toUpperCase();
			tipo = tipo.toUpperCase();
			String aux = null;
			File arq = new File("DADOS\\"+tipo+"\\"+marca+".txt");
			try{
				FileReader modelos = new FileReader(arq);
				BufferedReader modelosLer = new BufferedReader(modelos);
				String linha;
				while(modelosLer.ready()){
					linha = modelosLer.readLine();
					if(linha.contains("contador")){
						String temp = null;
						StringTokenizer st = new StringTokenizer(linha, "\"");
						st.nextToken();
						st.nextToken();
						st.nextToken();
						aux = st.nextToken();
						}
					break;
				}
				modelosLer.close();
				modelos.close();
			}catch(Exception o){
				o.printStackTrace();
			}
			resultado = Integer.parseInt(aux);
			return resultado;
		}
	//RETORNA TOTAL DE MODELOS DE UM TIPO
	public Integer totalDeModelos(String tipo) throws IOException{
			Integer total = 0;
			String[] marcas = this.listarMarcas(tipo);
			for(int i = 0; i < marcas.length; i++){
				String[] modelos = this.listarModelosPorMarcaETipo(tipo, marcas[i]);
				total+=modelos.length;
				for(int j = 0; j< modelos.length; j++){
					this.buscarModelos(tipo, marcas[i]);
				}
			}
			
			return total;
		}
		
	//METODOS AUXILIARES PARA MONTAGEM DOS LINKS
	//RETORNA O ID DA MARCA, PESQUISADO A PARTIR DO TIPO(CARRO, MOTO OU CAMINHAO)
	public Integer buscaPorMarca(String tipo, String marca) throws FileNotFoundException{
		String tipo1 = tipo.toUpperCase();
		String marca1 = marca.toUpperCase();
		Integer marcaSelecionada = 0;
		String aux = null;
		File arq = new File("DADOS\\"+tipo1+"_MARCAS.txt");
		try{
			FileReader marcas = new FileReader(arq);
			BufferedReader marcasLer = new BufferedReader(marcas);
			String linha;
			while(marcasLer.ready()){
				linha = marcasLer.readLine();
				if(linha.contains(marca1)){
					String temp = null;
					StringTokenizer st = new StringTokenizer(linha, "\": ");
					while(st.hasMoreTokens()){
						temp = st.nextToken();
						if(temp.contains("id")){
							aux = st.nextToken();
						}
					}
				}
			}
			marcasLer.close();
			marcas.close();
		}catch(Exception o){
			o.printStackTrace();
		}
		marcaSelecionada = Integer.parseInt(aux);
		return marcaSelecionada;        
	}
	//RETORNA O ID DE UM MODELO ---- FUNCIONANDO
	public String buscarIDModelo(String tipo, String marca, String modelo){
			tipo = tipo.toUpperCase();
			marca = marca.toUpperCase();
			String iDmodelo = null;
			String temp = null, aux = null;
			File arq = new File("DADOS\\"+tipo+"\\"+marca+".txt");
			try{
				FileReader modelo1 = new FileReader(arq);
				BufferedReader modeloLer = new BufferedReader(modelo1);
				String linha;
				while(modeloLer.ready()){
					linha = modeloLer.readLine();
					if(linha.contains(modelo)){
						StringTokenizer st = new StringTokenizer(linha, "\": ");
						while(st.hasMoreTokens()){
							temp = st.nextToken();
							if(temp.contains("id")){
								aux = st.nextToken();
							}
						}
					break;
					}
				}
				modeloLer.close();
				modelo1.close();
			}catch(Exception o){
				o.printStackTrace();
			}
			iDmodelo = aux;
			return iDmodelo;
		}
	//RETORNA O ID DE UMA VERSAO ---- FUNICONANDO
	public String buscarIDVersao(String tipo, String marca, String modelo, String versao){
			tipo = tipo.toUpperCase();
			marca = marca.toUpperCase();
			String iDversao = null;
			String temp = null, aux = null;
			File arq = new File("DADOS\\"+tipo+"\\"+marca+"\\"+modelo+".txt");
			try{
				FileReader modelo1 = new FileReader(arq);
				BufferedReader modeloLer = new BufferedReader(modelo1);
				String linha;
				while(modeloLer.ready()){
					linha = modeloLer.readLine();
					if(linha.contains(StringEscapeUtils.unescapeJava(this.converterPalavra1(versao)))){
						StringTokenizer st = new StringTokenizer(linha, "\": ");
						while(st.hasMoreTokens()){
							temp = st.nextToken();
							if(temp.equals("id")){
								aux = st.nextToken();
							}
						}
					break;
					}
				}
				modeloLer.close();
				modelo1.close();
			}catch(Exception o){
				o.printStackTrace();
			}
			iDversao = aux;
			return iDversao;
		}
	
	//OUTROS
	//BAIXA TODOS OS DADOS DE UM MODELO DE UM DETERMINADA MARCA E TIPO
	public String dadosModelos(String tipo, String marca) throws IOException{
		String retorno = null, temp = null, aux = null;
		tipo = tipo.toUpperCase();
		String[] listaDeModelos = new String[this.quantidadeDeMarcas(tipo)];
		listaDeModelos = this.listarMarcas(tipo);
		for(int i = 0; i < listaDeModelos.length; i++){
		marca = listaDeModelos[i];
		int contador = 0;
		int marcaId = this.buscaPorMarca(tipo, marca);
		File carros = new File("DADOS\\"+tipo+"\\"+marca+".txt");
		if(!(carros.exists())){
			FileWriter carrosWriter = new FileWriter(carros);
			BufferedWriter gravarCarros = new BufferedWriter(carrosWriter);
			try{
				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpGet httpPost = new HttpGet(link+"carros/veiculos/"+marcaId+".json");
				HttpResponse response = httpClient.execute(httpPost);
				HttpEntity entity = response.getEntity();
				String body = EntityUtils.toString(entity);
				StringTokenizer st = new StringTokenizer(body, "}", true);
				while(st.hasMoreTokens()){
					temp = st.nextToken();
					if(temp.contains("id")){
						contador++;
					}
				}
				Integer numero = contador;
				this.marcasCarros = contador;
				aux = Integer.toString(numero);
				gravarCarros.write("{\"contador\" = \""+aux+"\"}\n");
				StringTokenizer st1 = new StringTokenizer(body, "}", true);
				while(st1.hasMoreTokens()){
					temp = st1.nextToken();   				
					gravarCarros.write(temp+"\n");
				}
				retorno = "Arquivo de carros baixados com sucesso!!";
			}
			catch(Exception e){
				e.printStackTrace();
			}
			gravarCarros.close();	
			carrosWriter.close();
		}else{
			return "Arquivo de carros já existe!!";
		}
		}
		return retorno;

	}
	//RETORNA TOTAL DE VERSOES DE MODELO ESPECIFICO   -------     PRECISA TESTAR NO MAIN ----------
	public Integer totalDeVersoes(String tipo, String marca, String modelo){
		Integer contador = 0;
		marca = marca.toUpperCase();
		tipo = tipo.toUpperCase();
		String aux = null;
		File arq = new File("DADOS\\"+tipo+"\\"+marca+"\\"+modelo+".txt");
		try{
			FileReader versoes = new FileReader(arq);
			BufferedReader versoesLer = new BufferedReader(versoes);
			String linha;
			while(versoesLer.ready()){
				linha = versoesLer.readLine();
				if(linha.contains("contador")){
					String temp = null;
					StringTokenizer st = new StringTokenizer(linha, "\"");
					st.nextToken();
					st.nextToken();
					st.nextToken();
					aux = st.nextToken();
					}
				break;
			}
			versoesLer.close();
			versoes.close();
		}catch(Exception o){
			o.printStackTrace();
		}
		if(!aux.equals(null)){
			contador = Integer.parseInt(aux);
			contador = (contador/2)+1;
		}else{
			contador = 0;
		}
		return contador;
	}
	//RETORNAR O ID (EM STRING - PARA CRIAR O LINK DE DOWNLOAD) DA VERSAO DO VEICULO  ---- PRECISA TESTAR NO MAIN ------
	public String retornaIDVersao(String tipo, String marca, String modelo, String versao) throws IOException{
		String retorno = null;
		tipo = tipo.toUpperCase();
		marca = marca.toUpperCase();
		String diretorio = "DADOS\\"+tipo+"\\"+marca+"\\"+modelo+".txt";
		File arq = new File(diretorio);
		String aux = null;
		try{
			FileReader modelos = new FileReader(arq);
			BufferedReader modelosLer = new BufferedReader(modelos);
			String linha;
			String temp = null;
			while(modelosLer.ready()){
				linha = modelosLer.readLine();
				if(linha.contains(versao)){
					StringTokenizer st = new StringTokenizer(linha, "\": ");
					while(st.hasMoreTokens()){
						temp = st.nextToken();
						if(temp.contains("id")){
							aux = st.nextToken();
						}
					}
				}
			}
			modelosLer.close();
			modelos.close();
		}catch(Exception o){
			o.printStackTrace();
		}
		retorno = aux;
		return retorno;      
	}
	//BAIXA TODAS OS IDS DAS VERSOES DE UM DETERMINADO MODELO
	public String pegaIDVersao(String tipo, String marca, String modelo){
		String id = null;
		tipo = tipo.toUpperCase();
		marca = marca.toUpperCase();
		String aux = null;
		File arq = new File("DADOS\\"+marca+"\\"+modelo+".txt");
		try{
			FileReader marcas = new FileReader(arq);
			BufferedReader marcasLer = new BufferedReader(marcas);
			String linha;
			while(marcasLer.ready()){
				linha = marcasLer.readLine();
				if(linha.contains(marca)){
					String temp = null;
					StringTokenizer st = new StringTokenizer(linha, "\": ");
					while(st.hasMoreTokens()){
						temp = st.nextToken();
						if(temp.contains("id")){
							aux = st.nextToken();
						}
					}
				}
			}
			marcasLer.close();
			marcas.close();
		}catch(Exception o){
			o.printStackTrace();
		}
		
		
		
		
		return id;
	}
		
	
	//###########METODO PRINCIPAL QUE IRÁ RETORNA O PREÇO DE UMA VERSAO#############
	public String retornaPreco(String tipo, String marca, String modelo, String versao){
		String preco = null;
		tipo = tipo.toUpperCase();
		marca = marca.toUpperCase();
		String temp = null, aux = null;
		File arq = new File("DADOS\\"+tipo+"\\"+marca+"\\"+this.converterPalavra1(modelo)+"\\"+versao+".txt");
		try{
			FileReader modelo1 = new FileReader(arq);
			BufferedReader modeloLer = new BufferedReader(modelo1);
			String linha;
			while(modeloLer.ready()){
				linha = modeloLer.readLine();
				if(linha.contains("preco")){
					StringTokenizer st = new StringTokenizer(linha, "\":");
					while(st.hasMoreTokens()){
						temp = st.nextToken();
						if(temp.equals("preco")){
							st.nextToken();
							temp = st.nextToken();
							aux = temp;
						}
					}
				break;
				}
			}
			modeloLer.close();
			modelo1.close();
		}catch(Exception o){
			o.printStackTrace();
		}
		if(!aux.equals(null)){
			preco = aux;
		}else{
			preco = "Preco inexistente";
		}
		return preco;
	}

	//DOWNLOADS LIMITADOS
	//BAIXAR CERTA MARCA DE CERTO TIPO
	
	
}