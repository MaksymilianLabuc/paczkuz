package org.janbat.paczkuz;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.io.IOException;

public class API {
    /**
     * Metoda odpytująca API o dystans między dwoma punktami
     * @param start_lat - szerokość geograficzna punktu 1
     * @param start_lon - długość geograficzna punktu 1
     * @param dest_lat - szerokość geograficzna punktu 2
     * @param dest_lon - długość geograficzna punktu 2
     * @return dystans w metrach
     */
    private static int calculateDistance(double start_lat, double start_lon, double dest_lat, double dest_lon) {
        String query = "https://graphhopper.com/api/1/route?point=" + start_lat + "," + start_lon +  "&point=" + dest_lat + "," + dest_lon + "&profile=car&locale=pl&calc_points=false&key=ebc7a0f9-d89e-4fd2-a2a9-e2f3edade585";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(query))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JsonParser parser = new JsonParser();
        JsonObject obj = (JsonObject) parser.parse(response.body());

        return obj.get("paths").getAsJsonArray().get(0).getAsJsonObject().get("distance").getAsInt();
    }

    /**
     * Metoda obliczająca dystans pomiędzy miastami (najszybsza trasa drogowa).
     * @param townName1 (String) - nazwa miasta startowego
     * @param townName2 (String) - nazwa miasta docelowego
     * @return dystans w metrach
     */
    public static int getDistanceOfTowns(String townName1, String townName2) {
        townName1 = townName1.replace(" ", "%20");
        townName2 = townName2.replace(" ", "%20");
        String query1 =  "https://graphhopper.com/api/1/geocode?q=" + townName1 + "&locale=pl&key=ebc7a0f9-d89e-4fd2-a2a9-e2f3edade585";
        String query2 =  "https://graphhopper.com/api/1/geocode?q=" + townName2 + "&locale=pl&key=ebc7a0f9-d89e-4fd2-a2a9-e2f3edade585";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(query1))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        HttpRequest request2 = HttpRequest.newBuilder()
                .uri(URI.create(query2))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response2 = null;
        try {
            response2 = HttpClient.newHttpClient().send(request2, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        JsonParser parser = new JsonParser();
        JsonObject obj = (JsonObject) parser.parse(response.body());
        JsonObject obj2 = (JsonObject) parser.parse(response2.body());
        JsonObject coords1 = obj.get("hits").getAsJsonArray().get(0).getAsJsonObject().get("point").getAsJsonObject();
        JsonObject coords2 = obj2.get("hits").getAsJsonArray().get(0).getAsJsonObject().get("point").getAsJsonObject();

        return API.calculateDistance(coords1.get("lat").getAsDouble(), coords1.get("lng").getAsDouble(), coords2.get("lat").getAsDouble(), coords2.get("lng").getAsDouble());
    }
}
