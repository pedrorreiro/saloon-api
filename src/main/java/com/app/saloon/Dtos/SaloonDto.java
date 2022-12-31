package com.app.saloon.Dtos;

import com.app.saloon.Model.Saloon;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SaloonDto {

    public static SaloonDto from(Saloon saloon) {

        SaloonDto s = new SaloonDto();

        s.setId(saloon.getId());
        s.setName(saloon.getName());
        s.setOwner(UserDto.from(saloon.getOwner()));
        s.setActive(saloon.getActive());

        return s;
    }

    @JsonProperty("key")
    private long id;
    private String name;
    private UserDto owner;
    private boolean active;

}
