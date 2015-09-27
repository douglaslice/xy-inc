package br.com.zup.controlller.impl;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.controlller.CepController;
import br.com.zup.dto.EnderecoDTO;
import br.com.zup.exceptions.NegocioException;
import br.com.zup.exceptions.ValidacaoException;
import br.com.zup.service.CepService;

/**
 * Implementação do controller que expõe os serviços relacionados a CEP  
 * @author Douglas Felice
 */
@RestController
@RequestMapping(value = "/services/cep")
public class CepControllerImpl implements CepController{
	
	
	private static final Logger LOGGER = LoggerFactory
            .getLogger(CepControllerImpl.class);
	
	@Autowired
	private CepService cepService;

	/**
	 * Retorna uma lista de endereços relacionados com o CEP 
	 * passados como parametro 
	 * @param cep -  Parâmetro obrigatório, caso o mesmo não seja preenchido
	 * 			 	 o sistema retorna código de resposta PRECONDITION_FAILED(HTTP Error 412)
	 * @return
	 * @throws NegocioException 
	 * @throws ValidacaoException 
	 * @throws IOException 
	 */
	@RequestMapping(value = "/buscarEnderecoPorCep", method = RequestMethod.GET)
	@ResponseBody
	@Override
	public List<EnderecoDTO> buscarEnderecoPorCep(String cep) throws ValidacaoException, Exception {
		
		LOGGER.info("Pesquisando endereço pelo cep: {}", cep);
		return cepService.buscarEnderecoPorCep(cep);
		
	}

	/**
	 * Procura o cep que o logradouro passado como parametro está vinculado
	 * @param logradouro - Parâmetro obrigatório, caso o mesmo não seja preenchido
	 * 					   o sistema retorna código de resposta PRECONDITION_FAILED(HTTP Error 412)
	 * @return
	 * @throws NegocioException 
	 * @throws ValidacaoException 
	 * @throws IOException 
	 */
	@RequestMapping(value = "/buscarCepPorEndereco", method = RequestMethod.GET)
	@ResponseBody
	@Override
	public List<EnderecoDTO> buscarCepPorEndereco(String logradouro) throws ValidacaoException, Exception {
		
		LOGGER.info("Pesquisando Cep pelo logradouro: {}", logradouro);
		return cepService.buscarCepPorEndereco(logradouro);
		
	}
	

}
