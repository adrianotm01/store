package br.com.ecommerce.store.product;

import static br.com.ecommerce.store.utils.ProductMocks.mockProduct;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.ecommerce.store.product.db.ProductRepository;
import br.com.ecommerce.store.product.model.Product;
import br.com.ecommerce.store.product.rest.dto.ProductDTO;
import br.com.ecommerce.store.product.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProductServiceTest {

    @InjectMocks
    ProductService productService;

    @Mock
    ProductRepository productRepository;

    @Captor
    ArgumentCaptor<Product> productCaptor;

    @Mock
    private ObjectMapper mapper;


    @BeforeEach
    public void setUpMocks(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldSaveOneProduct(){
        Product mockProduct = mockProduct();
        ProductDTO mockDTO = new ProductDTO(BigDecimal.ONE,"Celu", null);
        when(mapper.convertValue(any(), eq(Product.class))).thenReturn(mockProduct);
        productService.createProduct(mockDTO);
        verify(productRepository).save(productCaptor.capture());
        Product product = productCaptor.getValue();
        assertEquals("Notebook test", product.getName());
        assertEquals(BigDecimal.TEN,product.getPrice());
    }

}
