package br.com.productconsumer.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.productconsumer.models.Product;
import br.com.productconsumer.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	/**
	 * Insert products on database.
	 * @param products
	 */
	public void createProducts(List<Product> products) {
		productRepository.saveAll(products);
	}
}
