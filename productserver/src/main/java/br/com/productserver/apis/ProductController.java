package br.com.productserver.apis;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.productserver.dto.FileMessageDto;
import br.com.productserver.models.MessageResponse;
import br.com.productserver.models.Status;
import br.com.productserver.services.ProductService;

@RestController("api/v1/products")
public class ProductController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;

	@GetMapping(path = "/")
	public List<FileMessageDto> getProducts() {
		return null;
	}

	@GetMapping(path = "/id/{id}/status")
	public Status getStatus(@PathVariable(name = "id", required = true) String id) {
		
		return null;
	}
 
	@PostMapping(path = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public MessageResponse createProduct(@RequestParam(name = "file", required = true) MultipartFile fileMultipart) throws Exception {
		logger.debug("createProduct:{}",fileMultipart.getOriginalFilename());
		productService.sendFile(fileMultipart);
		return null;
	}
	
	@PutMapping(path="/id/{id}")
	public MessageResponse updateProduct(@PathVariable(name="id", required = true) String id) {
		return null;
	}
	
	@DeleteMapping(path="/id/{id}")
	public MessageResponse deleteProducts(String id) {
		return null;
	}
}
