package br.com.product.commons.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.http.HttpStatus;

import br.com.product.commons.dtos.FileMessageDto;

@Document("message")
public class FileMessage {
	@Id
	@Field("token")
	private int token;

	@Field("name")
	private String name;
	
	@Field("status")
	private Status status;
	
	public FileMessage(FileMessageDto fileDto) {
		super();
		this.name = fileDto.getName();
		this.token = fileDto.hashCode();
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	public int getToken() {
		return token;
	}
	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}	
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
}