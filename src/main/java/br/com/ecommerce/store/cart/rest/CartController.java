package br.com.ecommerce.store.cart.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerce.store.cart.rest.dto.CartResponseDTO;
import br.com.ecommerce.store.cart.rest.dto.ItemDTO;
import br.com.ecommerce.store.cart.service.CartService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<Void> createCart(@RequestBody CartResponseDTO cartResponseDTO){
        return ResponseEntity.ok().build();
    }

    @PostMapping("/items")
    public ResponseEntity<Void> addItem(@RequestBody ItemDTO itemDTO){
        return ResponseEntity.ok().build();
    }

}
