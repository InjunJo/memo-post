package com.example.memo.entity;

public enum UserRole {

    ADMIN("admin"), USER("user");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getValue(){

        return this.role;
    }


    // consider : 스트링으로 받아서 Enum 타입으로 변환 해줍니다
    public static UserRole fromStrToUserRole(String role){

        for(UserRole userRole : UserRole.values()){

            if(role.equals(userRole.getValue())){

                return userRole;
            }
        }

        return null;
    }
}
