package KMeanClustering;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class DocumentReader {
    public StopWords _stopwords;
    public PorterStemmer _stemmer;

    DocumentReader() throws Exception {
        _stopwords=StopWords.getStopWords();
        _stemmer =  PorterStemmer.getStemmer();

    }

    public NewsWordData read(String news) throws Exception {

        news = news.replaceAll("[^A-Za-z ]", " ").trim().replaceAll(" +", " ");
        news = news.toLowerCase();
        NewsWordData result = new NewsWordData();

        String [] procWords = news.split(" ");
        for (int i = 0; i < procWords.length; i++) {
            if (!_stopwords.isStopWord(procWords[i])) {
                if (!procWords[i].equals("s")) {
                    String stem = _stemmer.getStem(procWords[i]);
                    result.putvalue(stem,1);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        DocumentReader dr = new DocumentReader();
        System.out.println(dr._stopwords);
    }
}


