package com.example.desafio_luizalabs.dtos;

import jakarta.validation.constraints.NotNull;

public record TokenRequestDTO(@NotNull  String token) {
}
