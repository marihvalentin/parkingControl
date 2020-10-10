package com.project.parkingControl.repository;

import com.project.parkingControl.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Integer>
{
    Veiculo findByPlaca(String placa);
}
