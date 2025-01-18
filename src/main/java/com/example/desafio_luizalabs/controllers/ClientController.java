package com.example.desafio_luizalabs.controllers;

import com.example.desafio_luizalabs.dtos.ClientRequestDTO;
import com.example.desafio_luizalabs.dtos.ClientResponseDTO;
import com.example.desafio_luizalabs.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientResponseDTO> save(@RequestBody ClientRequestDTO dto) {
        return new ResponseEntity<>(clientService.save(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ClientResponseDTO>> getAll() {
        return new ResponseEntity<>(clientService.findAll(), HttpStatus.OK);
    }
}