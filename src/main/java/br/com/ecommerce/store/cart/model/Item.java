package br.com.ecommerce.store.cart.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.ecommerce.store.product.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id",referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_cart_id"))
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id",referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_product_item_id"))
    private Product product;

    private Integer quantity;

    private BigDecimal price;

}
