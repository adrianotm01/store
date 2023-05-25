package br.com.ecommerce.store.utils;

import java.math.BigDecimal;

import br.com.ecommerce.store.product.model.Product;

public class ProductMocks {

    public static Product mockProduct(){
        return new Product(1L, BigDecimal.TEN, "Notebook test", null);
    }

}
