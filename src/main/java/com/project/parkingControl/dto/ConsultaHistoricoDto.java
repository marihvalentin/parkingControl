package com.project.parkingControl.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.parkingControl.model.Estacionamento;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@JsonIgnoreProperties
public class ConsultaHistoricoDto
{
    private Integer id;
    private Duration tempoPermanencia;
    private boolean pago;
    private boolean saiu;

    public ConsultaHistoricoDto(Estacionamento estacionamento) {
        this.id = estacionamento.getIdReserva();
        if(estacionamento.getMomentoSaida() != null)
        {
            this.tempoPermanencia = Duration.between(estacionamento.getEntrada(), estacionamento.getMomentoSaida());
        }
        else
        {
            LocalDateTime atual = LocalDateTime.now();
            this.tempoPermanencia = Duration.between(atual, estacionamento.getEntrada());
        }
        this.pago = estacionamento.getPagamento();
        this.saiu = estacionamento.getSaida();
    }

    public static List<EstacionamentoDto> converter(List<Estacionamento> estacionamentos)
    {
        return estacionamentos.stream().map(EstacionamentoDto::new).collect(Collectors.toList());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = this.id;
    }

    public Duration getTempoPermanencia() {
        return tempoPermanencia;
    }

    public void setTempoPermanencia(Duration tempoPermanencia) {
        this.tempoPermanencia = tempoPermanencia;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public boolean isSaiu() {
        return saiu;
    }

    public void setSaiu(boolean saiu) {
        this.saiu = saiu;
    }

}
