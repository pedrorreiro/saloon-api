package com.app.saloon.Dtos;

import com.app.saloon.Model.Functionary;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class FunctionaryDto {

    public static FunctionaryDto from(Functionary functionary) {

        FunctionaryDto f = new FunctionaryDto();

        f.setId(functionary.getId());
        f.setSaloon(SaloonDto.from(functionary.getSaloon()));

        UserDto u = new UserDto();

        u.setId(functionary.getUser().getId());
        u.setEmail(functionary.getUser().getEmail());
        u.setName(functionary.getUser().getName());
        u.setActive(functionary.getUser().getActive());

        f.setUser(u);

        f.setActive(functionary.getActive());

        return f;
    }

    @JsonProperty("key")
    private long id;
    private UserDto user;
    private SaloonDto saloon;
    private boolean active;

}
