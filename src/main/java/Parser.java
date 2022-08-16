


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    public List<Response> getParsedNews(JSONArray jsonArray){

        List<Response> news = new ArrayList<>();
        for(int i = 0; i < jsonArray.length(); i++){
            JSONObject object = jsonArray.getJSONObject(i);
            Response response = new Response();
            response.setTitle(object.get("title").toString());
            response.setDescription(object.get("description").toString().replaceAll("<[^>]*>", ""));
            response.setPubTime(object.get("pubDate").toString());
            response.setLink(object.get("link").toString());
            news.add(response);
        }

        return news;
    }
}
