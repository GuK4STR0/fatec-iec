package com.argos.argos.controller;

import com.argos.argos.controller.response.HttpResponse;
import com.argos.argos.model.entities.Responsavel;
import com.argos.argos.service.impl.ResponsavelService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "api/v1/responsavel")
public class ResponsavelAPIController {

    private final ResponsavelService responsavelService;
    private final Logger log = LogManager.getLogger(ResponsavelAPIController.class);

    public ResponsavelAPIController(ResponsavelService responsavelService) {
        this.responsavelService = responsavelService;
    }

    @CrossOrigin
    @GetMapping
    @Transactional
    public ResponseEntity<Object> consultaResponsavel(){
        log.info(">>>> [Controller] consultaResponsavel iniciado");

        return ResponseEntity.ok().body(responsavelService.find());
    }

    @CrossOrigin
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> consultaResponsavelPorId(@PathVariable Long id){
        log.info(">>>> [Controller] consultaResponsavelPorId iniciado");

        Optional<Responsavel> responsavel = responsavelService.findById(id);

        return ResponseEntity.ok().body(responsavel);
    }

    @CrossOrigin
    @PostMapping
    @Transactional
    public ResponseEntity<Object> inserirResponsavel(@RequestBody Responsavel responsavel){
        log.info(">>>> [Controller] inserirResponsavel iniciado");

        return ResponseEntity.ok().body(responsavelService.insert(responsavel));
    }

    @CrossOrigin
    @PatchMapping
    @Transactional
    public ResponseEntity<Object> updateResponsavel(@RequestBody Responsavel responsavel){
        log.info(">>>> [Controller] updateResponsavel iniciado");

        return ResponseEntity.ok().body(responsavelService.update(responsavel));
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> deleteResponsavel(@PathVariable Long id, HttpServletRequest request){
        log.info(">>>> [Controller] deleteResponsavel iniciado");

        responsavelService.delete(id);
        HttpResponse response = new HttpResponse();

        response.setStatus(HttpStatus.OK);
        response.setMessage("Responsavel id: " + id +" deletado com sucesso");
        response.setPath(request.getRequestURI());

        return ResponseEntity.ok(response);
    }
}