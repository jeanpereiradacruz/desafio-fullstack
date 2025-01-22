package com.example.desafio_luizalabs.auth;

import com.example.desafio_luizalabs.domain.Client;
import com.example.desafio_luizalabs.repositories.ClientRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final ClientRepository clientRepository;

    public CustomUserDetailsService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Client client = clientRepository.findByEmail(email);

        if (client == null) {
            throw new UsernameNotFoundException("usuario nao encontrado com o email: " + email);
        }

        return new org.springframework.security.core.userdetails.User(
                client.getEmail(),
                client.getPassword(),
                new ArrayList<>()
        );
    }
}
