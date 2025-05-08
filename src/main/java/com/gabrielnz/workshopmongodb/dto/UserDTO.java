package com.gabrielnz.workshopmongodb.dto;

import com.gabrielnz.workshopmongodb.domain.User;

import java.io.Serializable;

public class UserDTO implements Serializable {
    private String id;
    private String name;
    private String password;
    private String email;

    public UserDTO() {

    }
    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.password = user.getPassword();
        this.email = user.getEmail();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
