package br.com.ecommerce.store.cart.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerce.store.cart.rest.dto.CartResponseDTO;
import br.com.ecommerce.store.cart.rest.dto.ItemDTO;
import br.com.ecommerce.store.cart.service.ItemService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public ResponseEntity<Void> addItem(@RequestBody ItemDTO itemDTO){
        itemService.createItem(itemDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/cart/{id}")
    public ResponseEntity<CartResponseDTO> getItems(@PathVariable Long id){
        return ResponseEntity.ok(itemService.getItemsByCart(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateItem(@PathVariable Long id, @RequestBody ItemDTO itemDTO){
        itemService.updateItem(id, itemDTO);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id){
        itemService.removeItem(id);
        return ResponseEntity.accepted().build();
    }

}
