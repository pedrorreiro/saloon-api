package com.app.saloon.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.saloon.Model.Agendamento;

public interface AgendamentoRepo extends JpaRepository<Agendamento, Integer> {

    List<Agendamento> findAllByActiveTrue();

    Optional<Agendamento> findByIdAndActiveTrue(int id);

    List<Agendamento> findBySaloon_idAndActiveTrue(int saloon_id);
}
