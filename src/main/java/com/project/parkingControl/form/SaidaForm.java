package com.project.parkingControl.form;

import com.project.parkingControl.model.Estacionamento;
import com.project.parkingControl.repository.EstacionamentoRepository;

import javax.validation.constraints.NotNull;

public class SaidaForm
{
    @NotNull
    private Boolean saida;

    public Boolean getSaida(){
        return saida;
    }

    public void setSaida(Boolean saida) {
        this.saida = saida;
    }

    //editando estacionamento adicionando true para saída
    public Estacionamento editarEstacionamentoComSaida(Integer idReserva,
                                                       EstacionamentoRepository estacionamentoRepository)
    {
        Estacionamento estacionamento = estacionamentoRepository.getOne(idReserva);

        estacionamento.setSaida(this.saida);
        //estacionamento.setMomentoSaida(LocalDateTime.now());

        return estacionamento;
    }
}
