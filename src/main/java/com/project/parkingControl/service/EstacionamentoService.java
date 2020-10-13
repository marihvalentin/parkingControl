package com.project.parkingControl.service;

import com.project.parkingControl.dto.SaidaDto;
import com.project.parkingControl.model.Estacionamento;
import com.project.parkingControl.repository.EstacionamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstacionamentoService
{
    @Autowired
    private EstacionamentoRepository estacionamentoRepository;
    public SaidaDto sair(Integer idReserva){

        Estacionamento estacionamento  = estacionamentoRepository.findByIdReserva(idReserva);
        SaidaDto saidaDto = new SaidaDto(estacionamento);
        return saidaDto;
    }

}
