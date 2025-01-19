package com.example.desafio_luizalabs.controllers;

import com.example.desafio_luizalabs.dtos.ClientRequestLoginDTO;
import com.example.desafio_luizalabs.dtos.ClientRequestRegisterDTO;
import com.example.desafio_luizalabs.dtos.ClientResponseDTO;
import com.example.desafio_luizalabs.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/signup")
    public ResponseEntity<ClientResponseDTO> save(@Validated @RequestBody ClientRequestRegisterDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.save(dto));
    }

    @PostMapping("/signin")
    public ResponseEntity<ClientResponseDTO> signIn(@Validated @RequestBody ClientRequestLoginDTO dto) {
        return ResponseEntity.ok(clientService.signIn(dto));
    }

    @GetMapping
    public ResponseEntity<List<ClientResponseDTO>> getAll() {
        return ResponseEntity.ok(clientService.findAll());
    }
}