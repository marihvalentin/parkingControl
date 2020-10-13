package com.project.parkingControl.controller;

import com.project.parkingControl.controller.dto.ConsultaHistoricoDto;
import com.project.parkingControl.controller.dto.EntradaDto;
import com.project.parkingControl.controller.dto.PagamentoDto;
import com.project.parkingControl.controller.dto.SaidaDto;
import com.project.parkingControl.controller.form.EntradaForm;
import com.project.parkingControl.controller.form.PagamentoForm;
import com.project.parkingControl.controller.form.SaidaForm;
import com.project.parkingControl.model.Estacionamento;
import com.project.parkingControl.repository.EstacionamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class EstacionamentoController
{
    @Autowired
    private EstacionamentoRepository estacionamentoRepository;

    //lista teste com entradas realizadas
    /*@RequestMapping(value = "/estacionamentos", method = RequestMethod.GET) @ResponseBody
    public List<EntradaDto> listarEntradas()
    {
        List<Estacionamento> estacionamentos = estacionamentoRepository.findAll();
        return EntradaDto.converter(estacionamentos);
    }*/

    //Entrada
    @RequestMapping(value = "/estacionamentos", method = RequestMethod.POST) @Transactional
    public ResponseEntity<EntradaDto> entrar(@RequestBody @Valid EntradaForm form, UriComponentsBuilder uriBuilder)
    {
        Estacionamento estacionamento = form.converter();
        estacionamentoRepository.save(estacionamento);

        URI uri = uriBuilder.path("/estacionamentos/{id}").buildAndExpand(estacionamento.getIdReserva()).toUri();
        return ResponseEntity.created(uri).body(new EntradaDto(estacionamento));

    }

    //Pagamento
    @RequestMapping(value = "estacionamentos/{idReserva}/pagamento", method = RequestMethod.PUT) @Transactional
    public ResponseEntity<PagamentoDto> pagar(@PathVariable Integer idReserva, @RequestBody @Valid PagamentoForm form)
    {
        Optional<Estacionamento> optional = estacionamentoRepository.findByIdReserva(idReserva);
        if(optional.isPresent())
        {
            Estacionamento estacionamento = form.editarEstacionamentoComPagamento(idReserva, estacionamentoRepository);
            return ResponseEntity.ok(new PagamentoDto(estacionamento));
        }
        return ResponseEntity.notFound().build();
    }

    //Saida
    @RequestMapping(value = "estacionamentos/{idReserva}/saida", method = RequestMethod.PUT) @Transactional
    public ResponseEntity<SaidaDto> sair(@PathVariable Integer idReserva, @RequestBody @Valid SaidaForm form,
                                         Estacionamento pagamento)
    {
        Optional<Estacionamento> optional = estacionamentoRepository.findByIdReserva(idReserva);

        /*if(pagamento.getPagamento())
        { logica não funcionou como esperado*/
            if (optional.isPresent()) {
                Estacionamento estacionamento = form.editarEstacionamentoComSaida(idReserva, estacionamentoRepository);
                return ResponseEntity.ok(new SaidaDto(estacionamento));
            }
        //}
        return ResponseEntity.notFound().build();
    }

    //Historico
    @RequestMapping(value = "estacionamentos/{placaVeiculo}", method = RequestMethod.GET)
    public ResponseEntity<ConsultaHistoricoDto> consultarVeiculo(@PathVariable String placaVeiculo)
    {
        Optional<Estacionamento> optional = estacionamentoRepository.findByPlacaVeiculo(placaVeiculo);
        if(optional.isPresent())
        {
            return ResponseEntity.ok(new ConsultaHistoricoDto(optional.get()));
        }
        return ResponseEntity.notFound().build();
    }
}
