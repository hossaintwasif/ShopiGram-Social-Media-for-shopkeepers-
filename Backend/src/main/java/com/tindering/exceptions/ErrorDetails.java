package com.tindering.exceptions;

import java.time.LocalDateTime;

public class ErrorDetails {

    private String message;
    private String error;
    private LocalDateTime timeStamp;

    public ErrorDetails(String message, String error, LocalDateTime timeStamp) {
        this.message = message;
        this.error = error;
        this.timeStamp = timeStamp;
    }

    public ErrorDetails() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "ErrorDetails{" +
                "message='" + message + '\'' +
                ", error='" + error + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
