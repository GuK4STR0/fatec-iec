package com.argos.argos.controller;

import com.argos.argos.controller.response.HttpResponse;
import com.argos.argos.model.entities.Administrador;
import com.argos.argos.service.impl.AdministradorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "api/v1/administrador")
public class AdministradorAPIController {

    private final AdministradorService administradorService;
    private final Logger log = LogManager.getLogger(AdministradorAPIController.class);

    public AdministradorAPIController(AdministradorService administradorService) {
        this.administradorService = administradorService;
    }

    @CrossOrigin
    @GetMapping
    @Transactional
    public ResponseEntity<Object> consultaAdministrador(){
        log.info(">>>> [Controller] consultaAdministrador iniciado");

        return ResponseEntity.ok().body(administradorService.find());
    }

    @CrossOrigin
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> consultaAdministradorPorId(@PathVariable Long id){
        log.info(">>>> [Controller] consultaAdministradorPorId iniciado");

        Optional<Administrador> administrador = administradorService.findById(id);

        return ResponseEntity.ok().body(administrador);
    }

    @CrossOrigin
    @PostMapping
    @Transactional
    public ResponseEntity<Object> inserirAdministrador(@RequestBody Administrador administrador){
        log.info(">>>> [Controller] inserirAdministrador iniciado");

        return ResponseEntity.ok().body(administradorService.insert(administrador));
    }

    @CrossOrigin
    @PatchMapping
    @Transactional
    public ResponseEntity<Object> updateAdministrador(@RequestBody Administrador administrador){
        log.info(">>>> [Controller] updateAdministrador iniciado");

        return ResponseEntity.ok().body(administradorService.update(administrador));
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> deleteAdministrador(@PathVariable Long id, HttpServletRequest request){
        log.info(">>>> [Controller] deleteAdministrador iniciado");

        administradorService.delete(id);
        HttpResponse response = new HttpResponse();

        response.setStatus(HttpStatus.OK);
        response.setMessage("Administrador id: " + id +" deletado com sucesso");
        response.setPath(request.getRequestURI());

        return ResponseEntity.ok(response);
    }
}