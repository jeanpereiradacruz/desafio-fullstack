package com.example.desafio_luizalabs.repositories;

import com.example.desafio_luizalabs.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {

    Client findByEmail(String email);
    boolean existsByEmail(String email);
}