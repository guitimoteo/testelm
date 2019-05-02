package br.com.productconsumer.models;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbookFactory;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import br.com.productconsumer.dto.FileMessageDto;

public class ExcelReader {

	private Workbook workbook;

	public ExcelReader(FileMessageDto fileDto) {
		try {
			this.workbook = HSSFWorkbookFactory.create(new ByteArrayInputStream(fileDto.getContent()));
		} catch (EncryptedDocumentException | IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Product> listProducts() {
		Sheet sheet = workbook.getSheetAt(0);
		Double category = sheet.getRow(0).getCell(1).getNumericCellValue();
		sheet.forEach(r-> new Product(category, r.getCell(0).getStringCellValue(), r.getCell(1).getStringCellValue(), r.getCell(2).getBooleanCellValue(), r.getCell(3).getStringCellValue(), r.getCell(4).getNumericCellValue()));
		return null;
	}
}