package br.com.ecommerce.store.cart.rest.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponseDTO {

    private ProductItemDTO product;

    private Integer quantity;

    private BigDecimal price;

}
