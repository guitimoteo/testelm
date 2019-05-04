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
import br.com.product.commons.repositories.ProductRepository;
import br.com.productserver.models.Status;

@Service
public class ProductService {

	private static final Logger logger = LoggerFactory.getLogger(ProductService .class);

	@Autowired
	private KafkaTemplate<String, FileMessageDto> template;
	
	@Autowired
	private ProductRepository productRepository;

	public int sendFile(MultipartFile fileMultipart) throws EncryptedDocumentException, IOException {
		logger.debug("sendFile: {}", fileMultipart.getName());
		FileMessageDto fileDto = new FileMessageDto(fileMultipart);
		template.send("topic.productsfile",fileDto);
		int token = fileDto.hashCode();
		return token;
	}
	
	public Status getStatus(int id) {
		return null;
	}

	public void update(Double id, ProductDto productDto) {
	}
	
	public void delete(Double id) {
		productRepository.deleteById(id);
	}
}
