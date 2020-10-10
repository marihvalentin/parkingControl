package com.project.parkingControl.repository;

import com.project.parkingControl.model.Estacionamento;
import com.project.parkingControl.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstacionamentoRepository extends JpaRepository<Estacionamento, Integer>
{
    Estacionamento findById(int id);

    Optional<Estacionamento> findByPlaca(Veiculo placa);
}
