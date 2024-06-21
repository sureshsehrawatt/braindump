import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientImpl {
    HttpClient client;
    HttpRequest request;
    HttpResponse<String> response;
    HttpHeaders headers;

    public void makeRequest(String uri) {
        try {
            client = HttpClient.newHttpClient();
            request = HttpRequest.newBuilder()
                    .uri(new URI(uri))
                    .GET()
                    .build();
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            headers = response.headers();
            System.out.println("Response Code: "+response.statusCode());
            System.out.println("Response body: "+response.body());
            headers.map().forEach((k , v)->System.out.println(k+" : "+v));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HttpClientImpl obj = new HttpClientImpl();
        obj.makeRequest("https://www.google.com");
    }
}
