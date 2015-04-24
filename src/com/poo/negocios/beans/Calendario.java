package com.poo.negocios.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Calendario implements Serializable {
	
	//atributos
	
	private Calendar calendar; 
    private SimpleDateFormat formatter;
    private Date minhaDataEncapsulada; 
    private String dataFormatada;

    //construtor
    
    public Calendario(){
    	
    	this.calendar = Calendar.getInstance();
    	this.formatter  = new SimpleDateFormat("dd/MMM/YYYY    HH: mm: ss");
    	this.minhaDataEncapsulada = calendar.getTime();
    	this.dataFormatada = formatter.format(minhaDataEncapsulada);
        
    }
    
    //metodos
    
    public String toString(){
    	
    	return "\n\n   " + dataFormatada;
    }

}
