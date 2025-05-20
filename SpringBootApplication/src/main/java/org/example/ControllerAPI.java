package org.example;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news")
public class ControllerAPI {
    private final ServiceNews serviceNews;

    public ControllerAPI(ServiceNews serviceNews) {
        this.serviceNews = serviceNews;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getById(@PathVariable long id) {
        News message = serviceNews.getById(id);
        boolean status = (message != null);
        return status ?
                new ResponseEntity<>(message, HttpStatus.OK) :
                new ResponseEntity<>(new ErrorMessage(id).getMessage(), HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity getAll() {
        return new ResponseEntity<>(serviceNews.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody News message) {
        return new ResponseEntity<>(serviceNews.create(message), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity put(@RequestBody News message) {
        return new ResponseEntity<>(serviceNews.update(message), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteById(@PathVariable long id) {
            return serviceNews.deleteById(id) ?
                   new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                   new ResponseEntity<>(new ErrorMessage(id).getMessage(), HttpStatus.NOT_FOUND);
    }
}