package com.app.saloon.Dtos;

import java.util.List;

import com.app.saloon.Model.Service;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ServiceDto {

    public static ServiceDto from(Service service) {

        ServiceDto s = new ServiceDto();

        s.setId(service.getId());
        s.setName(service.getName());
        s.setPrice(service.getPrice());
        s.setDuration(service.getDuration());
        s.setComission(service.getComission());
        s.setSaloonId(service.getSaloon().getId());
        s.setFunctionaries(service.getFunctionaries().stream().map(FunctionaryDto::from).toList());
        s.setActive(service.getActive());

        return s;
    }

    @JsonProperty("key")
    private long id;
    private String name;
    private double price;
    private int duration;
    private double comission;
    private long saloonId;
    private List<FunctionaryDto> functionaries;
    private boolean active;

}
