package backend;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class FileHandle implements Tasklet {

	static String csv_filename = "A:\\Github\\BlueScope_Training\\Task20\\src\\main\\resources\\Student_Data.csv";
	static String excel_filename = "A:\\Github\\BlueScope_Training\\Task20\\src\\main\\resources\\Student_Data.xlsx";
	
	static ArrayList<ArrayList<String>> read_file(String filename) throws Exception {
		FileInputStream file = new FileInputStream(new File(filename));
		
		Workbook excelfile = new XSSFWorkbook(file);
		
		Sheet sheet = excelfile.getSheetAt(0);
		
		ArrayList<ArrayList<String>> excel_data = new ArrayList<>();
		
		for(Row row: sheet) {
			ArrayList<String> data = new ArrayList<>();
			for(Cell cell: row) {
				switch(cell.getCellType()) {
				case STRING:
					data.add(cell.getStringCellValue());
					break;
				case NUMERIC:
					data.add(String.valueOf(new BigDecimal(cell.getNumericCellValue()).toBigInteger()));
					break;
				default:
					break;
				}
			}
			if(!data.isEmpty()) {
				excel_data.add(data);
			}
		}
		return excel_data;
	}
	
	static void write_csv() throws Exception{
		
		ArrayList<ArrayList<String>> datas = read_file(excel_filename);
		
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
		try {
			FileOutputStream out = new FileOutputStream(new File(csv_filename));
			wb.write(out);
			out.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception{
    	ArrayList<ArrayList<String>> exceldata = read_file(excel_filename);
		ArrayList<ArrayList<String>> csvdata = read_file(csv_filename);
		
		boolean change = false;
		
		if(csvdata.size()==exceldata.size()) {
			for(int i=0;i<exceldata.size();i++) {
				if(!csvdata.contains(exceldata.get(i))) {
					write_csv();
					change = true;
				}
			}
		}
		else {
			write_csv();
			change = true;
		}
		
		if(change) {
			System.out.println("Changes in content of Excel File.\nCSV file written successfully");
		}else{
			System.out.println("No changes in content of Excel File");
		}
        return RepeatStatus.FINISHED;
    }
}


