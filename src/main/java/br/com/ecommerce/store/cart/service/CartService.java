package br.com.ecommerce.store.cart.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.ecommerce.store.cart.db.CartRepository;
import br.com.ecommerce.store.cart.model.Cart;
import br.com.ecommerce.store.cart.rest.dto.ItemDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CartService {

    private final ObjectMapper objectMapper;

    private final CartRepository cartRepository;

    public Optional<Cart> findCartById(Long id){
        return cartRepository.findById(id);
    }
}
