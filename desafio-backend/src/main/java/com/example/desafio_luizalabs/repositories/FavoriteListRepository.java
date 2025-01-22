package com.example.desafio_luizalabs.repositories;

import com.example.desafio_luizalabs.domain.FavoriteList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FavoriteListRepository extends JpaRepository<FavoriteList, UUID> {
}
