package NewsGenerator;

import GenerateResponse.HttpURLConnection;
import NewsParsers.NewsParser;
import NewsSources.*;
import Responses.Response;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class TechnologyNewsGenerator {

    public List<Response> getAllTechnologyNews(){

        List<Response> responses = new ArrayList<>();
        try {
            JSONArray bbc = HttpURLConnection.sendGET(BBC.TECHNOLOGY.getAction());
            JSONArray cnn = HttpURLConnection.sendGET(CNN.TECHNOLOGY.getAction());
            JSONArray cnbc = HttpURLConnection.sendGET(CNBC.TECHNOLOGY.getAction());
            JSONArray hindustanTimes = HttpURLConnection.sendGET(HindustanTimes.TECHNOLOGY.getAction());
//            JSONArray huffPost = HttpURLConnection.sendGET(Huffpost.TECHNOLOGY.getAction());
            JSONArray indianExpress = HttpURLConnection.sendGET(IndianExpress.TECHNOLOGY.getAction());
            JSONArray ndtv = HttpURLConnection.sendGET(NDTV.TECHNOLOGY.getAction());
            JSONArray news18 = HttpURLConnection.sendGET(News18.TECHNOLOGY.getAction());
            JSONArray newYorkTimes = HttpURLConnection.sendGET(NewYorkTimes.TECHNOLOGY.getAction());
            JSONArray theGuardian = HttpURLConnection.sendGET(TheGuardian.TECHNOLOGY.getAction());
            JSONArray timesOfIndia = HttpURLConnection.sendGET(TimesOfIndia.TECHNOLOGY.getAction());

            getResonse(cnn, responses);
            getResonse(timesOfIndia, responses);
            getResonse(bbc, responses);
            getResonse(theGuardian, responses);
            getResonse(newYorkTimes, responses);
            getResonse(news18, responses);
            getResonse(ndtv, responses);
            getResonse(cnbc, responses);
            getResonse(indianExpress, responses);
//            getResonse(huffPost, responses);
            getResonse(hindustanTimes, responses);

        } catch (Exception e){
            // Will handle it
        }
        return responses;
    }

    private void getResonse(JSONArray jsonArray, List<Response> responses) {
        List<Response> responseList = NewsParser.getParsedNews(jsonArray);
        responses.addAll(responseList);
    }
}
