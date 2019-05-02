package br.com.productserver.dto;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("message")
public class FileMessageDto {

	@JsonProperty("name")
	private String name;
	@JsonProperty("content")
	private byte[] bytes;
	
	public FileMessageDto(MultipartFile file) {
		super();
		try {
			this.name = file.getName();
			this.bytes = file.getBytes();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}