package br.com.productconsumer.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.product.commons.models.FileMessage;
import br.com.product.commons.models.Product;
import br.com.product.commons.models.FileMessage.Status;
import br.com.product.commons.repositories.FileMessageRepository;
import br.com.product.commons.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private FileMessageRepository fileMessageRepository;
	
	/**
	 * Insert products on database.
	 * @param products
	 */
	public void createProducts(List<Product> products) {
		productRepository.saveAll(products);
	}
	
	public void saveFileMesssage(FileMessage fileMessage) {
		fileMessage.setStatus(Status.OK);
		fileMessageRepository.save(fileMessage);
	}
}
