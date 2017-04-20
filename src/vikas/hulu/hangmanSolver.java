package vikas.hulu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by VikasN on 10/30/16.
 */
public class hangmanSolver {
    public static ArrayList<String> LETTER_PAIRS = new ArrayList<String>(Arrays.asList("TH", "HE", "AN", "RE", "ER", "IN", "ON", "AT", "ND", "ST", "ES",
            "EN", "OF", "TE", "ED", "OR", "TI", "HI", "AS", "TO"));
    private static final String USER_AGENT = "Mozilla/5.0";

    public static void main(String... args){

    }

    public static void function1(){

    }

    private void sendGet() throws Exception {

        String url = "http://www.google.com/search?q=mkyong";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }

}
