package br.com.ecommerce.store.product.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerce.store.product.rest.dto.ProductDTO;
import br.com.ecommerce.store.product.service.ProductService;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<ProductDTO> getProducts(){
        return ResponseEntity.ok().build();
    }

}
