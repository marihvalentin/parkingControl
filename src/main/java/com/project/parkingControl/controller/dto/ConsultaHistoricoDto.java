package com.project.parkingControl.controller.dto;

import com.project.parkingControl.model.Estacionamento;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ConsultaHistoricoDto
{
    private Integer idReserva;
    private LocalDateTime tempoPermanencia;
    private boolean pago;
    private boolean saiu;

    public ConsultaHistoricoDto(Estacionamento estacionamento)
    {
        this.idReserva = estacionamento.getIdReserva();
        this.tempoPermanencia = estacionamento.getTempoPermanencia();
        this.pago = estacionamento.getPagamento();
        this.saiu = estacionamento.getSaida();
    }

    public static List<EntradaDto> converter(List<Estacionamento> estacionamentos)
    {
        return estacionamentos.stream().map(EntradaDto::new).collect(Collectors.toList());
    }

    public int getId() {
        return idReserva;
    }

    public void setId(int id) {
        this.idReserva = idReserva;
    }

    public LocalDateTime getTempoPermanencia() {
        return tempoPermanencia;
    }

    public void setTempoPermanencia(LocalDateTime tempoPermanencia) {
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
