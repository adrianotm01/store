package br.com.ecommerce.store;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.ecommerce.store.discount.db.DiscountRepository;
import br.com.ecommerce.store.discount.model.Discount;
import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class StoreApplication {

	private final DiscountRepository discountRepository;

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner initDiscount(){
		return (test) -> {
			discountRepository.save(new Discount(null, "Leve 2 e Pague 1"));
			discountRepository.save(new Discount(null, "3 por R$10,00"));
		};
	}

}
