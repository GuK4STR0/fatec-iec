package com.argos.argos.controller;

import com.argos.argos.controller.response.HttpResponse;
import com.argos.argos.model.entities.Chamado;
import com.argos.argos.service.impl.ChamadoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "api/v1/chamado")
public class ChamadoAPIController {

    private final ChamadoService chamadoService;
    private final Logger log = LogManager.getLogger(ChamadoAPIController.class);

    public ChamadoAPIController(ChamadoService chamadoService) {
        this.chamadoService = chamadoService;
    }

    @CrossOrigin
    @GetMapping
    @Transactional
    public ResponseEntity<Object> consultaChamado(){
        log.info(">>>> [Controller] consultaChamado iniciado");

        return ResponseEntity.ok().body(chamadoService.find());
    }

    @CrossOrigin
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> consultaChamadoPorId(@PathVariable Long id){
        log.info(">>>> [Controller] consultaChamadoPorId iniciado");

        Optional<Chamado> chamado = chamadoService.findById(id);

        return ResponseEntity.ok().body(chamado);
    }

    @CrossOrigin
    @PostMapping
    @Transactional
    public ResponseEntity<Object> inserirChamado(@RequestParam(value = "userId") Long id ,@RequestBody Chamado chamado){
        log.info(">>>> [Controller] inserirChamado iniciado");

        return ResponseEntity.ok().body(chamadoService.insert(chamado, id));
    }

    @CrossOrigin
    @PatchMapping
    @Transactional
    public ResponseEntity<Object> updateChamado(@RequestBody Chamado chamado){
        log.info(">>>> [Controller] updateChamado iniciado");

        return ResponseEntity.ok().body(chamadoService.update(chamado));
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> deleteChamado(@PathVariable Long id, HttpServletRequest request){
        log.info(">>>> [Controller] deleteChamado iniciado");

        chamadoService.delete(id);
        HttpResponse response = new HttpResponse();

        response.setStatus(HttpStatus.OK);
        response.setMessage("Chamado id: " + id +" deletado com sucesso");
        response.setPath(request.getRequestURI());

        return ResponseEntity.ok(response);
    }
}