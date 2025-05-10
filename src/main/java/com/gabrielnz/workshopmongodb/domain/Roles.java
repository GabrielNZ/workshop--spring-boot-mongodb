package com.gabrielnz.workshopmongodb.domain;

public enum Roles {
    USER("user"),
    ADMIN("admin");

    private String role;

    Roles(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
