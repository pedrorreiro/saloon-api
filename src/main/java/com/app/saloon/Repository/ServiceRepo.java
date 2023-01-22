package com.app.saloon.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.saloon.Model.Service;

public interface ServiceRepo extends JpaRepository<Service, Integer> {

    List<Service> findAllByActiveTrue();

    Optional<Service> findByIdAndActiveTrue(long id);

    List<Service> findBySaloon_idAndActiveTrue(int saloon_id);

    List<Service> findByFunctionaries_idAndActiveTrue(int functionary_id);
}
