package com.app.saloon.InputDto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AgendamentoInputDto {

    @JsonProperty("key")
    private long id;
    private String clientName;
    private String clientPhone;
    private long saloonId;
    private long functionaryId;
    private long serviceId;
    private Date date;
    private boolean active;

}
