package restapi;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;  

public class Server {

    public static void main(String[] args) throws Exception {
    	
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/insert", new PostMapping());
        
        server.createContext("/select", new GetMapping());

        server.start();
        
        System.out.println("Server url: http://localhost:8080");
    }
}