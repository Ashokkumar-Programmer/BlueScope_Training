package ftl;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class XMLReader {

	public String readxml(String filepath) throws IOException {
		FileReader file = new FileReader(new File(filepath));
		String xmlstring = "";
		int i = 0;
		while((i=file.read())!=-1) {
			xmlstring += (char)i;
		}
		
		return xmlstring;
	}

}
