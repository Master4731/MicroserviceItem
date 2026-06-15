package com.sedena.app.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sedena.app.entities.item;
import com.sedena.app.services.IService;

@RestController
@RequestMapping("/item")
public class MicroserviceController {

    private IService service;

    public MicroserviceController(IService service) {
        this.service = service;

    }

    @PostMapping
    public ResponseEntity<String> insert(@RequestBody item i) {

        try {
            if (service.insert(i)) {
                return new ResponseEntity<String>("Insertado", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<String>("revisa request", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            return new ResponseEntity<String>("error en server", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/name/{name}") // http://ip:port/item/name
    public ResponseEntity<item> searchByName(@PathVariable("name") String name) {
        try {
            return new ResponseEntity<>(service.selectByName(name), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}") // http://ip:port/item/id
    public ResponseEntity<item> findById(@PathVariable("id") long id) {
        try {
            return new ResponseEntity<>(service.selectById(id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody item i) {
        try {
            if (service.updateById(i))
                return new ResponseEntity<>("actualizado", HttpStatus.OK);
            else
                return new ResponseEntity<>("revisa request", HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<item>> selectAll() {
        try {
            return new ResponseEntity<>(service.selectALL(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") long id) {

        try {
            if (service.deleteById(id))
                return new ResponseEntity<>("Eliminado", HttpStatus.OK);
            else
                return new ResponseEntity<>("revisa solicitud", HttpStatus.BAD_REQUEST);

        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("revisa solicitud", HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
