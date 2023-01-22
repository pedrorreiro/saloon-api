package com.app.saloon.Dtos;

import com.app.saloon.Model.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserDto {

    public static UserDto from(User user) {

        UserDto u = new UserDto();

        u.setId(user.getId());
        u.setEmail(user.getEmail());
        u.setActive(user.getActive());
        u.setMySaloonId(user.getSaloon().getId());

        return u;
    }

    @JsonProperty("key")
    private long id;
    private String email;
    private boolean active;
    private long mySaloonId;

}
