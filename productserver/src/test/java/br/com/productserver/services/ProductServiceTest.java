package br.com.productserver.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import br.com.product.commons.dtos.FileMessageDto;
import br.com.product.commons.dtos.ProductDto;
import br.com.product.commons.repositories.FileMessageRepository;
import br.com.product.commons.repositories.ProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
@ContextConfiguration(classes = { ProductService.class, 
								  ProductRepository.class})
@WebAppConfiguration
public class ProductServiceTest {

	@InjectMocks
	private ProductService coletaService;
	
	@MockBean
	private ProductRepository coletaRespository;

	@MockBean
	private FileMessageRepository fileMessageRepository;
	
	@MockBean
	private KafkaTemplate<String, FileMessageDto> template;

	private ProductDto consulta;

	@Before
	public void setUp() throws Exception {				
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void should() throws Exception {
		
	}
}