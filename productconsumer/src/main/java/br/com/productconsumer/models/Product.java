package br.com.productconsumer.models;

import org.apache.poi.ss.usermodel.Row;

public class Product {
	
	private Double category;
	private Double lm;
	private String name;
	private Boolean free_shipping;	
	private String description;	
	private Double price;
	
	public Product(Double category, Row row) {
		super();
		this.category = category;
		this.lm = row.getCell(0).getNumericCellValue();
		this.name = row.getCell(1).getStringCellValue();
		this.free_shipping = row.getCell(2).getNumericCellValue() == 1;
		this.description = row.getCell(3).getStringCellValue();
		this.price = Double.valueOf(row.getCell(4).getStringCellValue());
	}
}