package com.poo.negocios;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.apache.commons.lang.StringEscapeUtils;


import com.poo.negocios.beans.Cliente;

import java.util.regex.*;
import java.rmi.UnexpectedException;
import java.util.Locale;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URLDecoder;
import java.util.ArrayList;


public class BuscarInformacoesProfessor{
		static Cliente prof;
		
	public static void main(String[] args) throws IOException {
		DadosFipe a = new DadosFipe();
		}
	
}
