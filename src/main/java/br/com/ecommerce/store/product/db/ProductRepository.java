package br.com.ecommerce.store.product.db;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ecommerce.store.product.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

}
