package br.com.fiap;

import com.sun.net.httpserver.HttpServer;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) throws Exception {

        // Cria servidor na porta 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/", exchange -> {

            String resposta = """
                <html>
                <head>
                    <meta charset="UTF-8">
                    <title>CP01 DevOps</title>
                    <style>
                        body {
                            background-color: #191970;
                            color: white;
                            font-family: Poppins;
                            display: flex;
                            justify-content: center;
                            align-items: center;
                            height: 100vh;
                        }
                        .box {
                            border: 3px solid #38bdf8;
                            padding: 40px;
                            border-radius: 12px;
                            text-align: center;
                            font-size: 24px;
                        }
                    </style>
                </head>
                <body>
                    <div class="box">
                        Minha primeira aplicação rodando na VM da Azure! 
                    </div>
                </body>
                </html>
                """;

            byte[] bytes = resposta.getBytes(StandardCharsets.UTF_8);
            
            exchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
            exchange.sendResponseHeaders(200, bytes.length);

            OutputStream os = exchange.getResponseBody();
            os.write(bytes);
            os.close();
        });

        server.start();

        System.out.println("Servidor rodando na porta 8080...");
    }
}
