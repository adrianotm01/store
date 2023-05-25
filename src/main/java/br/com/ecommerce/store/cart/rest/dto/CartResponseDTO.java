package br.com.ecommerce.store.cart.rest.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartResponseDTO {

    private Long id;

    private BigDecimal totalPrice;

    private List<ItemResponseDTO> items;

}
