package br.com.zup.dto;

import java.time.Instant;
import java.util.Date;
import org.springframework.http.HttpStatus;

/**
 * Objeto de resposta por trasportar dados de erros / validações
 * @author Douglas Felice
 */
public final class ErrorInfo {

    private final Date timestamp;

    private final HttpStatus status;

    private final String message;

    public ErrorInfo(HttpStatus status, String message) {
        Instant now = Instant.now();
        this.timestamp = Date.from(now);
        this.status = status;
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status.value();
    }

    public String getMessage() {
        return message;
    }

    public String getError() {
        return status.getReasonPhrase();
    }

}
