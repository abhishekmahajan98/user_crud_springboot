package com.example.user_crud_springboot.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @Size(min = 2,message = "First name length should be minimum 2.")
    private String firstName;

    @Size(min = 2,message = "Last name length should be minimum 2.")
    private String lastName;

    @Size(min = 4,message = "Username length should be minimum 4.")
    @Column(unique = true)
    private String username;

    @Size(min = 4,message = "Password length should be minimum 4.")
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;


    public User( String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + username + '\'' +
                ", password='" + password + '\'' +
                ", posts=" + posts +
                '}';
    }
}
