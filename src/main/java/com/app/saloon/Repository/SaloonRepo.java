package com.app.saloon.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.saloon.Model.Saloon;

public interface SaloonRepo extends JpaRepository<Saloon, Integer>{

    List<Saloon> findAllByActiveTrue();

    Optional<Saloon> findByOwner_idAndActiveTrue(int id);

    Optional<Saloon> findByIdAndActiveTrue(int id);
}
