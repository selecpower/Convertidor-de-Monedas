package org.example.convertidorservice;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
public class ConvertidorService {
    private static final String API_KEY = "678b0543b4626ac365f128b4";

    public JsonObject getConversionRates(String baseCurrency) throws IOException, InterruptedException {
        String uri = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/" + baseCurrency;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();

        return jsonObject.getAsJsonObject("conversion_rates");
    }

    public double convert(double amount, double rate) {
        return amount * rate;
    }
}

