package com.example.desafio_luizalabs.dtos;

import com.example.desafio_luizalabs.domain.Client;
import com.example.desafio_luizalabs.domain.FavoriteList;

import java.util.UUID;

public record ClientResponseDTO(UUID id, String name, String email, FavoriteList favoriteList, String token) {

    public ClientResponseDTO(Client client){
        this(client.getId(), client.getName(), client.getEmail(), client.getFavoriteList(), null);
    }

    public ClientResponseDTO(Client client, String token){
        this(client.getId(), client.getName(), client.getEmail(), client.getFavoriteList(), token);
    }

}