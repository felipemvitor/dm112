package br.inatel.dm112.rest.support;

public class OrderStatusException extends RuntimeException{
    public OrderStatusException() {
        super();
    }

    public OrderStatusException(String message) {
        super(message);
    }

    public OrderStatusException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderStatusException(Throwable cause) {
        super(cause);
    }

    protected OrderStatusException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
