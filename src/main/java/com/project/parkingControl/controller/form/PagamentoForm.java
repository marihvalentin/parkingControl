package com.project.parkingControl.controller.form;

import com.project.parkingControl.model.Estacionamento;
import com.project.parkingControl.repository.EstacionamentoRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PagamentoForm
{
    @NotNull
    private Boolean pagamento;

    public Boolean getPagamento() {
        return pagamento;
    }

    public void setPagamento(Boolean pagamento) {
        this.pagamento = pagamento;
    }

    //editando estacionamento adicionando true para pagamento
    public Estacionamento editarEstacionamentoComPagamento(Integer idReserva,
                                                           EstacionamentoRepository estacionamentoRepository)
    {
        Estacionamento estacionamento = estacionamentoRepository.getOne(idReserva);

        estacionamento.setPagamento(this.pagamento);

        return estacionamento;
    }
}
