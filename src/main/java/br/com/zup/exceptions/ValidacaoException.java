package br.com.zup.exceptions;

/**
 * Exceção que deve ser estourada em falhas de validações em geral
 * @author Douglas Felice
 *
 */
public class ValidacaoException extends NegocioException {

	private static final long serialVersionUID = 6892821811533076971L;

	public ValidacaoException() {
        super();
    }

    public ValidacaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidacaoException(String message) {
        super(message);
    }

    public ValidacaoException(Throwable cause) {
        super(cause);
    }

}
