package br.com.productserver.services;

import static org.mockito.Mockito.when;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.rule.EmbeddedKafkaRule;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.multipart.MultipartFile;

import br.com.product.commons.dtos.FileMessageDto;
import br.com.product.commons.dtos.ProductDto;
import br.com.product.commons.repositories.FileMessageRepository;
import br.com.product.commons.repositories.ProductRepository;
import br.com.productserver.exceptions.BadRequestException;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
@ContextConfiguration(classes = { ProductService.class, 
								  ProductRepository.class, 
								  FileMessageRepository.class})
@WebAppConfiguration
public class ProductServiceUnitTest {

	@InjectMocks
	private ProductService productService;
	
	@MockBean
	private ProductRepository productRespository;

	@MockBean
	private FileMessageRepository fileMessageRepository;
	@MockBean
	private KafkaTemplate<String, FileMessageDto> template;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
		
	@Test(expected = BadRequestException.class)
	public void shouldThrowBadRequestExceptionOnStatusRequest() throws Exception {
		when(fileMessageRepository.existsById(1)).thenReturn(false);
		productService.getStatus(1);
	}
	
	@Test(expected = BadRequestException.class)
	public void shouldThrowBadRequestExceptionOnSave() throws Exception {
		when(productRespository.existsById(1.0d)).thenReturn(false);
		productService.update(new ProductDto().withLm(1.0d));
	}
	
	@Test(expected = BadRequestException.class)
	public void shouldThrowBadRequestExceptionOnDelete() throws Exception {
		when(productRespository.existsById(1.0d)).thenReturn(false);
		productService.delete(1.0d);
	}
}