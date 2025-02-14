package backend.chunk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.batch.item.ItemReader;

public class ChunkReader implements ItemReader<ArrayList<String>> {

	public static ArrayList<ArrayList<String>> DB() {
		ArrayList<ArrayList<String>> datas = new ArrayList<ArrayList<String>>();
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BlueScope", "root", "root");
			Statement st = con.createStatement();
			ResultSet result = st.executeQuery("select * from student");
			
			if(result.isBeforeFirst()) {
				while(result.next()) {
					ArrayList<String> data = new ArrayList<String>();
					data.add(String.valueOf(result.getLong("rollno")));
					data.add(result.getString("studentname"));
					data.add(result.getString("dept"));
					data.add(String.valueOf(result.getLong("phonenumber")));
					datas.add(data);
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return datas;
	}
	
	static ArrayList<ArrayList<String>> datas = DB();
	
	int index = 0;
	
	@Override
	public ArrayList<String> read() throws Exception {
		if(index==datas.size()) {
			index=0;
		}
		index++;
		return datas.get(index-1);
	}
}
