package com.argos.argos.controller;

import com.argos.argos.controller.response.HttpResponse;
import com.argos.argos.model.entities.Localidades;
import com.argos.argos.service.impl.LocalidadesService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "api/v1/localidades")
public class LocalidadesAPIController {

    private final LocalidadesService localidadesService;
    private final Logger log = LogManager.getLogger(LocalidadesAPIController.class);

    public LocalidadesAPIController(LocalidadesService localidadesService) {
        this.localidadesService = localidadesService;
    }

    @CrossOrigin
    @GetMapping
    @Transactional
    public ResponseEntity<Object> consultaLocalidades(){
        log.info(">>>> [Controller] consultaLocalidades iniciado");

        return ResponseEntity.ok().body(localidadesService.find());
    }

    @CrossOrigin
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> consultaLocalidadesPorId(@PathVariable Long id){
        log.info(">>>> [Controller] consultaLocalidadesPorId iniciado");

        Optional<Localidades> localidades = localidadesService.findById(id);

        return ResponseEntity.ok().body(localidades);
    }

    @CrossOrigin
    @PostMapping
    @Transactional
    public ResponseEntity<Object> inserirLocalidades(@RequestBody Localidades localidades){
        log.info(">>>> [Controller] inserirLocalidades iniciado");

        return ResponseEntity.ok().body(localidadesService.insert(localidades));
    }

    @CrossOrigin
    @PatchMapping
    @Transactional
    public ResponseEntity<Object> updateLocalidades(@RequestBody Localidades localidades){
        log.info(">>>> [Controller] updateLocalidades iniciado");

        return ResponseEntity.ok().body(localidadesService.update(localidades));
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> deleteLocalidades(@PathVariable Long id, HttpServletRequest request){
        log.info(">>>> [Controller] deleteLocalidades iniciado");

        localidadesService.delete(id);
        HttpResponse response = new HttpResponse();

        response.setStatus(HttpStatus.OK);
        response.setMessage("Localidades id: " + id +" deletado com sucesso");
        response.setPath(request.getRequestURI());

        return ResponseEntity.ok(response);
    }
}