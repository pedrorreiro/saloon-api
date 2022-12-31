package com.app.saloon.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.saloon.Model.User;

public interface UserRepo extends JpaRepository<User, Integer>{

    Optional<User> findByEmailAndActiveTrue(String email);

    Optional<User> findByIdAndActiveTrue(int id);
}
