package br.com.ecommerce.store.cart.rest.dto;

import java.math.BigDecimal;

import br.com.ecommerce.store.discount.rest.dto.DiscountDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductItemDTO {

    private Long id;

    private DiscountDTO discount;

}
