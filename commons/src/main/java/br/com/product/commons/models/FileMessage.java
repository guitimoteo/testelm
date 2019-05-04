package br.com.product.commons.models;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import br.com.product.commons.dtos.FileMessageDto;

@Document("message")
public class FileMessage {
	@Field("name")
	private String name;
	@Field("content")
	private byte[] content;
	@Field("token")
	private final int token = hashCode();

	public FileMessage(FileMessageDto fileDto) {
		super();
		this.name = fileDto.getName();
		this.content = fileDto.getContent();
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the content
	 */
	public byte[] getContent() {
		return content;
	}
	
	public int getToken() {
		return token;
	}
}