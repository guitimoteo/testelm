package br.com.product.commons.dtos;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("message")
public class FileMessageDto {

	@JsonProperty("name")
	private String name;
	@JsonProperty("content")
	private byte[] content;
	@JsonProperty("token")
	private final int token = hashCode();
	public FileMessageDto(MultipartFile file) {
		super();
		try {
			this.name = file.getName();
			this.content = file.getBytes();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
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
}