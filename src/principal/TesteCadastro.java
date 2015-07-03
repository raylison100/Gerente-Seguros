package principal;

import java.io.IOException;
import java.util.Scanner;

import com.poo.execoes.CPFCadastradoExeception;
import com.poo.execoes.CPFInvalidoExeception;
import com.poo.execoes.CadatroPessoaExistenteExeception;
import com.poo.execoes.ProcuraPessoaInexistenteExeception;
import com.poo.negocios.GerenciadorFachada;
import com.poo.negocios.IGerenciador;
import com.poo.negocios.beans.Automovel;
import com.poo.negocios.beans.Cliente;
import com.poo.negocios.beans.Pessoa;
import com.poo.negocios.beans.Endereco;

public class TesteCadastro {

	public static void main(String[] agrs) throws IOException,
			ProcuraPessoaInexistenteExeception,
			CadatroPessoaExistenteExeception, CPFCadastradoExeception {

		int escolha;
		int cont = 0;
		String nome;
		IGerenciador fachada = GerenciadorFachada.getInstance();

		Scanner ler = new Scanner(System.in);

		while (cont == 0) {

			System.out.println("********  Menu  *********");
			System.out
					.printf("1 Cadastra cliente\n2 Exibi clientes\n3 Excluir clientes\n4 Procurar clientes\n5 SAIR");
			System.out.println("\n");

			escolha = ler.nextInt();
			System.out.println();

			switch (escolha) {
			case 1:

				Cliente cliente1 = null;
				Pessoa cliente2 = null;
				Cliente cliente3 = null;

				try {
					Endereco endereco1 = new Endereco("sao cristovao",
							"torroes", "50440280", "242", "b", "recife",
							"pernambuco");
					Endereco endereco2 = new Endereco("sao cristovao",
							"torroes", "50440280", "242", "b", "recife",
							"pernambuco");
					Endereco endereco3 = new Endereco("sao cristovao",
							"torroes", "50440280", "242", "b", "recife",
							"pernambuco");

		
			
                
                    
                    
					cliente1 = new Cliente( "RAYLISON", "10103566406",
							"8123321", "12\12\12", "SDS", "MASCULINO",
							"8188458982", "SOLTEIRO", endereco1,"12345777", "12\12\12",
							"13\12\15", "JOGADOR", "MEDIO COMPLETO");
					cliente2 = new Cliente( "ELAINE", "82024732259",
							"8123321", "12\12\12", "SDS", "MASCULINO",
							"8188458982", "SOLTEIRO", endereco2,"12345777", "12\12\12",
							"13\12\15", "JOGADOR", "MEDIO COMPLETO");
					cliente3 = new Cliente("MARCOS", "38138643838",
							"8123321", "12\12\12", "SDS", "MASCULINO",
							"8188458982", "SOLTEIRO", endereco3,"12345777", "12\12\12",
							"13\12\15", "JOGADOR", "MEDIO COMPLETO");
					
					//contrato = new Cliente();

					fachada.cadatrarCliente("Jose antonio", "10103566406",
							"8123321", "12\12\12", "SDS", "MASCULINO",
							"8188458982", "SOLTEIRO","sao cristovao",
									"torroes", "50440280", "242", "b", "recife",
									"pernambuco","12345777", "12\12\12",
									"13\12\15", "JOGADOR", "MEDIO COMPLETO");
					
					

					System.out.println("CADASTRO COM SUCESSO");

				} catch (CadatroPessoaExistenteExeception cpe) {

					System.out.println(cpe.getMessage());
				}  catch (CPFInvalidoExeception cpfi) {
					System.out.println(cpfi.getMessage());
				}

				break;

			case 2:

				fachada.exibiCliente();
				break;

			case 3:

				try {
					System.out.println("DIGITE UM NOME A SER EXCLUIDO:\n");
					nome = ler.next();
					fachada.excluirPessoa(nome);
				} catch (ProcuraPessoaInexistenteExeception ppi) {
					System.out.println(ppi.getMessage());
				}
				break;

			case 4:

				try {
					System.out.println("Pesquisa rapida digite nome: ");
					nome = ler.next();
					System.out.println(fachada.pesquisarPessoa(nome));
				} catch (ProcuraPessoaInexistenteExeception ppi) {
					System.out.println(ppi.getMessage());
				}
				break;

			case 5:
				++cont;
				break;
			}

		}

		ler.close();

	}

}
