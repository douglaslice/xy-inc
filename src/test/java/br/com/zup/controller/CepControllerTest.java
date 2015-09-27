package br.com.zup.controller;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.zup.controlller.CepController;
import br.com.zup.controlller.impl.CepControllerImpl;
import br.com.zup.dto.EnderecoDTO;
import br.com.zup.exceptions.ValidacaoException;
import br.com.zup.service.CepService;
import br.com.zup.utils.EnderecoDTOUtils;


/**
 * Teste da classe CepController
 * @author Douglas Felice
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class CepControllerTest {
	
	@Autowired
	private CepController instance;
	
	@Test
	public void buscarEnderecoPorCepTest() throws ValidacaoException, Exception{
		List<EnderecoDTO> enderecos = instance.buscarEnderecoPorCep("38414-400");
		Assert.assertEquals(enderecos.size(), 1);
		Assert.assertEquals(enderecos.get(0).getLogradouro(), "Logradouro");
	}
	
	@Test
	public void buscarCepPorEnderecoTest() throws ValidacaoException, Exception{
		List<EnderecoDTO> enderecos = instance.buscarCepPorEndereco("Avenida Teste");
		Assert.assertEquals(enderecos.size(), 1);
		Assert.assertEquals(enderecos.get(0).getCep(), "38414-400");
	}
	
	//INJEÇÕES DE MOCKS
	@Configuration
	static class Config {
		
		@Bean
		public CepController getCepController(){
			return new CepControllerImpl();
		}
		
		@Bean
		public CepService getCepService() throws ValidacaoException, Exception{
			CepService cepService = Mockito.mock(CepService.class);
			
			List<EnderecoDTO> enderecos = EnderecoDTOUtils.getListaEnderecoMock();
			
			Mockito.when(cepService.buscarEnderecoPorCep(Mockito.anyString())).thenReturn(enderecos);
			Mockito.when(cepService.buscarCepPorEndereco(Mockito.anyString())).thenReturn(enderecos);
			
			return cepService;
		}

	}	
	

}
