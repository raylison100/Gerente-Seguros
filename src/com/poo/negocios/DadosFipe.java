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

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.TokenParser;
import org.apache.http.util.EntityUtils;
import org.apache.commons.lang.StringEscapeUtils;


public class DadosFipe implements Serializable{
	/*STRING AUXILIAR USADA NO METODO linkLates PARA FACILITAR A DESCOBERTA
    DOS LINKS DE CADA PROFESSOR LISTADO NA PAGINA DO DEINFO  */
	private static String link = "http://fipeapi.appspot.com/api/1/";
	private int marcasVeiculos;
	private int marcasMotos;
	private int marcasCaminhoes;

	//CONSTRUTOR DA CLASSE
	public DadosFipe(String tipo, String marca) throws IOException {
		super();
		baixarDadosVeiculos();
		baixarDadosMotos();
		baixarDadosCaminhoes();
		//        System.out.println(buscaPorMarca(tipo, marca));
//		System.out.println(quantidadeDeMarcas(tipo));
		listarMarcas(tipo);
		baixarDadosCarrosPorMarca();
	}

	public String baixarDadosVeiculos() throws IOException{
		String retorno = null, temp = null, aux = null;
		int contador = 0;
		File veiculos = new File("DADOS\\VEICULOS_MARCAS.txt");
		if(!(veiculos.exists())){
			FileWriter veiculosWriter = new FileWriter(veiculos);
			PrintWriter gravarVeiculos = new PrintWriter(veiculosWriter);
			try{
				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpGet httpPost = new HttpGet(link+"veiculos/marcas.json");
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
				this.marcasVeiculos = contador;
				aux = Integer.toString(numero);
				veiculosWriter.write("{\"contador\" = \""+aux+"\"}\n");
				StringTokenizer st1 = new StringTokenizer(body, "}", true);
				while(st1.hasMoreTokens()){
					temp = st1.nextToken();   				
					veiculosWriter.write(temp+"\n");
				}
				retorno =  "Arquivo de veiculos baixados!!";
			}
			catch(Exception e){
				e.printStackTrace();
			}
			gravarVeiculos.close();
			veiculosWriter.close();
		}else{
			return "Arquivo de veiculos já existe!!";
		}

		return retorno;
	}

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
					}
				}
				Integer numero = contador;
				this.marcasMotos = contador;
				aux = Integer.toString(numero);
				motosWriter.write("{\"contador\" = \""+aux+"\"}\n");
				StringTokenizer st1 = new StringTokenizer(body, "}", true);
				while(st1.hasMoreTokens()){
					temp = st1.nextToken();   				
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
					}
				}
				Integer numero = contador;
				this.marcasCaminhoes = contador;
				aux = Integer.toString(numero);
				gravarCaminhoes.write("{\"contador\" = \""+aux+"\"}\n");
				StringTokenizer st1 = new StringTokenizer(body, "}", true);
				while(st1.hasMoreTokens()){
					temp = st1.nextToken();   				
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

	//Quantidade de marcas na lista definida no construtor
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
					System.out.println(aux);
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

	//Listar marcas
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
							listaMarcas[posicao] = temp;
							System.out.println(listaMarcas[posicao]);
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

	//RETORNA O ID DA MARCA
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

	public String baixarDadosCarrosPorMarca() throws IOException{
		String retorno = null, temp = null, aux = null, marca = null;
		String[] listaDeCarros = new String[this.quantidadeDeMarcas("veiculos")];
		listaDeCarros = this.listarMarcas("VEICULOS");
		for(int i = 0; i < listaDeCarros.length; i++){
		marca = listaDeCarros[i];
		int contador = 0;
		int marcaId = this.buscaPorMarca("VEICULOS", marca);
		File carros = new File("DADOS\\CARROS\\"+marca+".txt");
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
				this.marcasVeiculos = contador;
				aux = Integer.toString(numero);
				gravarCarros.write("{\"contador\" = \""+aux+"\"}\n");
				StringTokenizer st1 = new StringTokenizer(body, "}", true);
				while(st1.hasMoreTokens()){
					temp = st1.nextToken();   				
					gravarCarros.write(temp+"\n");
				}
				retorno = "Arquivo de caminhoes baixados com sucesso!!";
			}
			catch(Exception e){
				e.printStackTrace();
			}
			gravarCarros.close();	
			carrosWriter.close();
		}else{
			return "Arquivo de caminhoes já existe!!";
		}
		}
		return retorno;

	}
}
