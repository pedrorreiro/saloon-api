package com.app.saloon.Dtos;

import com.app.saloon.Model.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserDto {

    public static UserDto from(User user) {

        UserDto u = new UserDto();

        u.setId(user.getId());
        u.setName(user.getName());
        u.setEmail(user.getEmail());
        u.setActive(user.getActive());

        return u;
    }

    @JsonProperty("key")
    private long id;
    private String name;
    private String email;
    private boolean active;

}
