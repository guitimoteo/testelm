package br.com.productconsumer.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.productconsumer.models.Product;

public interface ProductRepository extends CrudRepository<Product, Double> {
}
