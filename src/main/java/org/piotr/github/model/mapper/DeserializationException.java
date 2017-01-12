package org.piotr.github.model.mapper;


public class DeserializationException extends RuntimeException {
    private String response;

    public DeserializationException(String response, String message) {
        super(message);
        this.response = response;
    }

    public DeserializationException(String response, Throwable cause) {
        super(cause);
        this.response = response;
    }

    public DeserializationException(String response, String mesage, Throwable cause) {
        super(mesage, cause);
        this.response = response;
    }

    public String getResponse() {
        return response;
    }
}
