package edu.escuelaing.arep.returns;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.file.Paths;

public class PetitionResponse {

    public static void response(Socket clienteSocket,String entrada) {
        String url;
        if (entrada.contains("GET")) {
            url = entrada.split(" ")[1].substring(1);
            try {
                System.out.println(Paths.get("").toAbsolutePath());
                System.out.println("/src/"+url);
                ResponseType.getInstance().recursoToString("/src/"+url, clienteSocket);
            } catch (IOException e) {
                try {
                    ResponseType.getInstance().recursoToString("/resource/img/404.jpg", clienteSocket);
                } catch (IOException e1) {
                    System.exit(1);
                }
            }
        }
    }

    public static void getentry(Socket clienteSocket) throws IOException {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(clienteSocket.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response(clienteSocket, inputLine);
            if (!in.ready()) {
                break;
            }
        }
    }

}
