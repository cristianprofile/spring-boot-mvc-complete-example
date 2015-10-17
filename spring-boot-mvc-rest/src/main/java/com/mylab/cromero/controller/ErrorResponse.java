package com.mylab.cromero.controller;

/**
 * Error Dto.
 * <p>
 * Dto user to map error value and message in controller api layer.
 * <p>
 */
public class ErrorResponse {

    private String message;
    private String code;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ErrorResponse [message=");
        builder.append(message);
        builder.append(", code=");
        builder.append(code);
        builder.append("]");
        return builder.toString();
    }


}
