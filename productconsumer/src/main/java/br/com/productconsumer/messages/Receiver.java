package br.com.productconsumer.messages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import br.com.product.commons.dtos.FileMessageDto;
import br.com.productconsumer.models.ExcelReader;
import br.com.productconsumer.services.ProductService;

@Component
public class Receiver {

	private static final Logger logger = LoggerFactory.getLogger(Receiver.class);
	
	@Autowired
	private ProductService productService;
	
	@KafkaListener(topics = "${application.topic}")
	public void receive(FileMessageDto fileDto) {
		logger.info("receive: {}", fileDto.getName());
		try {
			productService.createProducts(new ExcelReader(fileDto).listProducts());
		} catch (RuntimeException e) {
			logger.error("Erro ao converter o arquivo: {}, {}", e.getMessage(), e.getCause());
			e.printStackTrace();
			throw e;
		}
	}
}
