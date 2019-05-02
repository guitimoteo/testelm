package br.com.productconsumer.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import br.com.productconsumer.dto.FileMessageDto;

@Component
public class Receiver {

	private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

	@KafkaListener(topics = "${application.topic}")
	public void receive(FileMessageDto fileDto) {
		logger.info("receive: {}", fileDto.getName());
//		try {
//			ExcelExtractor.this.getText();
//		} catch (IOException e) {
//			logger.error("Erro ao converter o arquivo: {}, {}", e.getMessage(), e.getCause());
//			e.printStackTrace();
//		}
	}
}
