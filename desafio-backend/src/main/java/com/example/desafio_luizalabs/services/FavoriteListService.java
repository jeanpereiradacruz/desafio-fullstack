package com.example.desafio_luizalabs.services;

import com.example.desafio_luizalabs.domain.Client;
import com.example.desafio_luizalabs.domain.FavoriteList;
import com.example.desafio_luizalabs.dtos.FavoriteListRequestDTO;
import com.example.desafio_luizalabs.repositories.FavoriteListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FavoriteListService {

    @Autowired
    private FavoriteListRepository repository;

    @Autowired
    private ClientService clientService;

    public Client addFavoriteList(FavoriteListRequestDTO dto) {
        Client client = clientService.findById(UUID.fromString(dto.clientId()));

        FavoriteList favoriteList;
        if (client.getFavoriteList() == null) {
            favoriteList = new FavoriteList();
            favoriteList.setId(UUID.randomUUID());
            favoriteList.setDescription(dto.description());
            favoriteList.setTitle(dto.title());
            favoriteList.setClient(client);
            client.setFavoriteList(save(favoriteList));
        } else {
            client.getFavoriteList().setTitle(dto.title());
            client.getFavoriteList().setDescription(dto.description());
            save(client.getFavoriteList());
        }

        clientService.save(client);
        return client;
    }

    public FavoriteList save(FavoriteList favoriteList) {
        return this.repository.save(favoriteList);
    }

    public Client removeFavoriteList(String clientId) {
        Client client = clientService.findById(UUID.fromString(clientId));
        client.setFavoriteList(null);
        clientService.save(client);
        return client;
    }
}
