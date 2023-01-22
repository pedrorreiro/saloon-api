package com.app.saloon.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.saloon.Dtos.ServiceDto;
import com.app.saloon.InputDto.ServiceInputDto;
import com.app.saloon.Service.ServiceService;

@RestController
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    ServiceService serviceService;

    @GetMapping
    public List<ServiceDto> listFunctionaries() {
        return serviceService.getAll();
    }

    @GetMapping("/{id}")
    public ServiceDto findById(@PathVariable int id) {
        return serviceService.findByIdAndActiveTrue(id);
    }

    @GetMapping("/saloon/{saloonId}")
    public List<ServiceDto> findBySaloon(@PathVariable int saloonId) {
        return serviceService.findBySaloon(saloonId);
    }

    @GetMapping("/functionary/{functionaryId}")
    public List<ServiceDto> findByFunctionary(@PathVariable int functionaryId) {
        return serviceService.findByFunctionary(functionaryId);
    }

    @PostMapping()
    public ServiceDto save(@RequestBody ServiceInputDto functionary) {
        return serviceService.save(functionary);
    }
}
