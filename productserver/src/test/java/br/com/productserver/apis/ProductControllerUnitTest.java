package br.com.productserver.apis;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.product.commons.dtos.ProductDto;
import br.com.product.commons.repositories.FileMessageRepository;
import br.com.product.commons.repositories.ProductRepository;
import br.com.productserver.services.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
@ContextConfiguration(classes = { ProductController.class, 
								  ProductService.class, 
								  ProductRepository.class, 
								  FileMessageRepository.class})
@WebAppConfiguration
public class ProductControllerUnitTest {

	private static final Logger logger = LoggerFactory.getLogger(ProductControllerUnitTest.class);

	private MockMvc mockMvc;
	
	@InjectMocks
	private ProductController productController;
	
	@MockBean
	private ProductService productService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
	}
		
	@Test
	public void shouldUpdateProducts() throws Exception {
		ProductDto productStub = new ProductDto();
		String content = new ObjectMapper().writeValueAsString(productStub);
		MockHttpServletRequestBuilder request = put("/").contentType(MediaType.APPLICATION_JSON).content(content);
		mockMvc.perform(request).andExpect(status().is2xxSuccessful());
	}
	
	@Test
	public void shouldDeleteProducts() throws Exception {
		MockHttpServletRequestBuilder request = delete("/id/{id}", 1);
		mockMvc.perform(request).andExpect(status().is2xxSuccessful());
	}
}