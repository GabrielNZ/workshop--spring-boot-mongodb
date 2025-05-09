package com.gabrielnz.workshopmongodb.dto;

import com.gabrielnz.workshopmongodb.domain.User;

public class AuthorDTO {
    private String id;
    private String name;

    public AuthorDTO() {

    }

    public AuthorDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }
}
