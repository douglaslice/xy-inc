package br.com.zup.controlller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.zup.dto.ErrorInfo;
import br.com.zup.exceptions.NegocioException;
import br.com.zup.exceptions.ValidacaoException;

/**
 * Captura exceções  dos controllers, tratando para que seja passado a exceção 
 * de maneira limpa para  o solicitante
 * @author Douglas Felice
 *
 */
@ControllerAdvice
public class ControllerExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionHandler.class);
	
	
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorInfo defaultErrorHandler(HttpServletRequest request,
            Exception ex) throws Exception {
        LOGGER.error("Exception Handler Default: {}", ex.getLocalizedMessage(), ex);
        ErrorInfo errorInfo = new ErrorInfo(HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getLocalizedMessage());
        return errorInfo;
    }

    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ExceptionHandler(ValidacaoException.class)
    @ResponseBody
    ErrorInfo handleBusinessException(HttpServletRequest req,
    		ValidacaoException ex) {
        LOGGER.error("ValidacaoException: {}", ex.getMessage());
        ErrorInfo errorInfo = new ErrorInfo(HttpStatus.PRECONDITION_FAILED,
                ex.getMessage());
        return errorInfo;
    }
    
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ExceptionHandler(NegocioException.class)
    @ResponseBody
    ErrorInfo handleBusinessException(HttpServletRequest req,
    		NegocioException ex) {
        LOGGER.error("NegocioException: {}", ex.getMessage());
        ErrorInfo errorInfo = new ErrorInfo(HttpStatus.PRECONDITION_FAILED,
                ex.getMessage());
        return errorInfo;
    }
	
}
