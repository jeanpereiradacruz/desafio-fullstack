package com.example.desafio_luizalabs.auth;

import com.example.desafio_luizalabs.domain.Client;
import com.example.desafio_luizalabs.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private ClientService clientService;

    @Autowired
    private JwtUtil jwtUtil;

    public Client validateToken(String token) {
        String email = jwtUtil.validateToken(token);
        if (email != null) {
            return clientService.findByEmail(email);
        }
        System.out.println("Token invalido ou expirado");
        return null;
    }

}
