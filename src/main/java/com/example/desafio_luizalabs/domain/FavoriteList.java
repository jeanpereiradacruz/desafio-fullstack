package com.example.desafio_luizalabs.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity(name = "favoriteList")
@Table(name = "favorite_lists")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class FavoriteList {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @OneToOne
    @JoinColumn(name = "client_id", nullable = false, unique = true)
    @JsonBackReference
    private Client client;

    @OneToMany(mappedBy = "favoriteList", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<FavoriteProduct> products = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.id = UUID.randomUUID();
    }

    public void addProduct(FavoriteProduct product) {
        products.add(product);
        product.setFavoriteList(this); // Sincroniza o lado bidirecional
    }
}
