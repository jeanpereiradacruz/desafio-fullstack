package com.example.desafio_luizalabs.dtos;

import com.example.desafio_luizalabs.domain.Client;

import java.util.UUID;

public record ClientRequestDTO(UUID id, String name, String email, String password) {

    public ClientRequestDTO(Client client) {
        this(client.getId(), client.getName(), client.getEmail(), client.getPassword());
    }
}