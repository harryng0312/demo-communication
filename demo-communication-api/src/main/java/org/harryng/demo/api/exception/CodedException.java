package org.harryng.demo.api.exception;

public class CodedException extends Exception {
    public static final int SUCCESS = 0;
    public static final int NO_AUTH = 10;

    private final int code;

//    @Builder.Default
//    private final String message;

    public CodedException(int code) {
        super();
        this.code = code;
    }

    public CodedException(int code, String message) {
        super(message);
        this.code = code;
    }

    public CodedException(int code, String message, Throwable throwable) {
        super(message, throwable);
        this.code = code;
    }
    public int getCode() {return this.code;}
}
