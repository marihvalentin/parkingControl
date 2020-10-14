package com.project.parkingControl.service;

import com.project.parkingControl.dto.ConsultaHistoricoDto;
import com.project.parkingControl.form.EntradaForm;
import com.project.parkingControl.model.Estacionamento;
import com.project.parkingControl.repository.EstacionamentoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EstacionamentoService {

    @Autowired
    private EstacionamentoRepository estacionamentoRepository;
    private static Logger log = LoggerFactory.getLogger(EstacionamentoService.class);

    public Boolean estacionar(EntradaForm form) {

        Estacionamento estacionamento = new Estacionamento();

        if(this.isPlacaValida(form.getPlacaVeiculo())){
            estacionamento.setEntrada(LocalDateTime.now());
            estacionamento.setPlacaVeiculo(form.getPlacaVeiculo());
            estacionamentoRepository.save(estacionamento);
            return true;
        }else {
            log.error("Placa Inválida!!");
            return false;
        }
    }
    public ConsultaHistoricoDto getHistoricoVeiculo(String placa){
        ConsultaHistoricoDto consultaHistoricoDto = new ConsultaHistoricoDto();
        if (this.isPlacaValida(placa)){
            Estacionamento estacionamento = estacionamentoRepository.findByPlacaVeiculo(placa);
            if (estacionamento != null) {
                consultaHistoricoDto.build(estacionamento);
            }
        }
        return consultaHistoricoDto;
    }


    public Boolean pagar(Integer idReserva) {
        Estacionamento estacionamento = estacionamentoRepository.findByIdReserva(idReserva);

        if (estacionamento != null) {
            estacionamento.setPagamento(true);
            estacionamentoRepository.save(estacionamento);
            return true;
        } else {
            log.error("idReserva " + idReserva + " inválido");
            return false;
        }
    }

    public Boolean sair(Integer idReserva) {
        Estacionamento estacionamento = estacionamentoRepository.findByIdReserva(idReserva);
        Boolean saidaPermitida = false;

        if (estacionamento != null && estacionamento.getPagamento()) {
            estacionamento.setSaida(true);
            estacionamento.setMomentoSaida(LocalDateTime.now());
            estacionamento.setTempoPermanencia(this.calculaPermanencia(estacionamento.getEntrada(),
                    estacionamento.getMomentoSaida()));
            estacionamentoRepository.save(estacionamento);
            saidaPermitida = true;
        } else if (estacionamento == null) {
            log.error("idReserva " + idReserva + " inválido");
        } else if (!estacionamento.getPagamento()) {
            log.info("Pagamento não realizado!!!");
        }

        return saidaPermitida;
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

    private Duration calculaPermanencia(LocalDateTime entrada, LocalDateTime atual){
        return Duration.between(entrada, atual);
    }
}
