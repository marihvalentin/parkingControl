package com.project.parkingControl.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Duration;
import java.time.LocalDateTime;

@Entity(name = "estacionamento")
public class Estacionamento
{
    //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReserva;
    private String placaVeiculo;
    private LocalDateTime entrada;
    private Boolean saida = false;
    private LocalDateTime momentoSaida;
    private Duration tempoPermanencia;
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

    public Duration getTempoPermanencia() {
        return tempoPermanencia;
    }

    public void setTempoPermanencia(Duration tempoPermanencia) {
        this.tempoPermanencia = tempoPermanencia;
    }

    public Boolean getPagamento() {
        return pagamento;
    }

    public void setPagamento(Boolean pagamento) {
        this.pagamento = pagamento;
    }

    public LocalDateTime getMomentoSaida() {
        return momentoSaida;
    }

    public void setMomentoSaida(LocalDateTime momentoSaida) {
        this.momentoSaida = momentoSaida;
    }
}
