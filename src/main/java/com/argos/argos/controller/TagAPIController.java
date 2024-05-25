package com.argos.argos.controller;

import com.argos.argos.controller.response.HttpResponse;
import com.argos.argos.model.entities.Tag;
import com.argos.argos.service.impl.TagService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "api/v1/tag")
public class TagAPIController {

    private final TagService tagService;
    private final Logger log = LogManager.getLogger(TagAPIController.class);

    public TagAPIController(TagService tagService) {
        this.tagService = tagService;
    }

    @CrossOrigin
    @GetMapping
    @Transactional
    public ResponseEntity<Object> consultaTag(){
        log.info(">>>> [Controller] consultaTag iniciado");

        return ResponseEntity.ok().body(tagService.find());
    }

    @CrossOrigin
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> consultaTagPorId(@PathVariable Long id){
        log.info(">>>> [Controller] consultaTagPorId iniciado");

        Optional<Tag> tag = tagService.findById(id);

        return ResponseEntity.ok().body(tag);
    }

    @CrossOrigin
    @PostMapping
    @Transactional
    public ResponseEntity<Object> inserirTag(@RequestBody Tag tag){
        log.info(">>>> [Controller] inserirTag iniciado");

        return ResponseEntity.ok().body(tagService.insert(tag));
    }

    @CrossOrigin
    @PatchMapping
    @Transactional
    public ResponseEntity<Object> updateTag(@RequestBody Tag tag){
        log.info(">>>> [Controller] updateTag iniciado");

        return ResponseEntity.ok().body(tagService.update(tag));
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> deleteTag(@PathVariable Long id, HttpServletRequest request){
        log.info(">>>> [Controller] deleteTag iniciado");

        tagService.delete(id);
        HttpResponse response = new HttpResponse();

        response.setStatus(HttpStatus.OK);
        response.setMessage("Tag id: " + id +" deletado com sucesso");
        response.setPath(request.getRequestURI());

        return ResponseEntity.ok(response);
    }
}