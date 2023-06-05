package net.nhonam.springboot.Utils;

import java.io.PrintWriter;

public class Exception extends java.lang.Exception {
    private String error;

    public Exception(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public void printStackTrace(PrintWriter s) {
        super.printStackTrace(s);
    }

    @Override
    public StackTraceElement[] getStackTrace() {
        return super.getStackTrace();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public Exception(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
