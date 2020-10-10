package com.project.parkingControl.model;

import javax.persistence.*;

public class Veiculo
{
    @Id
    @Column(unique=true)
    private String placa;
    @Column
    private String modelo;
    @Column
    private String marca;
    @Column
    private String cor;
    @ManyToOne
    @JoinColumn(name = "proprietario_fk")
    private Proprietario proprietario;

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Proprietario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
    }
}
