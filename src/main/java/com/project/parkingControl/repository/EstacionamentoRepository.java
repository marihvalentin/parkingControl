package com.project.parkingControl.repository;

import com.project.parkingControl.model.Estacionamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EstacionamentoRepository extends JpaRepository<Estacionamento, Integer>
{
    Optional<Estacionamento> findByIdReserva(Integer idReserva);

    Optional<Estacionamento> findByPlacaVeiculo(String placaVeiculo);

}
