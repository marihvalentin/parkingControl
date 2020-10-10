package com.project.parkingControl.repository;

import com.project.parkingControl.model.Proprietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProprietarioRepository extends JpaRepository<Proprietario, Integer>
{
    Proprietario findByCpf(String cpf);
}
