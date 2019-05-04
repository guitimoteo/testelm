package br.com.productconsumer.models;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbookFactory;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import br.com.productconsumer.dto.FileMessageDto;

public class ExcelReader {

	private Sheet sheet;

	public ExcelReader(FileMessageDto fileDto) {
		try {
			this.workbook = HSSFWorkbookFactory.create(new ByteArrayInputStream(fileDto.getContent()));
			sheet = workbook.getSheetAt(0);
			this.workbook.close();
		} catch (EncryptedDocumentException | IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Product> listProducts() {
		Double category = sheet.getRow(0).getCell(1).getNumericCellValue();
		List<Product> products = new ArrayList<>();
		sheet.forEach(r -> {if(r.getRowNum() > 2) products.add(new Product(category, r));});
		return products;
	}
}