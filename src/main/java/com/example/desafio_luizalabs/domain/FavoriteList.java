package com.example.desafio_luizalabs.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class FavoriteList {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @OneToOne
    @JoinColumn(name = "clientId", nullable = false)
    private Client client;

    @OneToMany(mappedBy = "favoriteList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FavoriteProduct> products = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.id = UUID.randomUUID();
    }
}
