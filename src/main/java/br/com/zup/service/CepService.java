package br.com.zup.service;

import java.util.List;

import br.com.zup.dto.EnderecoDTO;
import br.com.zup.exceptions.ValidacaoException;

/**
 * Interface dos serviços expotos de CEP 
 * @author Douglas Felice
 *
 */
public interface CepService {
	
	/**
	 * Retorna uma lista de endereços relacionados com o CEP 
	 * passados como parametro
	 * @param cep -  Parâmetro obrigatório, caso o mesmo não seja preenchido
	 * 			 	 o sistema dispara uma execeção de validação
	 * @return
	 */
	public List<EnderecoDTO> buscarEnderecoPorCep(String cep) throws ValidacaoException, Exception;
	
	/**
	 * Procura o cep que o logradouro passado como parametro está vinculado.
	 * @param logradouro - Parâmetro obrigatório, caso o mesmo não seja preenchido
	 * 					   o sistema dispara uma execeção de validação
	 * @return
	 */
	public List<EnderecoDTO> buscarCepPorEndereco(String logradouro) throws ValidacaoException, Exception;

}
