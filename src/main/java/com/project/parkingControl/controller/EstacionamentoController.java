package com.project.parkingControl.controller;

import com.project.parkingControl.dto.ConsultaHistoricoDto;
import com.project.parkingControl.dto.EstacionamentoDto;
import com.project.parkingControl.dto.PagamentoDto;
import com.project.parkingControl.dto.SaidaDto;
import com.project.parkingControl.form.EntradaForm;
import com.project.parkingControl.form.PagamentoForm;
import com.project.parkingControl.form.SaidaForm;
import com.project.parkingControl.model.Estacionamento;
import com.project.parkingControl.repository.EstacionamentoRepository;
import com.project.parkingControl.service.EstacionamentoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
public class EstacionamentoController {

    @Autowired
    private EstacionamentoService estacionamentoService;

    @Autowired
    private EstacionamentoRepository estacionamentoRepository;

    private static Logger log = LoggerFactory.getLogger(EstacionamentoService.class);

    //Entrada
    @RequestMapping(value = "/estacionamentos", method = RequestMethod.POST) @Transactional
    public ResponseEntity<EstacionamentoDto> entrar(@RequestBody @Valid EntradaForm form, UriComponentsBuilder uriBuilder)
    {
        Estacionamento estacionamento = form.converter();
        if(estacionamentoService.isPlacaValida(form.getPlacaVeiculo()))
        {
            estacionamento.setEntrada(LocalDateTime.now());
            estacionamento.setPlacaVeiculo(form.getPlacaVeiculo());
            estacionamentoRepository.save(estacionamento);
            URI uri = uriBuilder.path("/estacionamentos/{id}").buildAndExpand(estacionamento.getIdReserva()).toUri();
            return ResponseEntity.created(uri).body(new EstacionamentoDto(estacionamento));
        }
        else
        {
            log.error("Placa Inválida!!");
            return ResponseEntity.badRequest().build();
        }
    }

    //Pagamento
    @RequestMapping(value = "estacionamentos/{idReserva}/pagamento", method = RequestMethod.PUT) @Transactional
    public ResponseEntity<PagamentoDto> pagar(@PathVariable Integer idReserva, @RequestBody @Valid PagamentoForm form)
    {
        Estacionamento estacionamento = estacionamentoRepository.findByIdReserva(idReserva);
        if(estacionamento != null)
        {
            estacionamento = form.editarEstacionamentoComPagamento(idReserva, estacionamentoRepository);
            return ResponseEntity.ok(new PagamentoDto(estacionamento));
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    //Saida
    @RequestMapping(value = "estacionamentos/{idReserva}/saida", method = RequestMethod.PUT) @Transactional
    public ResponseEntity<SaidaDto> sair(@PathVariable Integer idReserva, @RequestBody @Valid SaidaForm form) {
        try {
            SaidaDto estacionamento = estacionamentoService.sair(idReserva, form);
            return ResponseEntity.ok(new SaidaDto(estacionamento));
        } catch (Exception ex) {
            log.error(String.valueOf(ex));
            return ResponseEntity.notFound().build();
        }
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
