package com.example.desafio_luizalabs.dtos;

import com.example.desafio_luizalabs.domain.FavoriteProduct;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record FavoriteProductRequestDTO(
        @NotNull(message = "O id do cliente não pode ser nulo") String clientId,
        String favoriteListId,
        @NotNull(message = "O id do Produto não pode ser nulo") FavoriteProduct product) {
}
