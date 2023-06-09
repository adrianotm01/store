package br.com.ecommerce.store.product.rest.dto;

import java.math.BigDecimal;

import br.com.ecommerce.store.discount.rest.dto.DiscountDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private BigDecimal price;

    private String name;

    private DiscountDTO discount;

}
