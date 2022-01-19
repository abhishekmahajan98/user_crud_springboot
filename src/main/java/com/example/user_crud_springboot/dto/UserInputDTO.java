package com.example.user_crud_springboot.dto;

import javax.validation.constraints.Size;

public class UserInputDTO {
    @Size(min = 2,message = "First name length should be minimum 2.")
    private String firstName;

    @Size(min = 2,message = "Last name length should be minimum 2.")
    private String lastName;

    @Size(min = 4,message = "Username length should be minimum 4.")
    private String username;

    @Size(min = 4,message = "Password length should be minimum 4.")
    private String password;

    public UserInputDTO() {
    }

    public UserInputDTO(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
