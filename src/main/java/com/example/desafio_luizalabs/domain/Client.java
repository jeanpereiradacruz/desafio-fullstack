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

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private FavoriteList favoriteList;

    public Client(ClientRequestDTO request) {
        this.name = request.name();
        this.email = request.email();
        this.password = request.password();
    }

    @PrePersist
    public void prePersist() {
        this.id = UUID.randomUUID();
    }
}