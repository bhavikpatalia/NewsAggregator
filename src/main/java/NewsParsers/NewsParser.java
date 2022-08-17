package NewsParsers;

import Responses.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class NewsParser {

    public  static List<Response> getParsedNews(JSONArray jsonArray) throws ParseException {

        List<Response> news = new ArrayList<>();
        for(int i = 0; i < jsonArray.length(); i++){
            JSONObject object = jsonArray.getJSONObject(i);
            Response response = new Response();
            if(object.has("title")) response.setTitle(object.get("title").toString());
            if(object.has("description")) response.setDescription(object.get("description").toString().replaceAll("<[^>]*>", ""));
            if(object.has("pubDate")){
                String time = object.get("pubDate").toString();
                if(time.contains("GMT")){
                    response.setPubTime(getTimeForNonT(time));
                }
                else if(time.charAt(10) == 'T') response.setPubTime(getTimeForT(time));
                else response.setPubTime(getTimeForNonT(time));
            }
            if(object.has("link")) response.setLink(object.get("link").toString());
            news.add(response);
        }
        return news;
    }

    public static Long getTimeForNonT(String time) throws ParseException {
        Date parsedDate = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z").parse(time);
        return parsedDate.getTime();
    }
    public static Long getTimeForT(String time) throws ParseException {
        Date parsedDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+SS:SS").parse(time);
        return parsedDate.getTime();
    }
}
