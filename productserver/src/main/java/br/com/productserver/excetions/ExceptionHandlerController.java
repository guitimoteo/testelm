package br.com.productserver.excetions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.productserver.apis.ProductController;

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

	@ExceptionHandler(InvalidConfigurationPropertyValueException.class)
	public ResponseEntity<?> resourceNotFoundException(InvalidConfigurationPropertyValueException exception) {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
