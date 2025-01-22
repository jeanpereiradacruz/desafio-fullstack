package com.example.desafio_luizalabs.controllers;

import com.example.desafio_luizalabs.domain.Client;
import com.example.desafio_luizalabs.dtos.FavoriteListRequestDTO;
import com.example.desafio_luizalabs.services.FavoriteListService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/favorite_lists")
public class FavoriteListController {

    @Autowired
    private FavoriteListService service;

    @PostMapping
    public ResponseEntity<Client> addFavoriteList(@Valid @RequestBody FavoriteListRequestDTO dto) {
        Client updated = service.addFavoriteList(dto);
        return ResponseEntity.ok(updated);
    }

    @PostMapping("/remove/{clientId}")
    public ResponseEntity<Client> removeFavoriteList(@PathVariable("clientId") String clientId) {
        return ResponseEntity.ok(service.removeFavoriteList(clientId));
    }
}
