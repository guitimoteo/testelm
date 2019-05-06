package br.com.productserver.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.productserver.apis.ProductController;
import br.com.productserver.models.MessageResponse;

/**
 * Handler de excecoes da aplicacao. Captura excecoes dos componentes e trata a reposta n api.
 */
@ControllerAdvice
public class ExceptionHandlerController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> Exception(Exception e) {
		logger.error("Erro interno {}:{}", e.getMessage(), e.getCause());
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<?> resourceNotFoundException(BadRequestException e) {
		return new ResponseEntity<>(new MessageResponse(e.getMessage()),HttpStatus.NOT_FOUND);
	}
}
