package com.example.desafio_luizalabs.domain;

import com.example.desafio_luizalabs.dtos.ClientRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table(name = "clients")
@Entity(name = "clients")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public class Client {

    @Id
    private UUID id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;

    public Client(ClientRequestDTO request) {
        this.name = request.name();
        this.email = request.email();
        this.password = request.password();
    }
}