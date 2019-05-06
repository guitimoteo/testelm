package br.com.productconsumer.messages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import br.com.product.commons.dtos.FileMessageDto;
import br.com.product.commons.models.FileMessage;
import br.com.product.commons.models.FileMessage.Status;
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
		FileMessage fileMessage = new FileMessage(fileDto);
		try {
			productService.createProducts(new ExcelReader(fileDto).listProducts());
			fileMessage.setStatus(Status.OK);
		} catch (RuntimeException e) {
			logger.error("Erro ao converter o arquivo: {}, {}", e.getMessage(), e.getCause());
			fileMessage.setStatus(Status.FAILED);
			throw e;
		} finally {
			productService.saveFileMesssage(fileMessage);
		}
	}
}
