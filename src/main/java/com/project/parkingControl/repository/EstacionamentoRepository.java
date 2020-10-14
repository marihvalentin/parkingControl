package com.project.parkingControl.repository;

import com.project.parkingControl.model.Estacionamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstacionamentoRepository extends JpaRepository<Estacionamento, Integer> {

    Estacionamento findByIdReserva(Integer idReserva);

    Estacionamento findByPlacaVeiculo(String placaVeiculo);

}
