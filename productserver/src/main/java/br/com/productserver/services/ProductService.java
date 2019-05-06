package br.com.productserver.services;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.multipart.MultipartFile;

import br.com.product.commons.dtos.FileMessageDto;
import br.com.product.commons.dtos.ProductDto;
import br.com.product.commons.models.FileMessage;
import br.com.product.commons.models.FileMessage.Status;
import br.com.product.commons.models.Product;
import br.com.product.commons.repositories.FileMessageRepository;
import br.com.product.commons.repositories.ProductRepository;
import br.com.productserver.exceptions.BadRequestException;

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
		FileMessage fileMessage = new FileMessage(fileDto);
		template.send("topic.productsfile",fileDto).addCallback(new ListenableFutureCallback<SendResult<String, FileMessageDto>>() {

			@Override
			public void onSuccess(SendResult<String, FileMessageDto> result) {
				fileMessage.setStatus(Status.IN_PROCESS);
			}

			@Override
			public void onFailure(Throwable ex) {
				fileMessage.setStatus(Status.FAILED);
				logger.error("Erro ao enviar a mensagem {}: {}", ex.getMessage(), ex.getCause());
			}
		});
		return fileMessageRepository.save(fileMessage).getToken();
	}
	
	public Status getStatus(int id) {
		if(!fileMessageRepository.existsById(id))
			throw new BadRequestException("File doesn't exist");
		return fileMessageRepository.findStatusById(id);
	}

	public void update(ProductDto productDto) {
		Product product = new Product(productDto);
		if(!productRepository.existsById(product.getLm()))
			throw new BadRequestException("Product doesn't exist");
		productRepository.save(product);
	}
	
	public void delete(Double id) {
		if(!productRepository.existsById(id))
			throw new BadRequestException("Product doesn't exist");
		productRepository.deleteById(id);
	}
}