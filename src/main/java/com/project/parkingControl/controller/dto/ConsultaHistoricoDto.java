package com.project.parkingControl.controller.dto;

public class EstacionamentoDto
{
    private int id;
    private String tempoPermanencia;
    private boolean pago;
    private boolean saida;

    public EstacionamentoDto(int id, String tempoPermanencia, boolean pago, boolean saida)
    {
        this.id = id;
        this.tempoPermanencia = tempoPermanencia;
        this.pago = pago;
        this.saida = saida;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTempoPermanencia() {
        return tempoPermanencia;
    }

    public void setTempoPermanencia(String tempoPermanencia) {
        this.tempoPermanencia = tempoPermanencia;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public boolean isSaida() {
        return saida;
    }

    public void setSaida(boolean saida) {
        this.saida = saida;
    }
}
