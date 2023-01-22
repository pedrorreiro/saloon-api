package com.app.saloon.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.saloon.Model.Client;

public interface ClientRepo extends JpaRepository<Client, Integer> {

    Optional<Client> findById(Long id);
}
