package com.example.copsbot.user;

import lombok.Value;

import java.util.Set;
import java.util.UUID;

@Value
public class UserDto {

    private UUID id; // Deliberately return a simple id here, not a UserId
    private String email;
    private Set<UserRole> roles;
    // Deliberately don't add the password in here as we don't want to return it in any requests.

    public UserDto(User user) {
        this.email = user.getEmail();
        this.roles = user.getRoles();
        this.id  = user.getId().getId();
    }

}
