package br.com.ecommerce.store.discount.db;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ecommerce.store.discount.model.Discount;

public interface DiscountRepository extends JpaRepository<Discount, Long> {

}
