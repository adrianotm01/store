package br.com.ecommerce.store.product.rest.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductDTO {

    private BigDecimal price;

    private String name;

}
