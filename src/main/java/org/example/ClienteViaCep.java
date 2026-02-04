package org.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClienteViaCep {

    public static void main(String[] args) throws Exception {

        /* URL url = new URL("https://viacep.com.br/ws/58052-320/json/");

        try (Scanner scanner = new Scanner(url.openStream())){
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }
        }*/

        URI uri = URI.create("https://viacep.com.br/ws/58900-000/json/");

        try (HttpClient httpClient = HttpClient.newHttpClient()) {
            HttpRequest httpRequest = HttpRequest.newBuilder(uri).build();
            HttpResponse<String> httpResponse = httpClient.send(httpRequest,
                    HttpResponse.BodyHandlers.ofString());

            int statusCode = httpResponse.statusCode();
            String body = httpResponse.body();
            System.out.println(statusCode);
            System.out.println(body);
        }
    }
}
