package br.com.zup.service.impl;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import br.com.zup.dto.EnderecoDTO;
import br.com.zup.exceptions.ValidacaoException;
import br.com.zup.htlmParser.impl.EnderecosHtmlParser;
import br.com.zup.service.CepService;

/**
 * Implementação dos serviços relacionados a CEP
 * 
 * @author Douglas Felice
 */
@Service
public class CepServiceImpl implements CepService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CepServiceImpl.class);
	
	@Autowired
	private Environment env;
	
	@Autowired
	private EnderecosHtmlParser enderecosHtmlParser;
	
	
	/**
	 * Retorna uma lista de endereços relacionados com o CEP passados como
	 * parametro. Caso não seja encontrado nenhum endereço o sistema dispara uma
	 * exeção de negócio.
	 * 
	 * @param cep - Parâmetro obrigatório, caso o mesmo não seja preenchido o
	 *              sistema dispara uma execeção de validação
	 * @return
	 * @throws IOException 
	 */
	@Override
	public List<EnderecoDTO> buscarEnderecoPorCep(String cep) throws ValidacaoException, Exception {
		
		validaCep(cep);
		
		String url = String.format(env.getProperty("urlCorreio.buscaPorCep"), cep);
		List<EnderecoDTO> retorno = pesquisaEnderecos(url);
		
		LOGGER.info("Foram encontrados {} endereços", retorno.size());
		
		return retorno;
	}
	
	/**
	 * Procura os CEPs que o logradouro passado como parametro está vinculado.
	 * @param logradouro - Parâmetro obrigatório, caso o mesmo não seja preenchido o
	 *            sistema dispara uma execeção de validação
	 * @return
	 */
	@Override
	public List<EnderecoDTO> buscarCepPorEndereco(String logradouro) throws ValidacaoException, Exception {
		
		validaLogradouro(logradouro);
		
		String url = String.format(env.getProperty("urlCorreio.buscaPorLogradouro"), logradouro);
		List<EnderecoDTO> retorno = pesquisaEnderecos(url);
		
		LOGGER.info("Foram encontrados {} ceps", retorno.size());
		
		return retorno;
	}
	
	
	/**
	 * Realiza validação do CEP
	 * @param cep
	 */
	private void validaCep(String cep)throws ValidacaoException{
		
		if(cep == null || cep.trim().isEmpty()){
			throw new ValidacaoException("CEP não informado!");
		}
		
		cep = cep.replaceAll("-", "");
		
		if(cep.length() != 8){
			throw new ValidacaoException("CEP deve possuir 8 digitos!");
		}
		
		if(cep.replaceAll("[^0-9]", "").length() != 8){
			throw new ValidacaoException("CEP deve possuir apenas números!");
		}
		
	}
	
	
	/**
	 * Realiza validação do lograduro
	 * @param cep
	 */
	private void validaLogradouro(String logradouro)throws ValidacaoException{
		
		if(logradouro == null || logradouro.trim().isEmpty()){
			throw new ValidacaoException("Logradouro não informado!");
		}
		
	}
	
	/**
	 * Pesquisa endereços no correio baseando-se pela url
	 * @param url
	 * @return
	 * @throws IOException 
	 */
	private List<EnderecoDTO> pesquisaEnderecos(String url) throws Exception{
		return enderecosHtmlParser.parse(url);
	}

}
