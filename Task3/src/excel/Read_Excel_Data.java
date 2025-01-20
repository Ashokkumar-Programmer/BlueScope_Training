package excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Read_Excel_Data{
	
	ArrayList<ArrayList<String>> read_data(String filename) throws IOException{ 
		ArrayList<ArrayList<String>> datas = null; 
		datas = new ArrayList<ArrayList<String>>();
		File file = new File(filename);
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);   
		XSSFSheet sheet = wb.getSheetAt(0);
		Iterator<Row> itr = sheet.iterator(); 
		while (itr.hasNext())   
		{  
			ArrayList<String> insert = new ArrayList<String>();
			Row row = itr.next();  
			Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext())   
			{  
				Cell cell = cellIterator.next();  
				switch (cell.getCellType())
				{  
					case Cell.CELL_TYPE_BLANK:
						insert.add("No Value");
					
					case Cell.CELL_TYPE_STRING:
						insert.add(String.valueOf(cell.getStringCellValue()));
						break;  
					case Cell.CELL_TYPE_NUMERIC:
						insert.add(String.valueOf(new BigDecimal(cell.getNumericCellValue()).toBigInteger()));
						break;
				} 
			}  
			datas.add(insert);
		}  
		return datas;
	}
}