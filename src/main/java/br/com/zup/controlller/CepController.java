package br.com.zup.controlller;

import java.io.IOException;
import java.util.List;

import br.com.zup.dto.EnderecoDTO;
import br.com.zup.exceptions.NegocioException;
import br.com.zup.exceptions.ValidacaoException;

/**
 * Interface com os serviços expotos relacionados à CEP 
 * @author Douglas Felice
 *
 */
public interface CepController {
	
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
	public List<EnderecoDTO> buscarEnderecoPorCep(String cep) throws ValidacaoException, Exception;
	
	/**
	 * Procura o cep que o logradouro passado como parametro está vinculado
	 * @param logradouro - Parâmetro obrigatório, caso o mesmo não seja preenchido
	 * 					   o sistema retorna código de resposta PRECONDITION_FAILED(HTTP Error 412)
	 * @return
	 * @throws NegocioException 
	 * @throws ValidacaoException 
	 * @throws IOException 
	 */
	public List<EnderecoDTO> buscarCepPorEndereco(String logradouro) throws ValidacaoException, Exception;
	
	

}
