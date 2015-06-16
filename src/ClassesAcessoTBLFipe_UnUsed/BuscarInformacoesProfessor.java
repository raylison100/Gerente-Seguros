package ClassesAcessoTBLFipe_UnUsed;
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
		
	}
	/*
	*public static void dadosProfessores(){
	*	String temp = null;
	*	int k = 0;
	*	for(int j = 0; j<links.length; j++){
	*		try{
	*			DefaultHttpClient httpClient = new DefaultHttpClient();
	*			HttpGet httpPost = new HttpGet(links[j]);
	*			HttpResponse response = httpClient.execute(httpPost);
	*			HttpEntity entity = response.getEntity();
	*			String body = EntityUtils.toString(entity);
	*			StringTokenizer st = new StringTokenizer(body, "<>/");
	*			while(st.hasMoreTokens()){
	*				temp = st.nextToken();
	*				if(temp.contains("infpessoa")){
	*					st.nextToken();
	*					st.nextToken();
	*	//						System.out.println(st.nextToken());
	*	//						String decoded = URLDecoder.decode(st.nextToken(), "UTF-8");
	*					StringEscapeUtils nomeDecoded = new StringEscapeUtils();
	*					nome[j] = nomeDecoded.unescapeHtml(st.nextToken());
	*				}
	*			}
	*		}
	*		catch(Exception e){
	*			e.printStackTrace();
	*		}
	*	}
	*	}
	*
*/

/*
	* public static void linkLates(){
	*	String temp = null;
	*	try{
	*		
	*		DefaultHttpClient httpClient = new DefaultHttpClient();
	*		HttpPost httpPost = new HttpPost("http://www.deinfo.ufrpe.br/docentes");
	*		HttpResponse response = httpClient.execute(httpPost);
	*		HttpEntity entity = response.getEntity();
	*		String body = EntityUtils.toString(entity);
	*		//		System.out.println(body);
	*		StringTokenizer st = new StringTokenizer(body, "<>=\"");
	*		while(st.hasMoreTokens()){
	*			temp = st.nextToken();
	*			if(temp.contains(linkLinha)){
	*				
	*				links[i]=st.nextToken();
	*				i++;
	*			}
	*		}
	*	}
	*	catch(Exception e){
	*		e.printStackTrace();
	*	}
	*	}
*/
}
