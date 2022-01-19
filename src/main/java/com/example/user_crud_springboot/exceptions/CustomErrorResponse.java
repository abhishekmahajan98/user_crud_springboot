package com.example.user_crud_springboot.exceptions;

import java.util.Date;

public class CustomErrorResponse {
    private Date timestamp;
    private String error;
    private String description;

    public CustomErrorResponse(Date timestamp, String error, String description) {
        this.timestamp = timestamp;
        this.error = error;
        this.description = description;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getError() {
        return error;
    }

    public String getDescription() {
        return description;
    }
}
