package dev.kirin.toy.lottoweb.common.exception;

import dev.kirin.toy.lottoweb.util.StringUtils;
import org.springframework.http.HttpStatus;

public class NotFoundException extends ApiException {
    private final Object id;

    public NotFoundException(Object id) {
        super(StringUtils.format("Id '{}' is not found.", id));
        this.id = id;
    }

    public NotFoundException(String message, Object id) {
        super(message);
        this.id = id;
    }

    public NotFoundException(String message, Throwable cause, Object id) {
        super(message, cause);
        this.id = id;
    }

    public NotFoundException(Throwable cause, Object id) {
        super(cause);
        this.id = id;
    }

    public NotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Object id) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.id = id;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
