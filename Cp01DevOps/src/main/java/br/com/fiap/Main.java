package br.com.fiap;
import com.sun.net.httpserver.HttpServer;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Main {

    public static void main(String[] args) throws Exception {

        // Cria servidor na porta 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/", exchange -> {

            String resposta = """
                <html>
                <head>
                    <title>CP01 DevOps</title>
                    <style>
                        body {
                            background-color: #0f172a;
                            color: white;
                            font-family: Arial;
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
                        Esta aplicação está rodando na nossa VM na Azure ☁️
                    </div>
                </body>
                </html>
                """;

            exchange.sendResponseHeaders(200, resposta.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(resposta.getBytes());
            os.close();
        });

        server.start();

        System.out.println("Servidor rodando na porta 8080...");
    }


}
