package com.example.desafio_luizalabs.dtos;

import com.example.desafio_luizalabs.domain.Client;

import java.util.UUID;

public record ClientResponseDTO(UUID id, String name, String email, String token) {

    public ClientResponseDTO(Client client){
        this(client.getId(), client.getName(), client.getEmail(), null);
    }

    public ClientResponseDTO(Client client, String token){
        this(client.getId(), client.getName(), client.getEmail(), token);
    }

}