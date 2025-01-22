package com.example.desafio_luizalabs.domain;

import com.example.desafio_luizalabs.dtos.ClientRequestRegisterDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    private FavoriteList favoriteList;

    public Client(ClientRequestRegisterDTO request) {
        this.name = request.name();
        this.email = request.email();
        this.password = request.password();
    }

    @PrePersist
    public void prePersist() {
        this.id = UUID.randomUUID();
    }

    public void setFavoriteList(FavoriteList favoriteList) {
        this.favoriteList = favoriteList;
        if (favoriteList != null) {
            favoriteList.setClient(this);
        }
    }
}