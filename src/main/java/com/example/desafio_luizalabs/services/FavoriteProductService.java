package com.example.desafio_luizalabs.services;

import com.example.desafio_luizalabs.domain.Client;
import com.example.desafio_luizalabs.domain.FavoriteList;
import com.example.desafio_luizalabs.domain.FavoriteProduct;
import com.example.desafio_luizalabs.dtos.FavoriteProductRequestDTO;
import com.example.desafio_luizalabs.exception.ProductNotExistsException;
import com.example.desafio_luizalabs.exception.TotalProductListExceeded;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class FavoriteProductService {

    @Autowired
    private ClientService clientService;

    @Autowired
    private RestTemplate restTemplate;

    public Client favorite(FavoriteProductRequestDTO dto) {
        if (!existsById(dto.product().getId().toString())) {
            throw new ProductNotExistsException("O produto não existe, portanto não é possível adiciona-lo");
        }

        Client client = clientService.findById(UUID.fromString(dto.clientId()));

        FavoriteList favoriteList = client.getFavoriteList();
        if (favoriteList == null) {
            favoriteList = new FavoriteList();
            favoriteList.setId(UUID.randomUUID());
            client.setFavoriteList(favoriteList);
        } else if (!favoriteList.getProducts().isEmpty() && favoriteList.getProducts().size() >= 5) {
            throw new TotalProductListExceeded("Só é permitido favoritar 5 ítens por lista");
        }

        if (favoriteList.getProducts().stream()
                .anyMatch(product -> product.getId().equals(dto.product().getId()))) {
            throw new EntityExistsException("O produto já existe na lista.");
        }

        FavoriteProduct favoriteProduct = createFavoriteProductFromDTO(dto);
        favoriteList.addProduct(favoriteProduct);

        return clientService.save(client);
    }

    private FavoriteProduct createFavoriteProductFromDTO(FavoriteProductRequestDTO dto) {
        FavoriteProduct favoriteProduct = new FavoriteProduct();
        favoriteProduct.setId(dto.product().getId());
        favoriteProduct.setImage(dto.product().getImage());
        favoriteProduct.setPrice(dto.product().getPrice());
        favoriteProduct.setTitle(dto.product().getTitle());
        return favoriteProduct;
    }

    public boolean existsById(String productId) {
        String url = System.getenv("FAKE_API_URL");
        return restTemplate.getForObject(url, String.class, productId) != null;
    }

    public Client removeFavorite(FavoriteProductRequestDTO dto) {
        Client client = clientService.findById(UUID.fromString(dto.clientId()));

        if (client.getFavoriteList() == null) {
            throw new EntityNotFoundException("Lista de favoritos não encontrada. Não foi possível remover o produto.");
        }
        FavoriteList favoriteList = client.getFavoriteList();
        favoriteList.getProducts().removeIf(product -> dto.product().getId().equals(product.getId()));
        client.setFavoriteList(favoriteList);
        return clientService.save(client);
    }
}
