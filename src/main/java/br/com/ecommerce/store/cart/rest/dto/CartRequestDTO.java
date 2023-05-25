package br.com.ecommerce.store.cart.rest.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartRequestDTO {
    private Long id;

    private BigDecimal totalPrice;

}
