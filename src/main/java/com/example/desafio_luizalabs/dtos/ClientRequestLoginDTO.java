package com.example.desafio_luizalabs.dtos;

import com.example.desafio_luizalabs.domain.Client;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClientRequestLoginDTO(

        @NotBlank(message = "O email é obrigatório")
        @Email(message = "O e-mail deve ser válido")
        String email,

        @NotBlank(message = "A senha é obrigatória")
        String password) {


    public ClientRequestLoginDTO(Client client) {
        this(client.getEmail(), client.getPassword());
    }
}