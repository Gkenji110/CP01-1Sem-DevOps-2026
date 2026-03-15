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
                            background: linear-gradient(135deg, #0f172a 0%, #1e293b 100%);
                            color: white;
                            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                            display: flex;
                            justify-content: center;
                            align-items: center;
                            height: 100vh;
                            margin: 0;
                        }
                        .box {
                            border: 2px solid #38bdf8;
                            padding: 50px;
                            border-radius: 20px;
                            text-align: center;
                            font-size: 28px;
                            font-weight: bold;
                            background-color: rgba(15, 23, 42, 0.8);
                    
                            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.5), 0 0 20px rgba(56, 189, 248, 0.2);
                    
                            transition: all 0.3s ease;
                            cursor: pointer;
                        }
                    
                        .box:hover {
                            transform: translateY(-10px) scale(1.05);
                            background-color: #38bdf8;
                            color: #0f172a;
                            box-shadow: 0 20px 40px rgba(56, 189, 248, 0.4);
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
