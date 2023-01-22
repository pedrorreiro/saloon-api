package com.app.saloon.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.saloon.Dtos.FunctionaryDto;
import com.app.saloon.InputDto.FunctionaryInputDto;
import com.app.saloon.Model.Functionary;
import com.app.saloon.Model.Saloon;
import com.app.saloon.Model.User;
import com.app.saloon.Repository.FunctionaryRepo;
import com.app.saloon.Repository.SaloonRepo;
import com.app.saloon.Repository.ServiceRepo;
import com.app.saloon.Repository.UserRepo;

@Service
public class FunctionaryService {

    @Autowired
    FunctionaryRepo functionaryRepo;

    @Autowired
    ServiceRepo serviceRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    SaloonRepo saloonRepo;

    public List<FunctionaryDto> getAll() {

        List<Functionary> funcionaries = functionaryRepo.findAllByActiveTrue();

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

        User user = null;
        Functionary f = null;

        if (functionary.getId() == 0) {
            // novo funcionario

            f = new Functionary();

            if (functionary.getUserId() != 0) { // usuario é zero se não for atribuir um usuário
                user = userRepo.findById(functionary.getUserId())
                        .orElseThrow(() -> new Error("Usuário de ID " + functionary.getUserId() + "não encontrado"));
            }
        }

        else {
            // atualizando funcionario

            f = functionaryRepo.findById(functionary.getId())
                    .orElseThrow(() -> new Error("Funcionário de ID " + functionary.getId() + " não encontrado"));

            f.setId(functionary.getId());
        }

        Saloon saloon = saloonRepo.findById(functionary.getSaloonId())
                .orElseThrow(() -> new Error("Salão de ID " + functionary.getSaloonId() + " não encontrado"));

        List<com.app.saloon.Model.Service> services = new ArrayList<>();

        for (int i = 0; i < functionary.getServicesId().length; i++) {
            int id = (functionary.getServicesId()[i]);
            com.app.saloon.Model.Service service = serviceRepo.findById(id)
                    .orElseThrow(() -> new Error("Serviço de ID " + id + " não encontrado"));

            // remove o funcionario do serviço

            services.add(service);

            Set<Functionary> functionaries = service.getFunctionaries();
            List<Functionary> list = new ArrayList<>(functionaries);
            list.stream().filter(k -> k.getId() != functionary.getId()).collect(Collectors.toList());
            service.setFunctionaries(functionaries);
            serviceRepo.save(service);
        }

        f.setName(functionary.getName());
        f.setSaloon(saloon);
        f.setActive(true);
        f.setServices(new HashSet<>(services));

        Functionary funcionarioModel = functionaryRepo.save(f);

        return FunctionaryDto.from(funcionarioModel);

    }

    public FunctionaryDto delete(int id) {

        Functionary f = functionaryRepo.findById(id)
                .orElseThrow(() -> new Error("Funcionário de ID " + id + " não encontrado"));

        f.setActive(false);

        Functionary funcionarioModel = functionaryRepo.save(f);

        return FunctionaryDto.from(funcionarioModel);
    }

    public FunctionaryDto restore(int id) {

        Functionary f = functionaryRepo.findById(id)
                .orElseThrow(() -> new Error("Funcionário de ID " + id + " não encontrado"));

        f.setActive(true);

        Functionary funcionarioModel = functionaryRepo.save(f);

        return FunctionaryDto.from(funcionarioModel);
    }

    public FunctionaryDto findByUser(int user_id) {

        Functionary f = functionaryRepo.findByUser_idAndActiveTrue(user_id).orElse(null);

        if (f == null) {
            return null;
        }

        return FunctionaryDto.from(f);
    }
}
