package com.example.memo.entity;

public enum UserRole {

    ADMIN("ROLE_ADMIN"), USER("ROLE_USER");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getValue(){

        return this.role;
    }

    public static UserRole ToUserRole(String role){

        for(UserRole userRole : UserRole.values()){

            if(role.equals(userRole.getValue())){

                return userRole;
            }
        }

        return null;
    }
}
