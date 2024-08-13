package com.behoh.challenge.web.exception;

public enum ErrorEnum {

    BAD_REQUEST("Bad Request"),
    NOT_FOUND("Not Found"),
    INTERNAL_SERVER_ERROR("Internal Server Error");

    private final String error;

    ErrorEnum(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return this.error;
    }

}
