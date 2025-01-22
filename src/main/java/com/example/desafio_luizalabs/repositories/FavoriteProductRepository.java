package com.example.desafio_luizalabs.repositories;

import com.example.desafio_luizalabs.domain.FavoriteProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteProductRepository extends JpaRepository<FavoriteProduct, Long> {
}
