package excel;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Store_Into_DataBase {
	
	private static String url = "jdbc:mysql://localhost:3306/BlueScope";
	private static String username = "root";
	private static String password = "root";
	private static String insert_query = null;
	private static String create_query = null;
	
	static Connection connect() throws SQLException {
		Connection con = DriverManager.getConnection(url, username, password);
		return con;
	}
	
	String insert_data(String read_filename) throws IOException, SQLException {
		ArrayList<ArrayList<String>> datas = new Read_Excel_Data().read_data(read_filename);
		String file[] = read_filename.split("\\\\");
		String filename = file[file.length-1].split(".csv")[0];
		create_query = "create table "+filename+"(";
		insert_query = "insert into "+filename+" values(";
		int values = 0;
		for(ArrayList<String> data: datas) {
			Iterator<String> iterator = data.iterator();
			while (iterator.hasNext()) {
	            String element = iterator.next();
	            if (!iterator.hasNext()) {
	            	create_query += element.replaceAll("[^a-zA-Z0-9]", "")+"_column varchar(200)";
	            	insert_query += "?";
	            }
	            else if (iterator.hasNext()) {
	            	create_query+=element.replaceAll("[^a-zA-Z0-9]", "")+"_column varchar(200), ";
	            	insert_query += "?,";
	            }
	            values++;
	        }
			create_query += ");";
			insert_query += ");";
			break;
		}
		
		datas.remove(0);
		Connection con = connect();
		PreparedStatement ps = null;
		ps = con.prepareStatement("drop table if exists "+filename);
		ps.executeUpdate();
		ps = con.prepareStatement(create_query);
		ps.executeUpdate();
		int rows = 1;
		for(ArrayList<String> data: datas) {
			int count = Collections.frequency(data, "No Value");
			if(count!=values) {
				ps = con.prepareStatement(insert_query);
				for(int i=0;i<values;i++) {
					ps.setString(i+1, data.get(i));
				}
				ps.executeUpdate();
				rows++;
			}
		}
		return rows+" EXCEL DATA INSERT INTO DATABASE SUCCESSFULLY";
	}
}