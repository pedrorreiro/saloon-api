package com.app.saloon.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.saloon.Dtos.AgendamentoDto;
import com.app.saloon.InputDto.AgendamentoInputDto;
import com.app.saloon.Service.AgendamentoService;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    @Autowired
    AgendamentoService agendamentoService;

    @GetMapping
    public List<AgendamentoDto> listAgendamentos() {
        return agendamentoService.getAll();
    }

    @GetMapping("/{id}")
    public AgendamentoDto findById(@PathVariable int id) {
        return agendamentoService.findById(id);
    }

    @GetMapping("/saloon/{saloonId}")
    public List<AgendamentoDto> findBySaloon(@PathVariable int saloonId) {
        return agendamentoService.findBySaloon(saloonId);
    }

    @PostMapping()
    public AgendamentoDto save(@RequestBody AgendamentoInputDto agendamento) {
        return agendamentoService.save(agendamento);
    }

    // @DeleteMapping("/{id}")
    // public void delete(@PathVariable int id) {
    // agendamentoService.delete(id);
    // }

}
