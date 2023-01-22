package com.app.saloon.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.saloon.Model.Functionary;

public interface FunctionaryRepo extends JpaRepository<Functionary, Integer> {

    Optional<Functionary> findByIdAndActiveTrue(long id);

    Optional<Functionary> findByUser_idAndActiveTrue(int user_id);

    List<Functionary> findBySaloon_idAndActiveTrue(int saloon_id);

    List<Functionary> findAllByActiveTrue();
}
