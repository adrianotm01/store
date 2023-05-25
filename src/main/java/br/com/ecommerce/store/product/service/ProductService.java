package br.com.ecommerce.store.product.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.ecommerce.store.exception.NotFoundException;
import br.com.ecommerce.store.product.db.ProductRepository;
import br.com.ecommerce.store.product.model.Product;
import br.com.ecommerce.store.product.rest.dto.ProductDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final ObjectMapper objectMapper;

    public Page<ProductDTO> getProducts(Pageable pageable){
        Page<ProductDTO> productDTOS = productRepository.findAll(pageable).map(product -> objectMapper.convertValue(product, ProductDTO.class));
        return productDTOS;
    }

    public ProductDTO getProductById(Long id){
        ProductDTO productDTO =  objectMapper.convertValue(productRepository.findById(id).orElseThrow(()-> new NotFoundException("Produto não encontrado")), ProductDTO.class);
        return productDTO;
    }

    public void updateProduct(Long id, ProductDTO productDTOToUpdate){
        Product productOutDated = productRepository.findById(id).orElseThrow();
        productOutDated.setPrice(productDTOToUpdate.getPrice());
        productOutDated.setName(productDTOToUpdate.getName());
        productRepository.save(productOutDated);
    }

    public void deleteProduct(Long id){
        productRepository.delete(
                productRepository.findById(id).orElseThrow(()-> new NotFoundException("Produto não encontrado"))
        );
    }

    public void createProduct(ProductDTO productDTO) {
        Product product = objectMapper.convertValue(productDTO,Product.class);
        productRepository.save(product);
    }
}
