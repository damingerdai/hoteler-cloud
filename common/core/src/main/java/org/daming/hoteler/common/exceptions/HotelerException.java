package org.daming.hoteler.common.exceptions;

import java.util.StringJoiner;

public class HotelerException extends RuntimeException {

    private int code;

    private String message;

    private Throwable cause;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Throwable getCause() {
        return cause;
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
    }

    public HotelerException() {
        super();
    }

    public HotelerException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public HotelerException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
        this.cause = cause;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", HotelerException.class.getSimpleName() + "[", "]")
                .add("code=" + code)
                .add("message='" + message + "'")
                .add("cause=" + cause)
                .toString();
    }
}
