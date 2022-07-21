package dev.kirin.toy.lottoweb.core.scrap.parser;

public class ScrapParserException extends RuntimeException {
    public ScrapParserException() {
    }

    public ScrapParserException(String message) {
        super(message);
    }

    public ScrapParserException(String message, Throwable cause) {
        super(message, cause);
    }

    public ScrapParserException(Throwable cause) {
        super(cause);
    }

    public ScrapParserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
