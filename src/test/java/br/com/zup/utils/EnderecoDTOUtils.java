package br.com.zup.utils;

import java.util.ArrayList;
import java.util.List;

import br.com.zup.dto.EnderecoDTO;

/**
 * Classe auxiliar de testes que envolvem EnderecoDTO
 * @author Douglas Felice
 *
 */
public class EnderecoDTOUtils {
	
	/**
	 * Retorna uma lista de endereços para auxiliar testes
	 * @return
	 */
	public static List<EnderecoDTO> getListaEnderecoMock(){
		List<EnderecoDTO> enderecos = new ArrayList<EnderecoDTO>();
		EnderecoDTO endereco = new EnderecoDTO();
		endereco.setLogradouro("Logradouro");
		endereco.setBairro("Bairro");
		endereco.setLocalidade("Localiade");
		endereco.setUf("uf");
		endereco.setCep("38414-400");
		enderecos.add(endereco);
		
		return enderecos;
	}


}
