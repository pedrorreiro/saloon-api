package com.app.saloon.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.saloon.Dtos.UserDto;
import com.app.saloon.Model.User;
import com.app.saloon.Repository.UserRepo;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public List<UserDto> getAll() {
        System.out.println("Getting all products");

        List<User> users = userRepo.findAll();

        List<UserDto> userDtos = users.stream().map(user -> {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(user, userDto);
            return userDto;
        }).collect(Collectors.toList());

        return userDtos;
    }

    public UserDto userExistsByEmail(String email) {

        User u = userRepo.findByEmailAndActiveTrue(email)
                .orElseThrow(() -> new Error("Usuário não encontrado na base de dados"));

        UserDto userDto = new UserDto();

        BeanUtils.copyProperties(u, userDto);

        return userDto;
    }

    public UserDto userExistsById(int id) {

        User u = userRepo.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new Error("Usuário de ID " + id + " não encontrado na base de dados"));

        UserDto userDto = new UserDto();

        BeanUtils.copyProperties(u, userDto);

        return userDto;

    }

    public User save(UserDto user) {

        // converte dto em model

        userRepo.findByEmailAndActiveTrue(user.getEmail()).ifPresent(u -> {
            throw new Error("Usuário já cadastrado na base de dados");
        });

        User newUser = new User();

        BeanUtils.copyProperties(user, newUser);
        newUser.setActive(true);

        User UserSaved = userRepo.save(newUser);

        // UserDto dto = new UserDto();

        // BeanUtils.copyProperties(UserSaved, dto);

        // return UserToReturn;

        return UserSaved;
    }
}
