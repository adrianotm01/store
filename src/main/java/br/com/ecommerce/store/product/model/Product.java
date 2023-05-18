package br.com.ecommerce.store.product.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.ecommerce.store.discount.model.Discount;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private BigDecimal price;

    private String name;

    @ManyToOne
    @JoinColumn(name = "discount_id",referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_discount_id"))
    private Discount discount;
}
