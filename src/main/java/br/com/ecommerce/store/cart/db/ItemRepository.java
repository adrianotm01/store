package br.com.ecommerce.store.cart.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ecommerce.store.cart.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByCartId(Long id);

}
