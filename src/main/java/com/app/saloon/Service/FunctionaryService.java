package com.app.saloon.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.saloon.Dtos.FunctionaryDto;
import com.app.saloon.InputDto.FunctionaryInputDto;
import com.app.saloon.Model.Functionary;
import com.app.saloon.Model.Saloon;
import com.app.saloon.Model.User;
import com.app.saloon.Repository.FunctionaryRepo;
import com.app.saloon.Repository.SaloonRepo;
import com.app.saloon.Repository.UserRepo;

@Service
public class FunctionaryService {

    @Autowired
    FunctionaryRepo functionaryRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    SaloonRepo saloonRepo;

    public List<FunctionaryDto> getAll() {

        List<Functionary> funcionaries = functionaryRepo.findAll();

        List<FunctionaryDto> funcDtos = funcionaries.stream().map(user -> {
            FunctionaryDto f = FunctionaryDto.from(user);
            return f;

        }).collect(Collectors.toList());

        return funcDtos;
    }

    public FunctionaryDto findById(int id) {

        Functionary f = functionaryRepo.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new Error("Funcionário de ID " + id + " não encontrado na base de dados"));

        return FunctionaryDto.from(f);
    }

    public List<FunctionaryDto> findBySaloon(int saloon_id) {

        List<Functionary> funcionaries = functionaryRepo.findBySaloon_idAndActiveTrue(saloon_id);

        List<FunctionaryDto> funcDtos = funcionaries.stream().map(user -> {
            FunctionaryDto f = FunctionaryDto.from(user);
            return f;

        }).collect(Collectors.toList());

        return funcDtos;
    }

    public FunctionaryDto save(FunctionaryInputDto functionary) {

        User user = userRepo.findById(functionary.getUserId())
                .orElseThrow(() -> new Error("Usuário de ID " + functionary.getUserId() + "não encontrado"));
        Saloon saloon = saloonRepo.findById(functionary.getSaloonId())
                .orElseThrow(() -> new Error("Salão de ID " + functionary.getSaloonId() + " não encontrado"));

        Functionary newFunctionary = new Functionary();

        newFunctionary.setUser(user);
        newFunctionary.setSaloon(saloon);

        BeanUtils.copyProperties(functionary, newFunctionary);
        newFunctionary.setActive(true);

        Functionary f = functionaryRepo.save(newFunctionary);

        return FunctionaryDto.from(f);

    }

    public FunctionaryDto findByUser(int user_id) {

        Functionary f = functionaryRepo.findByUser_idAndActiveTrue(user_id).orElse(null);

        if (f == null) {
            return null;
        }

        return FunctionaryDto.from(f);
    }
}
