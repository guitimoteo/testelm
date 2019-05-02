package br.com.productconsumer.models;

public class Product {
	
	private Double category;
	private String lm;
	private String name;
	private Boolean free_shipping;	
	private String description;	
	private Double price;
	
	public Product(Double category, String lm, String name, Boolean free_shipping, String description, Double price) {
		super();
		this.category = category;
		this.lm = lm;
		this.name = name;
		this.free_shipping = free_shipping;
		this.description = description;
		this.price = price;
	}
}