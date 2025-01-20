package excel;

import java.io.IOException;
import java.sql.SQLException;

public class MainClass {

	public static void main(String[] args) throws IOException, SQLException {
		Store_Into_DataBase database = new Store_Into_DataBase();
		Insert_Into_CSV csv = new Insert_Into_CSV();
		String result = database.insert_data(csv.insertcsv("A:\\Github\\BlueScope_Training\\Task3\\sample2.xlsx"));
		System.out.println(result);
	}
}