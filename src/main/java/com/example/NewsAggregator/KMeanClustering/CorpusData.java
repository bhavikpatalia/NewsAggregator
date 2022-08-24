package KMeanClustering;

import java.util.ArrayList;

public class CorpusData {

    public ArrayList<String> getNews() {
        return news;
    }

    public ArrayList<NewsWordData> getNewswordfreq() {
        return newswordfreq;
    }

    public ArrayList<String> news;
    public ArrayList<NewsWordData> newswordfreq;

    public CorpusData(ArrayList<String> news) throws Exception {
        DocumentReader reader = new DocumentReader();
        newswordfreq = new ArrayList<NewsWordData>();
        for(int i=0;i<news.size();i++) {
            newswordfreq.add(reader.read(news.get(i)));
        }
    }

}
