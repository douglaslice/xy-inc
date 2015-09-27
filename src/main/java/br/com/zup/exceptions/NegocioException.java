package br.com.zup.exceptions;

/**
 * Exceção de negócio genérica.
 *
 * @author Douglas Felice
 */
public class NegocioException extends Exception {

	private static final long serialVersionUID = -3042890270004631699L;

	public NegocioException() {
    }

    public NegocioException(String message) {
        super(message);
    }

    public NegocioException(String message, Throwable cause) {
        super(message, cause);
    }

    public NegocioException(Throwable cause) {
        super(cause);
    }

}
