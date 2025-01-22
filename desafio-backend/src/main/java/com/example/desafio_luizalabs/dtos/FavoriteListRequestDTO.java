package com.example.desafio_luizalabs.dtos;

import jakarta.validation.constraints.NotNull;

public record FavoriteListRequestDTO(
        String id,
        @NotNull String title,
        @NotNull String description,
        @NotNull String clientId) {
}
