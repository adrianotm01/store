package br.com.ecommerce.store.product.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.ecommerce.store.product.db.ProductRepository;
import br.com.ecommerce.store.product.rest.dto.ProductDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final ObjectMapper objectMapper;

    public Page<ProductDTO> getProducts(Pageable pageable){

        productRepository.findAll(pageable).map(product -> objectMapper.convertValue(product, ProductDTO.class));
        return Page.empty();
    }

}
