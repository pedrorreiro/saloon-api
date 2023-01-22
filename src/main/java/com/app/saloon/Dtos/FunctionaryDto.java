package com.app.saloon.Dtos;

import com.app.saloon.Model.Functionary;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class FunctionaryDto {

    public static FunctionaryDto from(Functionary functionary) {

        FunctionaryDto f = new FunctionaryDto();

        f.setId(functionary.getId());
        f.setSaloonId(functionary.getSaloon().getId());
        f.setName(functionary.getName());

        UserDto u = new UserDto();

        if (functionary.getUser() != null) {
            u.setId(functionary.getUser().getId());
            u.setEmail(functionary.getUser().getEmail());
            u.setActive(functionary.getUser().getActive());

        }

        f.setUser(u);

        f.setActive(functionary.getActive());

        return f;
    }

    @JsonProperty("key")
    private long id;
    private String name;
    private UserDto user;
    private long saloonId;
    private boolean active;

}
