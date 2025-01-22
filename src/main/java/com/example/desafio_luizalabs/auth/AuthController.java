package com.example.desafio_luizalabs.auth;

import com.example.desafio_luizalabs.domain.Client;
import com.example.desafio_luizalabs.dtos.TokenRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/validate")
    public ResponseEntity<Client> validateToken(@RequestBody TokenRequestDTO requestToken) {
        Client client = authService.validateToken(requestToken.token());
        if (client != null) {
            return ResponseEntity.ok().body(client);
        }
        return ResponseEntity.status(401).body(null);
    }

}
