package com.github.codeboy.jokes4j.api;

public class JokeException extends RuntimeException {
    public JokeException() {
    }

    /**
     * constructs a JokeException with the given message
     * @param message Message to use
     */
    public JokeException(String message) {
        super(message);
    }

    public JokeException(String message, Throwable cause) {
        super(message, cause);
    }

    public JokeException(Throwable cause) {
        super(cause);
    }

    public JokeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
