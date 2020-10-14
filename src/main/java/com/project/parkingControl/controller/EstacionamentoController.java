package com.project.parkingControl.controller;

import com.project.parkingControl.dto.ConsultaHistoricoDto;
import com.project.parkingControl.form.EntradaForm;
import com.project.parkingControl.model.Estacionamento;
import com.project.parkingControl.repository.EstacionamentoRepository;
import com.project.parkingControl.service.EstacionamentoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
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
    public ResponseEntity<Boolean> estacionar(@RequestBody @Valid EntradaForm form)
    {
        try{
            return ResponseEntity.ok(estacionamentoService.estacionar(form));
        }catch(Exception ex){
            log.error(String.valueOf(ex));
            return ResponseEntity.notFound().build();
        }

    }

    //Pagamento
    @RequestMapping(value = "estacionamentos/{idReserva}/pagamento", method = RequestMethod.PUT) @Transactional
    public ResponseEntity<Boolean> pagar(@PathVariable Integer idReserva) {
        try{
            return ResponseEntity.ok(estacionamentoService.pagar(idReserva));
        }catch(Exception ex){
            log.error(String.valueOf(ex));
            return ResponseEntity.notFound().build();
        }
    }

    //Saida
    @RequestMapping(value = "estacionamentos/{idReserva}/saida", method = RequestMethod.PUT) @Transactional
    public ResponseEntity<Boolean> sair(@PathVariable Integer idReserva) {
        try {
            return ResponseEntity.ok(estacionamentoService.sair(idReserva));
        } catch (Exception ex) {
            log.error(String.valueOf(ex));
            return ResponseEntity.notFound().build();
        }
    }

    //Historico
    @RequestMapping(value = "estacionamentos/{placaVeiculo}", method = RequestMethod.GET) @ResponseBody
    public ResponseEntity<ConsultaHistoricoDto> consultarVeiculo(@PathVariable String placaVeiculo){
        try {
            ConsultaHistoricoDto consultaHistoricoDto = estacionamentoService.getHistoricoVeiculo(placaVeiculo);
            return new ResponseEntity<> (consultaHistoricoDto, HttpStatus.OK);
        } catch (Exception ex) {
            log.error(String.valueOf(ex));
            return ResponseEntity.notFound().build();
        }
    }
}
