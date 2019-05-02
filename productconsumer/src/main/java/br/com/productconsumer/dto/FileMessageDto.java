package br.com.productconsumer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("message")
public class FileMessageDto {

	@JsonProperty("name")
	private String name;
	@JsonProperty("content")
	private byte[] content;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the content
	 */
	public byte[] getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(byte[] content) {
		this.content = content;
	}
}