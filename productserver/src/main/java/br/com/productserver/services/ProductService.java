package br.com.productserver.services;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.product.commons.dtos.FileMessageDto;
import br.com.product.commons.dtos.ProductDto;
import br.com.product.commons.models.FileMessage;
import br.com.product.commons.models.FileMessage.Status;
import br.com.product.commons.models.Product;
import br.com.product.commons.repositories.FileMessageRepository;
import br.com.product.commons.repositories.ProductRepository;

@Service
public class ProductService {

	private static final Logger logger = LoggerFactory.getLogger(ProductService .class);

	@Autowired
	private KafkaTemplate<String, FileMessageDto> template;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private FileMessageRepository fileMessageRepository;

	public int sendFile(MultipartFile fileMultipart) throws EncryptedDocumentException, IOException {
		logger.debug("sendFile: {}", fileMultipart.getName());
		FileMessageDto fileDto = new FileMessageDto(fileMultipart);
		template.send("topic.productsfile",fileDto);
		return fileMessageRepository.save(new FileMessage(fileDto)).getToken();
	}
	
	public Status getStatus(int id) {
		return fileMessageRepository.findStatusById(id);
	}

	public void update(Double id, ProductDto productDto) {
		productRepository.save(new Product(productDto));
	}
	
	public void delete(Double id) {
		productRepository.deleteById(id);
	}
}
