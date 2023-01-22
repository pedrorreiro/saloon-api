package com.app.saloon.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.saloon.Dtos.AgendamentoDto;
import com.app.saloon.InputDto.AgendamentoInputDto;
import com.app.saloon.Model.Agendamento;
import com.app.saloon.Model.Functionary;
import com.app.saloon.Model.Saloon;
import com.app.saloon.Repository.AgendamentoRepo;
import com.app.saloon.Repository.ClientRepo;
import com.app.saloon.Repository.FunctionaryRepo;
import com.app.saloon.Repository.SaloonRepo;
import com.app.saloon.Repository.ServiceRepo;

@Service
public class AgendamentoService {

    @Autowired
    AgendamentoRepo agendamentoRepo;

    @Autowired
    SaloonRepo saloonRepo;

    @Autowired
    ClientRepo clientRepo;

    @Autowired
    FunctionaryRepo functionaryRepo;

    @Autowired
    ServiceRepo serviceRepo;

    public List<AgendamentoDto> getAll() {

        List<Agendamento> agendamentos = agendamentoRepo.findAllByActiveTrue();

        List<AgendamentoDto> agendamentoDtos = agendamentos.stream().map(agendamento -> {
            AgendamentoDto ag = AgendamentoDto.from(agendamento);
            return ag;

        }).collect(Collectors.toList());

        return agendamentoDtos;
    }

    public AgendamentoDto findById(int id) {

        Agendamento a = agendamentoRepo.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new Error("Agendamento de ID " + id + " não encontrado na base de dados"));

        return AgendamentoDto.from(a);
    }

    public List<AgendamentoDto> findBySaloon(int saloon_id) {

        List<Agendamento> agendamentos = agendamentoRepo.findBySaloon_idAndActiveTrue(saloon_id);

        List<AgendamentoDto> agendamentoDtos = agendamentos.stream().map(agendamento -> {
            AgendamentoDto ag = AgendamentoDto.from(agendamento);
            return ag;

        }).collect(Collectors.toList());

        return agendamentoDtos;
    }

    public AgendamentoDto save(AgendamentoInputDto agendamento) {

        Agendamento a = null;
        Saloon saloon = null;
        Functionary functionary = null;
        com.app.saloon.Model.Service service = null;

        // novo agendamento

        a = new Agendamento();

        if (agendamento.getSaloonId() != 0) {
            saloon = saloonRepo.findByIdAndActiveTrue(agendamento.getSaloonId())
                    .orElseThrow(() -> new Error("Salão de ID " + agendamento.getSaloonId() + "não encontrado"));
        }

        if (agendamento.getFunctionaryId() != 0) {
            functionary = functionaryRepo.findByIdAndActiveTrue(agendamento.getFunctionaryId())
                    .orElseThrow(
                            () -> new Error("Funcionário de ID " + agendamento.getFunctionaryId() + "não encontrado"));
        }

        if (agendamento.getServiceId() != 0) {
            service = serviceRepo.findByIdAndActiveTrue(agendamento.getServiceId())
                    .orElseThrow(() -> new Error("Serviço de ID " + agendamento.getServiceId() + "não encontrado"));
        }

        a.setClientName(agendamento.getClientName());
        a.setClientPhone(agendamento.getClientPhone());
        a.setSaloon(saloon);
        a.setFunctionary(functionary);
        a.setService(service);
        a.setActive(true);
        a.setDone(false);
        a.setDate(agendamento.getDate());

        Agendamento agendamentoModel = agendamentoRepo.save(a);

        return AgendamentoDto.from(agendamentoModel);

    }

    // public FunctionaryDto delete(int id) {

    // Functionary f = functionaryRepo.findById(id)
    // .orElseThrow(() -> new Error("Funcionário de ID " + id + " não encontrado"));

    // f.setActive(false);

    // Functionary funcionarioModel = functionaryRepo.save(f);

    // return FunctionaryDto.from(funcionarioModel);
    // }

    // public FunctionaryDto restore(int id) {

    // Functionary f = functionaryRepo.findById(id)
    // .orElseThrow(() -> new Error("Funcionário de ID " + id + " não encontrado"));

    // f.setActive(true);

    // Functionary funcionarioModel = functionaryRepo.save(f);

    // return FunctionaryDto.from(funcionarioModel);
    // }

    // public FunctionaryDto findByUser(int user_id) {

    // Functionary f =
    // functionaryRepo.findByUser_idAndActiveTrue(user_id).orElse(null);

    // if (f == null) {
    // return null;
    // }

    // return FunctionaryDto.from(f);
    // }
}
