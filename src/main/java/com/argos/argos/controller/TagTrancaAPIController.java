package com.argos.argos.controller;

import com.argos.argos.controller.response.HttpResponse;
import com.argos.argos.model.entities.TagTranca;
import com.argos.argos.service.impl.TagTrancaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "api/v1/tagtranca")
public class TagTrancaAPIController {

    private final TagTrancaService tagTrancaService;
    private final Logger log = LogManager.getLogger(TagTrancaAPIController.class);

    public TagTrancaAPIController(TagTrancaService tagTrancaService) {
        this.tagTrancaService = tagTrancaService;
    }

    @CrossOrigin
    @GetMapping
    @Transactional
    public ResponseEntity<Object> consultaTagTranca(){
        log.info(">>>> [Controller] consultaTagTranca iniciado");

        return ResponseEntity.ok().body(tagTrancaService.find());
    }

    @CrossOrigin
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> consultaTagTrancaPorId(@PathVariable Long id){
        log.info(">>>> [Controller] consultaTagTrancaPorId iniciado");

        Optional<TagTranca> tagTranca = tagTrancaService.findById(id);

        return ResponseEntity.ok().body(tagTranca);
    }

    @CrossOrigin
    @PostMapping
    @Transactional
    public ResponseEntity<Object> inserirTagTranca(@RequestBody TagTranca tagTranca){
        log.info(">>>> [Controller] inserirTagTranca iniciado");

        return ResponseEntity.ok().body(tagTrancaService.insert(tagTranca));
    }

    @CrossOrigin
    @PatchMapping
    @Transactional
    public ResponseEntity<Object> updateTagTranca(@RequestBody TagTranca tagTranca){
        log.info(">>>> [Controller] updateTagTranca iniciado");

        return ResponseEntity.ok().body(tagTrancaService.update(tagTranca));
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> deleteTagTranca(@PathVariable Long id, HttpServletRequest request){
        log.info(">>>> [Controller] deleteTagTranca iniciado");

        tagTrancaService.delete(id);
        HttpResponse response = new HttpResponse();

        response.setStatus(HttpStatus.OK);
        response.setMessage("TagTranca id: " + id +" deletado com sucesso");
        response.setPath(request.getRequestURI());

        return ResponseEntity.ok(response);
    }
}