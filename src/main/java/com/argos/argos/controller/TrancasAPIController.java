package com.argos.argos.controller;

import com.argos.argos.controller.response.HttpResponse;
import com.argos.argos.model.entities.Trancas;
import com.argos.argos.service.impl.TrancasService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "api/v1/tranca")
public class TrancasAPIController {

    private final TrancasService trancaService;
    private final Logger log = LogManager.getLogger(TrancasAPIController.class);

    public TrancasAPIController(TrancasService trancaService) {
        this.trancaService = trancaService;
    }

    @CrossOrigin
    @GetMapping
    @Transactional
    public ResponseEntity<Object> consultaTranca(){
        log.info(">>>> [Controller] consultaTranca iniciado");

        return ResponseEntity.ok().body(trancaService.find());
    }

    @CrossOrigin
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> consultaTrancaPorId(@PathVariable Long id){
        log.info(">>>> [Controller] consultaTrancaPorId iniciado");

        Optional<Trancas> tranca = trancaService.findById(id);

        return ResponseEntity.ok().body(tranca);
    }

    @CrossOrigin
    @PostMapping
    @Transactional
    public ResponseEntity<Object> inserirTranca(@RequestBody Trancas tranca){
        log.info(">>>> [Controller] inserirTranca iniciado");

        return ResponseEntity.ok().body(trancaService.insert(tranca));
    }

    @CrossOrigin
    @PatchMapping
    @Transactional
    public ResponseEntity<Object> updateTranca(@RequestBody Trancas tranca){
        log.info(">>>> [Controller] updateTranca iniciado");

        return ResponseEntity.ok().body(trancaService.update(tranca));
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> deleteTranca(@PathVariable Long id, HttpServletRequest request){
        log.info(">>>> [Controller] deleteTranca iniciado");

        trancaService.delete(id);
        HttpResponse response = new HttpResponse();

        response.setStatus(HttpStatus.OK);
        response.setMessage("Tranca id: " + id +" deletado com sucesso");
        response.setPath(request.getRequestURI());

        return ResponseEntity.ok(response);
    }
}