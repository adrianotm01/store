package br.com.ecommerce.store.cart.db;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ecommerce.store.cart.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
