package KMeanClustering;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class StopWords {
    private static StopWords _words = null;
    private Set<String> _wordList = null;
    private BufferedReader _file;


    public static StopWords getStopWords() throws Exception
    {
        if (_words == null) {
            try {
                _words = new StopWords();
            }
            catch (Exception e) {
                _words = null;
                throw e;
            }
    }
        return _words;
    }

    public StopWords() throws Exception
    {
        _wordList = new HashSet<String>();


        try {

            String fullName = new String("/Users/bhavik.pa/Downloads/NewsAggregator /src/main/java/KMeanClustering/stopwords.txt");
            _file = new BufferedReader(new FileReader(fullName));
            if (_file == null) {
                // Failed to open.
                System.out.println("No stop words file " + fullName + " found. Assuming none defined");
            }
            else {
                String [] newData = _file.readLine().split(",");
                if (newData.length <= 0)
                    System.out.println("Stop words file " + fullName + " corrupt; no data");
                else {
                    int index;
                    for (index = 0; index < newData.length; index++){
//                        System.out.println(newData[index]);
                        _wordList.add(newData[index]);

                    }
                }
//                _file.close();
                _file = null;
            }
        }
        catch (Exception e) {
            _wordList.clear();


            _file = null;
            throw e;
        }
    }


    public void finailize()
    {
        if (_file != null) {
            System.out.println("WARNING: File not properly closed");
            try {
                _file.close();
            }

            catch (IOException e) {}
            _file = null;
        }
    }


    public String toString()
    {
        if (_wordList == null)
            return "NONE";
        else
            return Arrays.toString(_wordList.toArray());
    }

    public boolean isStopWord(String word)
    {
        if (_wordList == null)
            return false;
        else
            return (_wordList.contains(word));
    }


}