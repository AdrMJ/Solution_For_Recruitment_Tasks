import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Scanner;

import com.google.gson.Gson;


public class Main {
    public static void main(String[] args) throws Exception {

        V_Quotes v_quotes = new V_Quotes();
        Transcript transcript = new Transcript();
        transcript.setUrl("https://github.com/ajzbc/kanye.rest/blob/master/quotes.json");

        Gson gson = new Gson();
        String jsonRequest = gson.toJson(transcript);

        Scanner scan = new Scanner(System.in);
        String next;
        do {
            next = "";

            HttpRequest postRequest = HttpRequest.newBuilder()
                    .uri(new URI("https://api.kanye.rest"))
                    .POST(BodyPublishers.ofString(jsonRequest))
                    .build();

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> postResponse = httpClient.send(postRequest, BodyHandlers.ofString());
            for(int i = 0; i<v_quotes.size();i++){
                if (v_quotes.get(i)==transcript.getQuote()) {
                    next = "NEXT";
                    if(i==v_quotes.size()){
                    continue;
                    }
                } else {

                    transcript = gson.fromJson(postResponse.body(), Transcript.class);
                    v_quotes.add(transcript.getQuote());
                    System.out.println(transcript.getQuote());
                    System.out.println("Napisz \"next\", aby zobaczyć kolejny cytat");

                    next = scan.nextLine();
                }
            }
        } while (next.toUpperCase().equals("NEXT"));

        scan.close();

    }
}
