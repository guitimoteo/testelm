package br.com.productserver.models;

import org.springframework.http.HttpStatus;

public enum Status {
	IN_PROCESS("In progress...", HttpStatus.PROCESSING),
	OK("Done", HttpStatus.OK),
	FAILED("There is something wrong.", HttpStatus.UNPROCESSABLE_ENTITY);

	private String message;
	private HttpStatus httpStatus;
	Status(String message, HttpStatus httpStatus) {
		this.message = message;
		this.httpStatus = httpStatus;
	}
	public String getMessage() {
		return message;
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}