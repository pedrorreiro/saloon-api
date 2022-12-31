package com.app.saloon.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.saloon.Dtos.SaloonDto;
import com.app.saloon.InputDto.SaloonInputDto;
import com.app.saloon.Model.Saloon;
import com.app.saloon.Model.User;
import com.app.saloon.Repository.SaloonRepo;
import com.app.saloon.Repository.UserRepo;

@Service
public class SaloonService {

    @Autowired
    SaloonRepo saloonRepo;

    @Autowired
    UserRepo userRepo;

    public List<SaloonDto> getAll() {

        List<Saloon> saloons = saloonRepo.findAllByActiveTrue();

        List<SaloonDto> saloonDtos = saloons.stream().map(saloon -> {
            return SaloonDto.from(saloon);
        }).collect(Collectors.toList());

        return saloonDtos;
    }

    public SaloonDto findByOwner(int id) {

        Saloon saloon = saloonRepo.findByOwner_idAndActiveTrue(id).orElse(null);

        if (saloon == null) {
            return null;
        }

        return SaloonDto.from(saloon);
    }

    public SaloonDto findByIdAndActiveTrue(int id) {

        Saloon saloon = saloonRepo.findByIdAndActiveTrue(id).orElse(null);

        if (saloon == null) {
            return null;
        }

        return SaloonDto.from(saloon);
    }

    public SaloonDto save(SaloonInputDto saloon) {

        // converte dto em model

        Saloon newSaloon = new Saloon();

        User f = userRepo.findById(saloon.getOwnerId()).orElseThrow(
                () -> new Error("Usuário de ID " + saloon.getOwnerId() + " não encontrado na base de dados"));

        BeanUtils.copyProperties(saloon, newSaloon);

        newSaloon.setOwner(f);

        Saloon saloonSaved = saloonRepo.save(newSaloon);

        SaloonDto saloonToReturn = new SaloonDto();

        BeanUtils.copyProperties(saloonSaved, saloonToReturn);

        return saloonToReturn;
    }
}
