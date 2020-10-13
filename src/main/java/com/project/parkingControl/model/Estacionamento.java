package com.project.parkingControl.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "estacionamento")
public class Estacionamento
{
    //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReserva;
    private String placaVeiculo;
    private LocalDateTime entrada = LocalDateTime.now();
    private Boolean saida = false;
    //private LocalDateTime momentoSaida;
    private LocalDateTime tempoPermanencia = LocalDateTime.now().plusMinutes(25);
    private Boolean pagamento = false;

    public Estacionamento()
    {
        //construtor default
    }

    //construtor entrada
    public Estacionamento(String placaVeiculo)
    {
        this.placaVeiculo = placaVeiculo;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public Integer getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

    public LocalDateTime getEntrada() {
        return entrada;
    }

    public void setEntrada(LocalDateTime entrada) {
        this.entrada = entrada;
    }

    public Boolean getSaida() {
        return saida;
    }

    public void setSaida(Boolean saida) {
        this.saida = saida;
    }

    public LocalDateTime getTempoPermanencia() {
        return tempoPermanencia;
    }

    public void setTempoPermanencia(LocalDateTime tempoPermanencia) {
        this.tempoPermanencia = tempoPermanencia;
    }

    /*public LocalDateTime getMomentoSaida() {
        return momentoSaida;
    }

    public void setMomentoSaida(LocalDateTime momentoSaida) {
        this.momentoSaida = momentoSaida;
    }*/

    public Boolean getPagamento() {
        return pagamento;
    }

    public void setPagamento(Boolean pagamento) {
        this.pagamento = pagamento;
    }



}
