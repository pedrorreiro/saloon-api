package com.app.saloon.Dtos;

import java.util.Date;

import com.app.saloon.Model.Agendamento;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AgendamentoDto {

    public static AgendamentoDto from(Agendamento agendamento) {

        AgendamentoDto a = new AgendamentoDto();

        a.setId(agendamento.getId());
        a.setClientName(agendamento.getClientName());
        a.setClientPhone(agendamento.getClientPhone());
        a.setSaloon(SaloonDto.from(agendamento.getSaloon()));
        a.setFunctionary(FunctionaryDto.from(agendamento.getFunctionary()));
        a.setService(ServiceDto.from(agendamento.getService()));
        a.setDate(agendamento.getDate());

        return a;
    }

    @JsonProperty("key")
    private long id;
    private String clientName;
    private String clientPhone;
    private SaloonDto saloon;
    private FunctionaryDto functionary;
    private ServiceDto service;
    private Date date;
}
