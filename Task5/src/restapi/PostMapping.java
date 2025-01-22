package restapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

class PostMapping implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response;
        int statusCode = 200;

        if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
            try{
            	InputStreamReader reader = new InputStreamReader(exchange.getRequestBody());
                BufferedReader br = new BufferedReader(reader);
                StringBuilder requestData = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    requestData.append(line);
                }
                
                String data = requestData.toString();
                
                JsonObject json = JsonParser.parseString(data).getAsJsonObject();
                
                long rollno = Long.valueOf(json.get("rollno").toString());
                String studentname = json.get("studentname").toString().replace("\"", "");
                String dept = json.get("dept").toString().replace("\"", "");
                long phonenumber = Long.valueOf(json.get("phonenumber").toString());
                boolean valid = true;
                if(rollno==0) {
                	valid = false;
                }if(studentname.equals("")) {
                	valid = false;
                }if(dept.equals("")) {
                	valid = false;
                }if(phonenumber==0) {
                	valid = false;
                }
                if(valid) {
                	response = new InsertData().insertdata(rollno, studentname, dept, phonenumber);
                }
                else {
                	response = "Please enter valid data";
                }
            } catch (Exception e) {
                System.err.println("Error handling request: " + e.getMessage());
                statusCode = 500;
                response = "Please enter valid data";
            }
        } else {
            statusCode = 405;
            response = "Method Not Allowed";
        }

        exchange.sendResponseHeaders(statusCode, response.getBytes().length);
        try (OutputStream output = exchange.getResponseBody()) {
        	output.write(response.getBytes());
        }
    }
}
