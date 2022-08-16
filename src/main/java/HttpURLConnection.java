
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;

public class HttpURLConnection {

    private static final String GET_URL = "https://timesofindia.indiatimes.com/rssfeeds/296589292.cms";





    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {

        sendGET();
        System.out.println("GET DONE");

    }

    private static void sendGET() throws IOException, ParserConfigurationException, SAXException {
        URL obj = new URL(GET_URL);
        java.net.HttpURLConnection con = (java.net.HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        StringBuffer response = new StringBuffer();

        if (responseCode == java.net.HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

//            System.out.println(response.toString());
        } else {
            System.out.println("GET request not worked");
        }

        JSONArray json = XML.toJSONObject(response.toString()).getJSONObject("rss").getJSONObject("channel").getJSONArray("item");
//        System.out.println(json);
//        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
//                .parse(new InputSource(new StringReader(response.toString())));
        JSONObject jsonArray = json.getJSONObject(0);
        System.out.println(jsonArray.get("link"));
        System.out.println(jsonArray.get("title"));
        System.out.println(jsonArray.get("description").toString().replaceAll("<[^>]*>", ""));
        System.out.println(jsonArray.get("pubDate"));
    }



}