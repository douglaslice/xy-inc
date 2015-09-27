package br.com.zup.htlmParser;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.zup.dto.EnderecoDTO;
import br.com.zup.exceptions.ValidacaoException;
import br.com.zup.htlmParser.impl.EnderecosHtmlParser;


/**
 * Teste da classe EnderecosHtmlParser
 * @author Douglas Felice
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@TestPropertySource({"classpath:application.properties"})
public class EnderecosHtmlParserTest {
	
	@Autowired
	private EnderecosHtmlParser instance;
	
	@Autowired
	private Environment env;
	
	@Test
	public void buscarEnderecoPorCepTest() throws ValidacaoException, Exception{
		String url = String.format(env.getProperty("urlCorreio.buscaPorCep"), "38411-001");
		List<EnderecoDTO> enderecos =  instance.parse(url);
		Assert.assertTrue(enderecos.size() > 0);
	}
	
	@Test
	public void buscarEnderecoPorCepZeroResultsTest() throws ValidacaoException, Exception{
		String url = String.format(env.getProperty("urlCorreio.buscaPorCep"), "00000-000");
		List<EnderecoDTO> enderecos =  instance.parse(url);
		Assert.assertTrue(enderecos.size() == 0);
	}
	
	@Test(expected=ValidacaoException.class)
	public void buscarEnderecoPorCepManyResultsTest() throws ValidacaoException, Exception{
		String url = String.format(env.getProperty("urlCorreio.buscaPorLogradouro"), "Getulio%20Vargas");
		instance.parse(url);
	}
	
	//INJEÇÕES DE MOCKS
	@Configuration
	static class Config {
		
		@Bean
		public EnderecosHtmlParser getEnderecosHtmlParser(){
			return new EnderecosHtmlParser();
		}
		
	}	
	

}
