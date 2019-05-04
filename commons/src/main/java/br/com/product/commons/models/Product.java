package br.com.product.commons.models;

import org.apache.poi.ss.usermodel.Row;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.mongodb.lang.NonNull;

@Document("products")
public class Product {
	@Id
	@NonNull
	@Field("lm")
	private Double lm;
	@NonNull
	@Field("category")
	private Double category;
	@NonNull
	@Field("name")
	private String name;
	@Field("free_shipping")
	private Boolean freeShipping;	
	@Field("description")
	private String description;	
	@Field("price")
	private Double price;
	
	public Product(Double category, Row row) {
		super();
		this.category = category;
		this.lm = row.getCell(0).getNumericCellValue();
		this.name = row.getCell(1).getStringCellValue();
		this.freeShipping = row.getCell(2).getNumericCellValue() == 1;
		this.description = row.getCell(3).getStringCellValue();
		this.price = Double.valueOf(row.getCell(4).getStringCellValue());
	}
}