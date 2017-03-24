package com.vain.flicker.api;

/**
 * @author Dominic Gunn (dominic@vain.gg)
 */
public class FlickerException extends RuntimeException {

    private final static long serialVersionUID = 1L;

    public FlickerException() {}
    public FlickerException(String errorMessage) {
        super(errorMessage);
    }

    public FlickerException(Throwable throwable) {
        super(throwable);
    }

    public FlickerException(String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
    }
}
