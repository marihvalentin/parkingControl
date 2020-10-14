package com.project.parkingControl.service;


import com.project.parkingControl.dto.SaidaDto;
import com.project.parkingControl.form.SaidaForm;
import com.project.parkingControl.model.Estacionamento;
import com.project.parkingControl.repository.EstacionamentoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EstacionamentoService {

    @Autowired
    private EstacionamentoRepository estacionamentoRepository;
    private static Logger log = LoggerFactory.getLogger(EstacionamentoService.class);

    public SaidaDto sair(Integer idReserva, SaidaForm form) {
        Estacionamento estacionamento = estacionamentoRepository.findByIdReserva(idReserva);

        if (estacionamento != null && estacionamento.getPagamento())
        {
            estacionamento = form.editarEstacionamentoComSaida(idReserva, estacionamentoRepository);
        } else if (estacionamento == null) {
            log.error("idReserva " + idReserva + " inválido");
        } else if (!estacionamento.getPagamento()) {
            log.info("Pagamento não realizado!!!");
        }

        return new SaidaDto(estacionamento);
    }


    public Boolean isPlacaValida(String placaVeiculo) {
        boolean result = false;

        Pattern pattern = Pattern.compile("[A-Z]{3}-\\d{4}");
        Matcher mat = pattern.matcher(placaVeiculo);

        if (!mat.matches()) {
            result = false;
        }
        else {
            result = true;
        }
        return result;
    }

}
