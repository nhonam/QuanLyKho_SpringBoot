package net.nhonam.springboot.response;

import org.springframework.http.HttpStatus;

public class ApiResponse {
    HttpStatus httpStatus;
    private boolean success;
    private Object data;
    private String message;

    public ApiResponse(boolean success, Object data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }

    public ApiResponse(boolean success, Object data, String message, HttpStatus httpStatus) {
        this.success = success;
        this.data = data;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


