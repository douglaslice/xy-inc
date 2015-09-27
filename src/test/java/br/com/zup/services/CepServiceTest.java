package br.com.zup.services;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.zup.dto.EnderecoDTO;
import br.com.zup.exceptions.ValidacaoException;
import br.com.zup.htlmParser.impl.EnderecosHtmlParser;
import br.com.zup.service.CepService;
import br.com.zup.service.impl.CepServiceImpl;
import br.com.zup.utils.EnderecoDTOUtils;

/**
 * Classe de teste CepService
 * @author Douglas Felice
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@TestPropertySource({"classpath:application.properties"})
public class CepServiceTest {
	
	@Autowired
	private CepService instance;
	
	@Test
	public void buscarEnderecoPorCepSucessTest() throws ValidacaoException, Exception{
		List<EnderecoDTO> enderecos = instance.buscarEnderecoPorCep("38414-400");
		Assert.assertEquals(enderecos.size(), 1);
		Assert.assertEquals(enderecos.get(0).getLogradouro(), "Logradouro");
	}
	
	@Test(expected=ValidacaoException.class)
	public void buscarEnderecoPorCepValidationNullParamTest() throws ValidacaoException, Exception{
		instance.buscarEnderecoPorCep("");
	}
	
	@Test(expected=ValidacaoException.class)
	public void buscarEnderecoPorCepValidationCharactersTest() throws ValidacaoException, Exception{
		instance.buscarEnderecoPorCep("123456789");
	}
	
	@Test(expected=ValidacaoException.class)
	public void buscarEnderecoPorCepValidationOnlyNumberTest() throws ValidacaoException, Exception{
		instance.buscarEnderecoPorCep("38414-ABC");
	}
	
	@Test
	public void buscarCepPorEnderecoSucessTest() throws ValidacaoException, Exception{
		List<EnderecoDTO> enderecos = instance.buscarCepPorEndereco("Avenida Teste");
		Assert.assertEquals(enderecos.size(), 1);
		Assert.assertEquals(enderecos.get(0).getCep(), "38414-400");
	}
	
	@Test(expected=ValidacaoException.class)
	public void buscarCepPorEnderecoValidationNullParamTest() throws ValidacaoException, Exception{
		instance.buscarCepPorEndereco("");
	}
	
	//INJEÇÕES DE MOCKS
	@Configuration
	static class Config {
		
		@Bean
		public CepService getCepService(){
			return new CepServiceImpl();
		}
		
		@Bean
		public EnderecosHtmlParser getEnderecosHtmlParser() throws ValidacaoException, Exception{
			EnderecosHtmlParser enderecosHtmlParser = Mockito.mock(EnderecosHtmlParser.class);
			List<EnderecoDTO> enderecos = EnderecoDTOUtils.getListaEnderecoMock();
			Mockito.when(enderecosHtmlParser.parse(Mockito.anyString())).thenReturn(enderecos);
			return enderecosHtmlParser;
		}
	}
	
}
