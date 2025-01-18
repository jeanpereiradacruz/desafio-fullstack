package com.example.desafio_luizalabs.services;

import com.example.desafio_luizalabs.domain.Client;
import com.example.desafio_luizalabs.dtos.ClientRequestDTO;
import com.example.desafio_luizalabs.dtos.ClientResponseDTO;
import com.example.desafio_luizalabs.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public ClientResponseDTO save(ClientRequestDTO dto) {
        Client client = new Client(dto);
        client.setPassword(passwordEncoder.encode(dto.password()));
        clientRepository.save(client);
        return new ClientResponseDTO(client);
    }

    public List<ClientResponseDTO> findAll() {
        return clientRepository.findAll().stream().map(ClientResponseDTO::new).toList();
    }

    public Client findByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    public boolean checkPassword(String plain, String hashed) {
        return passwordEncoder.matches(plain, hashed);
    }
}