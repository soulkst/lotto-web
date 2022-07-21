package dev.kirin.toy.lottoweb.common.exception;

import dev.kirin.toy.lottoweb.util.StringUtils;
import org.springframework.http.HttpStatus;

public class AlreadyExistsException extends ApiException {
    private final Object id;

    public AlreadyExistsException(Object id) {
        super(StringUtils.format("Id '{}' is already exists.", id));
        this.id = id;
    }

    public AlreadyExistsException(String message, Object id) {
        super(message);
        this.id = id;
    }

    public AlreadyExistsException(String message, Throwable cause, Object id) {
        super(message, cause);
        this.id = id;
    }

    public AlreadyExistsException(Throwable cause, Object id) {
        super(cause);
        this.id = id;
    }

    public AlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Object id) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.id = id;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.CONFLICT;
    }
}
