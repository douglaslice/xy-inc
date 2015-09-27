package br.com.zup.htlmParser.impl;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import br.com.zup.dto.EnderecoDTO;
import br.com.zup.exceptions.ValidacaoException;
import br.com.zup.htlmParser.HtmlParser;

/**
 * 
 * @author Douglas Felice
 *
 */
@Service
public class EnderecosHtmlParser implements HtmlParser<List<EnderecoDTO>> {
	
	private final static String LOGRADOURO_ABRANGENTE = "procure ser mais especifico";

	
	/**
	 * Parsea o html obtido pela url e retorna uma lista de endereço 
	 * @param doc
	 * @return
	 */
	@Override
	public List<EnderecoDTO> parse(String url) throws Exception{
		Document doc = makeRequest(url);
		
		List<EnderecoDTO> ret = new ArrayList<EnderecoDTO>();
		
		if(doc.toString().contains(LOGRADOURO_ABRANGENTE)){
			throw new ValidacaoException("O logradouro informado é abrangente, procure ser mais específico!");
		}
		
		//Parseando html
		Elements lines;
		try{
			lines = doc.select(".ctrlcontent table").get(2).select("tr");
		}catch(Exception e){
			return ret;// nenhum endereço encontrado
		}
		
		for (Element tr : lines) {
			Elements tds = tr.select("td");
			
			EnderecoDTO endereco = new EnderecoDTO();
			int idx = 0;
			
			endereco.setLogradouro(tds.get(idx++).text());
			endereco.setBairro(tds.get(idx++).text());
			endereco.setLocalidade(tds.get(idx++).text());
			endereco.setUf(tds.get(idx++).text());
			endereco.setCep(tds.get(idx++).text());
			
			ret.add(endereco);
		}
		
		return ret;
	}

}
