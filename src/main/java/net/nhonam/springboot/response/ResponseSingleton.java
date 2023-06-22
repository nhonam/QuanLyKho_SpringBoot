package net.nhonam.springboot.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseSingleton {
    private static ResponseSingleton instance;

    private ResponseSingleton() {
        // private constructor to prevent instantiation
    }

    public static synchronized ResponseSingleton getInstance() {
        if (instance == null) {
            instance = new ResponseSingleton();
        }
        return instance;
    }

    public ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        map.put("status", status.value());
        map.put("data", responseObj);

        return new ResponseEntity<>(map, status);
    }
}