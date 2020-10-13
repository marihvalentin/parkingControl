package com.project.parkingControl.service;

import com.project.parkingControl.dto.SaidaDto;
import com.project.parkingControl.form.SaidaForm;
import com.project.parkingControl.model.Estacionamento;
import com.project.parkingControl.repository.EstacionamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstacionamentoService {

    @Autowired
    private EstacionamentoRepository estacionamentoRepository;

    public Boolean sair(Integer idReserva){
        Estacionamento estacionamento  = estacionamentoRepository.findByIdReserva(idReserva);

        if(estacionamento.getPagamento()){
            estacionamento.setSaida(true);
            estacionamentoRepository.save(estacionamento);
            return true;
        }else{
            return false;
        }

    }
}
