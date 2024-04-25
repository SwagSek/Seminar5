package lv.venta;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lv.venta.model.Product;
import lv.venta.repo.IProductRepo;

@SpringBootApplication
public class Seminar5Application {

	public static void main(String[] args) {
		SpringApplication.run(Seminar5Application.class, args);
	}

	@Bean
	public CommandLineRunner testModelLayer(IProductRepo productRepo) {
		return new CommandLineRunner() {
			public void run(String... args) {
				Product p1 = new Product("Abols", "Zali", 0.99f, 10);
				Product p2 = new Product("Arbuzs", "Roza", 3.99f, 15);
				Product p3 = new Product("Vinogas", "Zalas", 5.99f, 50);
				
				productRepo.save(p1);
				productRepo.save(p2);
				productRepo.save(p3);
				
			}
		};
	}
}
