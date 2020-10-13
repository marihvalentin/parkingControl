package com.project.parkingControl.form;

import com.project.parkingControl.model.Estacionamento;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EntradaForm
{
    @NotEmpty @NotNull
    private String placaVeiculo;

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public Estacionamento converter()
    {
        return new Estacionamento(placaVeiculo);
    }

    public Boolean validaPlaca(String placaVeiculo)
    {
        boolean result = false;

        Pattern pattern = Pattern.compile("[A-Z]{3}-\\d{4}");
        Matcher mat = pattern.matcher(placaVeiculo);

        if (!mat.matches())
        {
            result = false;
        }
        else
        {
            result = true;
        }
        return result;
    }


}
