package br.com.product.commons.dtos;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("message")
public class FileMessageDto {

	@JsonProperty("name")
	@NotNull(message = "File should have a name")
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(content);
		result = prime * result + Objects.hash(name);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileMessageDto other = (FileMessageDto) obj;
		return Arrays.equals(content, other.content) && Objects.equals(name, other.name);
	}
}