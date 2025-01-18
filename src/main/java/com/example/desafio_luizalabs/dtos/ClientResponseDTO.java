package com.example.desafio_luizalabs.dtos;

import com.example.desafio_luizalabs.domain.Client;

import java.util.UUID;

public record ClientResponseDTO(UUID id, String name, String email) {

    public ClientResponseDTO(Client client){
        this(client.getId(), client.getName(), client.getEmail());
    }

}