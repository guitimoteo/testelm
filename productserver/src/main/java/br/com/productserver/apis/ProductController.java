package br.com.productserver.apis;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.product.commons.dtos.FileMessageDto;
import br.com.product.commons.dtos.ProductDto;
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

	@GetMapping(path = "/token/{token}/status")
	public ResponseEntity<MessageResponse> getStatus(@PathVariable(name = "token", required = true) Integer id) {
		Status status = productService.getStatus(id);
		return new ResponseEntity<MessageResponse>(new MessageResponse(status.getMessage()), status.getHttpStatus());
	}
 
	@PostMapping(path = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<MessageResponse> createProduct(@RequestParam(name = "file", required = true) MultipartFile fileMultipart) throws Exception {
		logger.debug("createProduct:{} ", fileMultipart.getOriginalFilename());
		int token = productService.sendFile(fileMultipart);
		MultiValueMap<String, String> headers = new HttpHeaders();
		headers.add(HttpHeaders.LOCATION, String.format("api/v1/products/tokens/%s/status", token));
		return new ResponseEntity<MessageResponse>(new MessageResponse("File in process..."), headers, HttpStatus.ACCEPTED);
	}
	
	@PutMapping(path="/id/{id}")
	public ResponseEntity<MessageResponse> updateProduct(@PathVariable(name="id", required = true) Double id, ProductDto productDto) {
		
		return new ResponseEntity<MessageResponse>(new MessageResponse("Product updated"), HttpStatus.OK);
	}
	
	@DeleteMapping(path="/id/{id}")
	public ResponseEntity<MessageResponse> deleteProduct(Double id) {
		productService.delete(id);
		return new ResponseEntity<MessageResponse>(new MessageResponse("Product deleted"), HttpStatus.OK);
	}
}
