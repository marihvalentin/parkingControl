package com.project.parkingControl.controller.dto;

import com.project.parkingControl.model.Estacionamento;


public class PagamentoDto
{
    private Integer idReserva;
    private Boolean pagamento;

    public PagamentoDto(Estacionamento estacionamento)
    {
        this.idReserva = estacionamento.getIdReserva();
        this.pagamento = estacionamento.getPagamento();
    }

    public Boolean getPagamento() {
        return pagamento;
    }
}
