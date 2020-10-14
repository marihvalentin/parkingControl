package com.project.parkingControl.dto;

import com.project.parkingControl.model.Estacionamento;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class EstacionamentoDto
{
    private Integer idReserva;
    private String placaVeiculo;
    private LocalDateTime entrada;

    public EstacionamentoDto(Estacionamento estacionamento)
    {
        this.idReserva = estacionamento.getIdReserva();
        this.placaVeiculo = estacionamento.getPlacaVeiculo();
        this.entrada = estacionamento.getEntrada();
    }

    public EstacionamentoDto() {

    }

    public Integer getIdReserva() {
        return idReserva;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public LocalDateTime getEntrada() {
        return entrada;
    }


    public static List<EstacionamentoDto> converter(List<Estacionamento> estacionamentos) {
        return estacionamentos.stream().map(EstacionamentoDto::new).collect(Collectors.toList());
    }

}
