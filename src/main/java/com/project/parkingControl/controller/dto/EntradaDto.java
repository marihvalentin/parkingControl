package com.project.parkingControl.controller.dto;

import com.project.parkingControl.model.Estacionamento;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class EntradaDto
{
    private Integer idReserva;
    private String placaVeiculo;
    private LocalDateTime entrada;
    /*private boolean pagamento;
    private boolean saida;*/

    public EntradaDto(Estacionamento estacionamento)
    {
        this.idReserva = estacionamento.getIdReserva();
        this.placaVeiculo = estacionamento.getPlacaVeiculo();
        this.entrada = estacionamento.getEntrada();
        /*this.pagamento = estacionamento.getPagamento();
        this.saida = estacionamento.getSaida();*/
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

    /*public boolean isPagamento() {
        return pagamento;
    }

    public boolean isSaida() {
        return saida;
    }*/

    public static List<EntradaDto> converter(List<Estacionamento> estacionamentos) {
        return estacionamentos.stream().map(EntradaDto::new).collect(Collectors.toList());
    }

}
