package com.example.desafio_luizalabs.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class FavoriteProduct {

    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "favoriteListId", nullable = false)
    private FavoriteList favoriteList;
}
