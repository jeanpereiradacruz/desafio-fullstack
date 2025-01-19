package com.example.desafio_luizalabs.services;

import com.example.desafio_luizalabs.domain.Client;
import com.example.desafio_luizalabs.dtos.ClientRequestLoginDTO;
import com.example.desafio_luizalabs.dtos.ClientRequestRegisterDTO;
import com.example.desafio_luizalabs.dtos.ClientResponseDTO;
import com.example.desafio_luizalabs.exception.ClientNotFoundException;
import com.example.desafio_luizalabs.exception.InvalidCredentialsException;
import com.example.desafio_luizalabs.repositories.ClientRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public ClientResponseDTO save(ClientRequestRegisterDTO dto) {
        if (clientRepository.existsByEmail(dto.email())) {
            throw new EntityExistsException("Este cliente já está cadastrado.");
        }

        Client client = new Client(dto);
        client.setPassword(passwordEncoder.encode(dto.password()));
        clientRepository.save(client);

        String token = generateToken(client);

        return new ClientResponseDTO(client, token);
    }

    public String generateToken(Client client) {
        // TODO implementar token JWT
        return UUID.randomUUID().toString().replace("-", "");
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

    public ClientResponseDTO signIn(ClientRequestLoginDTO dto) {
        Client client = findByEmail(dto.email());

        if (client == null) {
            throw new ClientNotFoundException("Você ainda não possui cadastro! Cadastre-se e tente novamente.");
        }
        if (!checkPassword(dto.password(), client.getPassword())) {
            throw new InvalidCredentialsException("Email ou senha incorretos. Tente novamente.");
        }

        String token = generateToken(client);

        return new ClientResponseDTO(client, token);
    }

}