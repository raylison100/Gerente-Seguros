//package com.poo.negocios;
//
//import java.util.StringTokenizer;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.util.EntityUtils;
//import org.apache.commons.lang.StringEscapeUtils;
//
///*GLOSSARIO DO CODIGO:
// * STRINGTOKENIZER = RECEBE COMO ENTRADA UMA STRING E RETORNA DIVERSAS
// *                     PARTES DESSA STRING COMO RETORNO, UTILIZANDO OS
//DELIMITADORES
// * HTTPCLIENT/HTTPGET/HTTPENTITY/HTTPRESPONSE = METODOS DA BIBLIOTECA DA APACHE
// *                     HTTPCOMPONNETS
// */
//
///*A CLASSE A SEGUIR SERÁ UTILIZADA PARA CRIAR UM ARRAY DE OBJETOS DO
//TIPO PROFESSOR
// * E SEUS RESPECTIVOS METODOS PARA O FUNCIONAMENTO TOTAL DO CODIGO E
//PARA ATENDER OS
// * REQUISITOS DO PROJETO DE PROGRAMAÇÃO ORIENTADA A OBJETOS (POO) DA
//DISCIPLINA DE
// * INTRODUÇÃO A PROGRAMAÇÃO II (IP2)
// *
// * METODOS EXISTENTES NA CLASSE:
// * inserirProfessor(Professor: void)
// * buscarProfessor(String : String)
// * dadosProfessor()
// * linkLates()
// *
// */
//
//@SuppressWarnings("deprecation")
//public class ArrayProfessor {
//    /*STRING AUXILIAR USADA NO METODO linkLates PARA FACILITAR A DESCOBERTA
//    DOS LINKS DE CADA PROFESSOR LISTADO NA PAGINA DO DEINFO  */
//    private static String linkLinha = "http://lattes.cnpq.br/";
//
//    /*ARRAY AUXILIAR PARA SEREM INSERIDOS OS LINKS DO LATTES DE
//    CADA PROFESSOR, OS LINKS FORAM OBTIDOS A PARTIR DO SITE DO DEINFO*/
//    static String links[] = new String[74];
//
//    private Professor professores[];
//
//    //CONSTRUTOR DA CLASSE
//    public ArrayProfessor() {
//        super();
//        this.professores = new Professor[74];
//        this.linkLates();
//        this.dadosProfessores();
//    }
//    
//
//    //METODO USADO PARA INSERIR UM PROFESSOR NO ARRAY DE PROFESSORES
//    private void inserirProfessor(Professor professor){
//        boolean inseriu = false;
//        for(int j = 0 ; (j < this.professores.length) && !inseriu ; j++ ){
//            if(this.professores[j]==null){
//                this.professores[j]=professor;
//                inseriu = true;
//            }
//        }
//    }
//
//    //METODO PARA BUSCA DE UM PROFESSOR POR NOME NO ARRAY DE PROFESSORES
//    public Professor buscaPorNome(String nome){
//        boolean achou = false;
//        Professor aux = null;
//        for(int i = 0;(i < this.professores.length) && !achou ; i++){
//            if(this.professores[i].getNome().equals(nome)){
//                aux = this.professores[i];
//                achou = true;
//            }
//        }
//        return aux;
//    }
//
//    //METODO QUE IRA BUSCAR PROFESSOR POR PROFESSOR EM SEU RESPECTIVO LINK E IRA ADICIONA-LO NO ARRAY DO OBJETO
//    private void dadosProfessores(){
//        String temp = null;
//        int k = 0;
//        //LAÇO QUE IRÁ PERCORRER TODO O ARRAY QUE POSSUI OS LINKS ACESSANDO-OS 1 POR 1.
//        for( int j = 0 ; j < links.length ; j++ ){
//            Professor professor = null;
//            try{
//                DefaultHttpClient httpClient = new DefaultHttpClient();
//                HttpGet httpPost = new HttpGet(links[j]);
//                HttpResponse response = httpClient.execute(httpPost);
//                HttpEntity entity = response.getEntity();
//                String body = EntityUtils.toString(entity);
//                StringTokenizer st = new StringTokenizer(body, "<>/");
//                /*LAÇO QUE IRÁ VARER TODO O ARQUIVO HTML CONVERTIDO EM STRING E 
//                 * PROCURAR TODOS OS PARAMETROS DEFINIDOS DENTRO DOS OPERADORES 
//                 * CONDICIONAIS A SEGUIR
//                 */
//                while(st.hasMoreTokens()){
//                    temp = st.nextToken();
//                    //OPERADOR CONDICIONAL QUE PROCURA A LINHA QUE POSSUI O NOME DO PROFESSOR.
//                    if(temp.contains("infpessoa")){
//                        st.nextToken();
//                        st.nextToken();
//                        StringEscapeUtils nomeDecoded = new StringEscapeUtils();
//                        //FUNÇAO QUE IRA ATRIBUIR O NOME DO PROFESSOR AO OBJETO
//                        professor.setNome(StringEscapeUtils.unescapeHtml(st.nextToken()));
//                    }
//                    //OPERADOR CONDICIONAL QUE IRA PROCURAR A LINHA QUE POSSUI O RESUMO DA CARREIRA DO PROFESSOR.
//                    if(temp.contains("class=\"resumo\"")){
//                        professor.setResumo(st.nextToken());
//                    }
//
//
//
//                }
//
//            }
//            catch(Exception e){
//                e.printStackTrace();
//            }
////            professor.setLinkCurriculum(links[j]);
//            //METODO QUE IRÁ INSERIR PREFESSOR USANDO OS PARAMETROS RECEBIDOS A PARTIR DO LAÇO FOR
//            inserirProfessor(professor);
//            }
//
//    }
//
//    //METODO QUE IRÁ OBTER OS LINKS DE TODOS OS PROFESSORES DO DEINFO APARTIR DO SITE DO DEPARTAMENTO
//    private void linkLates(){
//        String temp = null;
//        int i = 0;
//        try{
//            DefaultHttpClient httpClient = new DefaultHttpClient();
//            HttpPost httpPost = new HttpPost("http://www.deinfo.ufrpe.br/docentes");
//            HttpResponse response = httpClient.execute(httpPost);
//            HttpEntity entity = response.getEntity();
//            String body = EntityUtils.toString(entity);
//            //        System.out.println(body);
//            StringTokenizer st = new StringTokenizer(body, "<>=\"");
//            while(st.hasMoreTokens()){
//                temp = st.nextToken();
//                if(temp.contains(linkLinha)){
//                    links[i]=st.nextToken();
//                    i++;
//                }
//            }
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public String toString() {
//        String resultado = "";
//        for(int i = 0; i<this.professores.length;i++){
//            resultado += professores[i].getNome()+"\n";
//        }
//        return resultado;
//    }
//
//
//}