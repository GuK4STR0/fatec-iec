package com.argos.argos.controller;

import com.argos.argos.controller.response.HttpResponse;
import com.argos.argos.model.entities.Dependente;
import com.argos.argos.service.impl.DependenteService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "api/v1/dependente")
public class DependenteAPIController {

    private final DependenteService dependenteService;
    private final Logger log = LogManager.getLogger(DependenteAPIController.class);

    public DependenteAPIController(DependenteService dependenteService) {
        this.dependenteService = dependenteService;
    }

    @CrossOrigin
    @GetMapping
    @Transactional
    public ResponseEntity<Object> consultaDependente(){
        log.info(">>>> [Controller] consultaDependente iniciado");

        return ResponseEntity.ok().body(dependenteService.find());
    }

    @CrossOrigin
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> consultaDependentePorId(@PathVariable Long id){
        log.info(">>>> [Controller] consultaDependentePorId iniciado");

        Optional<Dependente> dependente = dependenteService.findById(id);

        return ResponseEntity.ok().body(dependente);
    }

    @CrossOrigin
    @PostMapping
    @Transactional
    public ResponseEntity<Object> inserirDependente(@RequestBody Dependente dependente){
        log.info(">>>> [Controller] inserirDependente iniciado");

        return ResponseEntity.ok().body(dependenteService.insert(dependente));
    }

    @CrossOrigin
    @PatchMapping
    @Transactional
    public ResponseEntity<Object> updateDependente(@RequestBody Dependente dependente){
        log.info(">>>> [Controller] updateDependente iniciado");

        return ResponseEntity.ok().body(dependenteService.update(dependente));
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> deleteDependente(@PathVariable Long id, HttpServletRequest request){
        log.info(">>>> [Controller] deleteDependente iniciado");

        dependenteService.delete(id);
        HttpResponse response = new HttpResponse();

        response.setStatus(HttpStatus.OK);
        response.setMessage("Dependente id: " + id +" deletado com sucesso");
        response.setPath(request.getRequestURI());

        return ResponseEntity.ok(response);
    }
}