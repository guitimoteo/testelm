package br.com.productconsumer.messages;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import br.com.productconsumer.dto.FileMessageDto;
import br.com.productconsumer.models.ExcelReader;
import br.com.productconsumer.models.Product;

@Component
public class Receiver {

	private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

	@KafkaListener(topics = "${application.topic}")
	public void receive(FileMessageDto fileDto) {
		logger.info("receive: {}", fileDto.getName());
		try {
			List<Product> products = new ExcelReader(fileDto).listProducts();
		} catch (RuntimeException e) {
			logger.error("Erro ao converter o arquivo: {}, {}", e.getMessage(), e.getCause());
			e.printStackTrace();
		}
	}
}
