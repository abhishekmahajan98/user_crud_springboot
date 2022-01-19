package com.example.user_crud_springboot.dto;

import javax.validation.constraints.NotNull;

public class PostInputDTO {

    @NotNull(message = "Description cannot be null")
    private String description;

    public PostInputDTO() {
    }

    public PostInputDTO(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
