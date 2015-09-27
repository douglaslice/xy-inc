package br.com.zup.htlmParser;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Interface responsável por requisitar e parsear html em objetos
 * @author Douglas Felice
 *
 * @param <T>
 */
public interface HtmlParser<T> {
	
	/**
	 * Parsea o html obtido pela url e retorna um objeto com 
	 * o tipo genérico instanciado
	 * @param doc
	 * @return
	 */
	public T parse(String url) throws Exception;
	
	/**
	 * Requisita a URL e retorna um documento 
	 * @param url - Url completa que requisita o html 
	 * @return
	 * @throws IOException
	 */
	default Document makeRequest(String url) throws IOException {
		return Jsoup.connect(url).get();
	}

}
