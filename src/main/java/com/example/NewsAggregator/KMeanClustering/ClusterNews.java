package com.example.NewsAggregator.KMeanClustering;

import com.example.NewsAggregator.Responses.Response;

import java.util.*;

public class ClusterNews {

    public ArrayList<String> news;
    public ArrayList<NewsWordData> wordMapping;
    public List<Response> responsenews;
    HashSet<Integer> notClustered;
    public ClusterNews(List<Response> responsenews) throws Exception
    {
        this.responsenews=responsenews;
        ArrayList<String> temp = new ArrayList<String>();
        for(int i=0;i<responsenews.size();i++){
            temp.add(responsenews.get(i).getTitle()+responsenews.get(i).getTitle()+responsenews.get(i).getDescription());
        }
        this.news=temp;
        CorpusData data = new CorpusData(this.news);
        wordMapping = data.getNewswordfreq();



    }

    public  HashMap<Integer,ArrayList<Integer> > cluster(int noOfCluster){
        int[] clusterMapping = new int[news.size()];
        HashMap<Integer,ArrayList<Integer>> result = new HashMap<Integer,ArrayList<Integer> >();
        ArrayList<NewsWordData> currClusters = new ArrayList<NewsWordData>();
        for(int i=0;i<noOfCluster;i++){
            currClusters.add(wordMapping.get(i));
        }
        boolean centroidchanged = true;
        int iteration=0;
        while(centroidchanged && iteration<20){
            centroidchanged=false;
            for(int i=0;i<wordMapping.size();i++){
                int bestCluster = findClosest(currClusters,wordMapping.get(i));

                if(bestCluster!=clusterMapping[i]){
                    centroidchanged=true;
                    clusterMapping[i]=bestCluster;
                }
            }
            if(centroidchanged){
                for(int i=0;i<noOfCluster;i++){
                    ArrayList<NewsWordData> clusternews = new ArrayList<NewsWordData>();
                    for(int j=0;j<wordMapping.size();j++){
                        if(clusterMapping[j]==i){
                            clusternews.add(wordMapping.get(j));
                        }
                    }
                    if (!clusternews.isEmpty())
                        currClusters.set(i, findCentroid(clusternews));

                }
            }
            iteration++;
        }
//        for(int i=0;i< wordMapping.size();i++){
//            System.out.println(i+ " "+clusterMapping[i]);
//        }
//
        for(int i=0;i<wordMapping.size();i++){
            ArrayList<Integer> temp = new ArrayList<Integer>();
            for(int j=0;j<wordMapping.size();j++){
                if(clusterMapping[i]==clusterMapping[j]){
//                    System.out.println(i+" "+j);
                    temp.add(j);
                }
                result.put(i,temp);
            }
        }
        List < List<Response> > fin = new ArrayList<>();
        for (Map.Entry<Integer,ArrayList<Integer>> entry : result.entrySet()){
            ArrayList<Integer> list = entry.getValue();
            List<Response> responses = new ArrayList<Response>();
            for(int i = 0; i < list.size(); i++){
                responses.add(this.responsenews.get(list.get(i)));
            }
            fin.add(responses);
        }
        return result;

    }
    NewsWordData findCentroid(ArrayList<NewsWordData> news){
        HashMap<String,Integer> mp = new HashMap<String,Integer>();
        NewsWordData temp = new NewsWordData();
        for(int i=0;i<news.size();i++){
            for(int j=0;j<news.get(i).newsworddata.size();j++){
                temp.putvalue(news.get(i).newsworddata.get(j).word,news.get(i).newsworddata.get(j).value);
                if(mp.get(news.get(i).newsworddata.get(j).word)==null){
                    mp.put(news.get(i).newsworddata.get(j).word,1);
                }
                else mp.put(news.get(i).newsworddata.get(j).word,mp.get(news.get(i).newsworddata.get(j).word)+1);
            }
        }
        for(int i=0;i<temp.newsworddata.size();i++){
            WordFreq wf = new WordFreq();
            wf.word=temp.newsworddata.get(i).word;
            wf.value=temp.newsworddata.get(i).value/mp.get(temp.newsworddata.get(i).word);
            temp.newsworddata.set(i,wf);
        }
        return temp;
    }
    private int findClosest(ArrayList<NewsWordData> currClusters, NewsWordData newsWordData) {

            int index;
            int bestCluster = 0;
            int bestSimilarity = newsWordData.findSimilarity(currClusters.get(0));

            for (index = 1; index < currClusters.size(); index++) {
                int newSimilarity = newsWordData.findSimilarity(currClusters.get(index));
                if (newSimilarity > bestSimilarity) {
                    bestSimilarity = newSimilarity;
                    bestCluster = index;
                }
            }
            return bestCluster;
    }

}
