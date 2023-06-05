package net.nhonam.springboot.response;

import org.springframework.http.HttpStatus;

public class Response {
    private static Response instance;
    private Object data;
    private HttpStatus status;
    private String message;



    private Response() {
        // Private constructor
    }

    public static synchronized Response getInstance() {
        if (instance == null) {
            instance = new Response();
        }
        return instance;
    }

    public static void setInstance(Response instance) {
        Response.instance = instance;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
