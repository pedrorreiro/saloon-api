package com.app.saloon.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.saloon.Dtos.ServiceDto;
import com.app.saloon.InputDto.ServiceInputDto;
import com.app.saloon.Model.Functionary;
import com.app.saloon.Model.Saloon;
import com.app.saloon.Repository.FunctionaryRepo;
import com.app.saloon.Repository.SaloonRepo;
import com.app.saloon.Repository.ServiceRepo;

@Service
public class ServiceService {

    @Autowired
    ServiceRepo serviceRepo;

    @Autowired
    SaloonRepo saloonRepo;

    @Autowired
    FunctionaryRepo functionaryRepo;

    public List<ServiceDto> getAll() {

        List<com.app.saloon.Model.Service> services = serviceRepo.findAllByActiveTrue();

        List<ServiceDto> serviceDtos = services.stream().map(service -> {
            return ServiceDto.from(service);
        }).collect(Collectors.toList());

        return serviceDtos;
    }

    public ServiceDto findByIdAndActiveTrue(int id) {

        com.app.saloon.Model.Service service = serviceRepo.findByIdAndActiveTrue(id).orElse(null);

        if (service == null) {
            return null;
        }

        return ServiceDto.from(service);
    }

    public List<ServiceDto> findBySaloon(int id) {

        List<com.app.saloon.Model.Service> services = serviceRepo.findBySaloon_idAndActiveTrue(id);

        List<ServiceDto> serviceDtos = services.stream().map(service -> {
            return ServiceDto.from(service);
        }).collect(Collectors.toList());

        return serviceDtos;

    }

    public List<ServiceDto> findByFunctionary(int id) {

        List<com.app.saloon.Model.Service> services = serviceRepo.findByFunctionaries_idAndActiveTrue(id);

        List<ServiceDto> serviceDtos = services.stream().map(service -> {
            return ServiceDto.from(service);
        }).collect(Collectors.toList());

        return serviceDtos;

    }

    public ServiceDto save(ServiceInputDto serviceDto) {

        com.app.saloon.Model.Service service = new com.app.saloon.Model.Service();

        Saloon saloon = saloonRepo.findById(serviceDto.getSaloonId())
                .orElseThrow(() -> new Error(
                        "Salão de ID " + serviceDto.getSaloonId() + " não encontrado na base de dados"));

        BeanUtils.copyProperties(serviceDto, service);

        List<Functionary> functionaries = new ArrayList<>();

        for (int i = 0; i < serviceDto.getFunctionariesId().length; i++) {
            int id = serviceDto.getFunctionariesId()[i];

            Functionary functionary = functionaryRepo.findById(id)
                    .orElseThrow(() -> new Error(
                            "Funcionário de ID " + id + " não encontrado na base de dados"));

            functionaries.add(functionary);
        }

        service.setSaloon(saloon);

        Set<com.app.saloon.Model.Functionary> set = new HashSet<>(functionaries);

        service.setFunctionaries(set);

        service.setActive(true);

        serviceRepo.save(service);

        ServiceDto dto = new ServiceDto();

        dto.setId(service.getId());
        dto.setName(service.getName());
        dto.setPrice(service.getPrice());
        dto.setDuration(service.getDuration());

        dto.setSaloonId(service.getSaloon().getId());
        dto.setFunctionaries(null);
        dto.setActive(service.getActive());

        return dto;
    }
}
