package br.com.product.commons.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("product")
public class ProductDto {
	@JsonProperty("lm")
	private Double lm;
	@JsonProperty("category")
	private Double category;
	@JsonProperty("name")
	private String name;
	@JsonProperty("free_shipping")
	private Boolean freeShipping;	
	@JsonProperty("description")
	private String description;	
	@JsonProperty("price")
	private Double price;
	/**
	 * @return the lm
	 */
	public Double getLm() {
		return lm;
	}
	/**
	 * @param lm the lm to set
	 */
	public ProductDto withLm(Double lm) {
		this.lm = lm;
		return this;
	}
	/**
	 * @return the category
	 */
	public Double getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public ProductDto withCategory(Double category) {
		this.category = category;
		return this;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public ProductDto withName(String name) {
		this.name = name;
		return this;
	}
	/**
	 * @return the freeShipping
	 */
	public Boolean getFreeShipping() {
		return freeShipping;
	}
	/**
	 * @param freeShipping the freeShipping to set
	 */
	public ProductDto withFreeShipping(Boolean freeShipping) {
		this.freeShipping = freeShipping;
		return this;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public ProductDto withDescription(String description) {
		this.description = description;
		return this;
	}
	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public ProductDto withPrice(Double price) {
		this.price = price;
		return this;
	}
}