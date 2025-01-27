package xmlreader;

import java.io.FileReader;

public class XMLReader{
	public String read_xml(String filename) throws Exception{
		FileReader file = new FileReader(filename);
		int i;
		String XMLFile = "";
		while((i=file.read())!=-1) {
			XMLFile+=(char)i;
		}
		file.close();
		return XMLFile;
	}
}
