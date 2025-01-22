package com.example.desafio_luizalabs.controllers;

import com.example.desafio_luizalabs.domain.Client;
import com.example.desafio_luizalabs.dtos.FavoriteProductRequestDTO;
import com.example.desafio_luizalabs.services.FavoriteProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/favorite_products")
public class FavoriteProductController {

    @Autowired
    private FavoriteProductService service;

    @PostMapping
    public ResponseEntity<Client> addFavorite(@Valid @RequestBody FavoriteProductRequestDTO dto) {
        Client updated = service.favorite(dto);
        return ResponseEntity.ok(updated);
    }

    @PostMapping("/remove")
    public ResponseEntity<Client> removeFavorite(@Valid @RequestBody FavoriteProductRequestDTO dto) {
        Client updated = service.removeFavorite(dto);
        return ResponseEntity.ok(updated);
    }
}
