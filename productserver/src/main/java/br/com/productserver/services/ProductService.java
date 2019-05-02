package br.com.productserver.services;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.productserver.dto.FileMessageDto;

@Service
public class ProductService {

	private static final Logger logger = LoggerFactory.getLogger(ProductService .class);

	@Autowired
	private KafkaTemplate<String, FileMessageDto> template;
	
	public void sendFile(MultipartFile fileMultipart) throws EncryptedDocumentException, IOException {
		logger.debug("sendFile: {}", fileMultipart.getName());
		template.send("topic.productsfile", new FileMessageDto(fileMultipart));
	}
}
