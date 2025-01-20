package excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Insert_Into_CSV {
	String insertcsv(String read_filename) throws IOException {
		
		ArrayList<ArrayList<String>> datas = new Read_Excel_Data().read_data(read_filename);
		String write_filename = read_filename.split(".xlsx|.xls")[0]+".csv";
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sh = wb.createSheet("student data");
		
		XSSFRow row;

		int rowid = 0;
		
		for(ArrayList<String> data: datas) {
			row = sh.createRow(rowid++);
			int cellid = 0;
			for(String value: data) {
				Cell cell = row.createCell(cellid++);
				cell.setCellValue(value);
			}
		}
		
		FileOutputStream out = new FileOutputStream(new File(write_filename));
		wb.write(out);
		out.close();
		return write_filename;
	}
}