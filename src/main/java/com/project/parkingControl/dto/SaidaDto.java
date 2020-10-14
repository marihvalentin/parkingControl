package com.project.parkingControl.dto;

import com.project.parkingControl.model.Estacionamento;

import java.time.LocalDateTime;

public class SaidaDto
{
    private Integer idReserva;
    private Boolean saida;
    private LocalDateTime momentoSaida;
    private Boolean pagamento;

    public SaidaDto(Estacionamento estacionamento)
    {
        this.idReserva = estacionamento.getIdReserva();
        this.saida = estacionamento.getSaida();
        this.momentoSaida = estacionamento.getMomentoSaida();
    }


    public SaidaDto(SaidaDto estacionamento)
    {
        this.idReserva = estacionamento.getIdReserva();
        this.saida = estacionamento.getSaida();
        this.momentoSaida = estacionamento.getMomentoSaida();
    }


    public Boolean getSaida() {
        return saida;
    }

    public Integer getIdReserva() {
        return idReserva;
    }

    public LocalDateTime getMomentoSaida() {
        return momentoSaida;
    }

    public Boolean getPagamento(Estacionamento estacionamento) {
        return estacionamento.getPagamento();
    }
}