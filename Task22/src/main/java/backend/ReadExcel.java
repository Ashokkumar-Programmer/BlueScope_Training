package backend;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	
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
					data.add(String.valueOf(cell.getNumericCellValue()));
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
	
	static void send_mail(String filename, String userrole, String tomail) throws Exception {
        String sender = "ashokkumarprogrammer@gmail.com"; 
        String host = "smtp.gmail.com";
        int port = 587;
        final String username = "ashokkumarprogrammer@gmail.com";
        final String password = "unah aeni fnxn hmgn";

        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        
        MimeMessage message = new MimeMessage(session);

        message.setFrom(new InternetAddress(sender));

        message.addRecipient(Message.RecipientType.TO, new InternetAddress(tomail));

        message.setSubject("Mail for Updating Bank User Status");

        BodyPart messageBodyPart = new MimeBodyPart();
        
        LocalTime currentTime = LocalTime.now();
        int hour = currentTime.getHour();
        String starting = null;
        if (hour >= 5 && hour < 12) {
        	starting = "Good morning";
        } else if (hour >= 12 && hour < 18) {
        	starting = "Good afternoon";
        } else {
        	starting = "Good evening";
        }
        
        messageBodyPart.setText(starting+" Sir/Madam,\n"+userrole.substring(0, 1).toUpperCase() + userrole.substring(1)+" Kindly update the bank user status in excel then upload the excel file into database.\nHere I attached an Excel document that contains bank user details.");

        Multipart multipart = new MimeMultipart();

        multipart.addBodyPart(messageBodyPart);

        messageBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(filename);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(userrole.substring(0, 1).toUpperCase() + userrole.substring(1)+".xlsx");
        multipart.addBodyPart(messageBodyPart);

        message.setContent(multipart);

        Transport.send(message);
        
        System.out.println("Mail sent to "+userrole);
	}
	
	public void write_file(String filename, String userrole) throws Exception{
		
		ArrayList<ArrayList<String>> datas = read_write_excel_file(userrole);
		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BlueScope", "root", "root");
		
		PreparedStatement ps = null;
		
		ps = null;
		
        ResultSet rs = null;
        
        ArrayList<ArrayList<String>> database_datas = new ArrayList<ArrayList<String>>();
        
        if(datas!=null) {
        	datas.remove(0);
        	for(ArrayList<String> data: datas) {
        		ps = con.prepareStatement("update bank set status = ? where id = ?");
        		ps.setString(1, data.get(5));
        		ps.setInt(2, Integer.parseInt(data.get(0)));
        		ps.executeUpdate();
        		Workbook workbook = WorkbookFactory.create(new FileInputStream(filename));
        		Sheet sheet = workbook.getSheetAt(0);
        		for (Row row : sheet) {
        	        for (Cell cell : row) {
        	            cell.setCellValue("");
        	        }
        	    }
        		FileOutputStream outputStream = new FileOutputStream(filename);
        	    workbook.write(outputStream);
        	    outputStream.close();
        	}
        }
        
        ps = con.prepareStatement("select * from bank where status = ?");
        
        if(userrole.equals("maker")) {
    		ps.setString(1, "new");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ArrayList<String> value = new ArrayList<String>();
				value.add(rs.getString("id"));
				value.add(rs.getString("name"));
				value.add(rs.getString("party_code"));
				value.add(rs.getString("parent_code"));
    			value.add(rs.getString("entity_code"));
    			value.add(rs.getString("status"));
    			database_datas.add(value);
			}
			Workbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet("Sheet1");
			Row head = sheet.createRow(0);
			head.createCell(0).setCellValue("id");
			head.createCell(1).setCellValue("name");
			head.createCell(2).setCellValue("party_code");
			head.createCell(3).setCellValue("parent_code");
			head.createCell(4).setCellValue("entity_code");
			head.createCell(5).setCellValue("status");
			int rowNum = 1;
	        for (ArrayList<String> rowData : database_datas) {
	            Row row = sheet.createRow(rowNum++);
	            int cellNum = 0;
	            for (String cellData : rowData) {
	                Cell cell = row.createCell(cellNum++);
	                cell.setCellValue(cellData);
	            }
	        }
	        FileOutputStream outputStream = new FileOutputStream(filename);
	        workbook.write(outputStream);
    	}
    	else if(userrole.equals("checker")) {
    		ps.setString(1, "pending");
			rs = ps.executeQuery();

			while(rs.next()) {
				ArrayList<String> value = new ArrayList<String>();
				value.add(rs.getString("id"));
				value.add(rs.getString("name"));
				value.add(rs.getString("party_code"));
				value.add(rs.getString("parent_code"));
    			value.add(rs.getString("entity_code"));
    			value.add(rs.getString("status"));
    			database_datas.add(value);
			}
			
			Workbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet("Sheet1");
			Row head = sheet.createRow(0);
			head.createCell(0).setCellValue("id");
			head.createCell(1).setCellValue("name");
			head.createCell(2).setCellValue("party_code");
			head.createCell(3).setCellValue("parent_code");
			head.createCell(4).setCellValue("entity_code");
			head.createCell(5).setCellValue("status");
			int rowNum = 1;
	        for (ArrayList<String> rowData : database_datas) {
	            Row row = sheet.createRow(rowNum++);
	            int cellNum = 0;
	            for (String cellData : rowData) {
	                Cell cell = row.createCell(cellNum++);
	                cell.setCellValue(cellData);
	            }
	        }
	        FileOutputStream outputStream = new FileOutputStream(filename);
	        workbook.write(outputStream);
    	}
        System.out.println(database_datas);
        if(database_datas.size()!=0) {
            if(userrole.equals("maker")) {
            	send_mail(filename, userrole, "ashokkumarrough@gmail.com");
            }else {
            	send_mail(filename, userrole, "ashokkumar.pg23.mca@francisxavier.ac.in");
            }
        }

	}
	
	static ArrayList<ArrayList<String>> read_write_excel_file(String userrole) throws Exception{
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BlueScope", "root", "root");
        
        PreparedStatement ps = con.prepareStatement("select * from files where userrole=?");
        ps.setString(1, userrole);
        ResultSet rs = ps.executeQuery();
        
        ArrayList<ArrayList<String>> datas = new ArrayList<ArrayList<String>>();
        if (rs.next()) {
            String outputFilePath = "C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\" + rs.getString("userrole") + "_file.xlsx";
            byte[] filedata = rs.getBytes("upload_file");
            File file = new File(outputFilePath);
            file.getParentFile().mkdirs();
            
            if(filedata==null) {
            	return null;
            }
            
            FileOutputStream writefile = new FileOutputStream(file);
            writefile.write(filedata);
            writefile.close();
            
            datas = read_file(outputFilePath);
        }
        con.close();
        return datas;
	}
}
