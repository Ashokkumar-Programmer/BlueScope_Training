package restapi;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

class GetMapping implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response;
        int statusCode = 200;

        if ("GET".equalsIgnoreCase(exchange.getRequestMethod())) {
            try{
                ResultSet result = new SelectData().selectdata();
                
                JsonArray ja = new JsonArray();
                while(result.next()) {
                	JsonObject js = new JsonObject();
                	js.addProperty("rollno", result.getLong("rollno"));
                	js.addProperty("studentname", result.getString("studentname"));
                	js.addProperty("dept", result.getString("dept"));
                	js.addProperty("phonenumber", result.getLong("phonenumber"));
                	ja.add(js);
                }
                
                response = ja.toString().equals("[]")?"no data available":ja.toString();

            } catch (Exception e) {
                System.err.println("Error handling request: " + e.getMessage());
                statusCode = 500;
                response = "Internal Server Error";
            }
        } else {
            statusCode = 405;
            response = "Method Not Allowed";
        }

        exchange.sendResponseHeaders(statusCode, response.getBytes().length);
        try (OutputStream ouput = exchange.getResponseBody()) {
        	ouput.write(response.getBytes());
        }
    }
}
