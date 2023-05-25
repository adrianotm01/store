package br.com.ecommerce.store.cart.rest.dto;

import java.math.BigDecimal;

import br.com.ecommerce.store.product.rest.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {

    private ProductItemDTO product;

    private Integer quantity;

    private CartRequestDTO cart;

    private BigDecimal price;

}
