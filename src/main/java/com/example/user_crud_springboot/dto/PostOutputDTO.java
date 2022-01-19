package com.example.user_crud_springboot.dto;

import javax.validation.constraints.NotNull;

public class PostOutputDTO {
    private Integer id;
    private String description;

    public PostOutputDTO() {
    }

    public PostOutputDTO(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
