package com.example.desafio_luizalabs.dtos;

import com.example.desafio_luizalabs.domain.Client;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ClientRequestRegisterDTO(

        @NotBlank(message = "O nome é obrigatório")
        String name,

        @NotBlank(message = "O email é obrigatório")
        @Email(message = "O e-mail deve ser válido")
        String email,

        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 6, message = "A senha deve ter no minimo 6 caracteres")
        String password) {


    public ClientRequestRegisterDTO(Client client) {
        this(client.getName(), client.getEmail(), client.getPassword());
    }
}