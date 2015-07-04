package com.poo.negocios.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Contrato implements Serializable {

	// atributos
    private int numeroContrato;
    private String categoria; // premio= 1 , lite= 2, basic 3u 
    private String duracao;
    private double valor;
    private Automovel automovel;
    private static int numContratosFeitos = 1;
    private String ativado;
    private Calendar calendar;
    private SimpleDateFormat formatter;
    private Date minhaDataEncapsulada;
    private String dataFormatada;
    private int somaData;
    private int ano;

	// construtor
	// fazer o calculo da data final com base na data de inicio e na duracao
    // *obs modificar depois
    public Contrato(String duracao, String categoria) {

        this.setDuracao(duracao);
        this.setCategoria(categoria);
        this.setNumeroContrato();
        numContratosFeitos = numContratosFeitos + 1;
        this.calendar = Calendar.getInstance();
        this.formatter = new SimpleDateFormat("dd/MM/YYYY");
        this.minhaDataEncapsulada = calendar.getTime();
        this.dataFormatada = formatter.format(minhaDataEncapsulada);
        this.setSomaData(Integer.getInteger(dataFormatada.substring(4, 6)) + Integer.getInteger(this.duracao));
        this.ano = Integer.getInteger(dataFormatada.substring(9)) + 1;
    }

	// metodos
    public void setSomaData(int x) {

        int somaDat = x;
        if (somaDat > 12) {
            somaDat = somaDat - 12;
        }
        
        this.somaData = somaDat;

    }

    public String getDataFormatada() {
        return dataFormatada;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getNumeroContrato() {
        return numeroContrato;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        if (duracao != null) {
            this.duracao = duracao;
        }
    }

  

    public static int getNumContratosFeitos() {
        return numContratosFeitos;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double d) {
        
        valor = d;
       
    }

    private void setNumeroContrato() {

        numeroContrato = 0001 + numContratosFeitos;
    }

    public Automovel getAutomevel() {
        return automovel;
    }

    public void setAutomevel(Automovel automovel) {
        if (automovel != null) {
            this.automovel = automovel;
        }
    }

  

    public boolean contadorDeaAtivacao() {
        boolean contratoExpirado = false;
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatada = new SimpleDateFormat("dd/MM//YYYY");
        Date data = cal.getTime();
        String dataForma = formatada.format(data);
        int anoAtual = Integer.getInteger(dataForma.substring(9));
        int mesAtual = Integer.getInteger(dataForma.substring(4, 6));

        if (mesAtual == this.somaData && anoAtual > this.ano) {
            contratoExpirado = true;
        }

        return contratoExpirado;
    }
    
    public String ativado(){
        if(this.contadorDeaAtivacao() == false){
            return "Ativado";
        }
        else return "Expirou";
    }

    @Override
    public String toString() {
        return "Data de inicio: "+dataFormatada + "\nSituação: " + ativado();
        
    }

}
