package com.kiotfpt.Enum;

public enum Role {
    CUSTOMER("Customer"),
    SELLER("Seller"),
    ADMIN("Admin");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
