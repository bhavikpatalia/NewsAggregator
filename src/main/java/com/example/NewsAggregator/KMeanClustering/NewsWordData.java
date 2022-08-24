package KMeanClustering;

import java.util.ArrayList;

public class NewsWordData {

    public ArrayList<WordFreq> newsworddata;

    public double getvalue(String s){
        for(int i=0;i<newsworddata.size();i++){
            if(newsworddata.get(i).word.equals(s)){
                return newsworddata.get(i).value;
            }
        }
        return 0;
    }
    public void putvalue(String s,double x){
        int f=0;
        if(newsworddata==null){
            newsworddata=new ArrayList<WordFreq>();
        }
        for(int i=0;i<newsworddata.size();i++){
            if(newsworddata.get(i).word.equals(s)){
                WordFreq wf = new WordFreq();
                wf.word=newsworddata.get(i).word;
                wf.value=newsworddata.get(i).value+x;
                 newsworddata.set(i,wf);
                 f=1;
                 break;
            }
        }
        if(f==0){
            WordFreq temp = new WordFreq();
            temp.value=x;
            temp.word=s;
            newsworddata.add(temp);
        }
    }
    int findSimilarity(NewsWordData nwd){
        int res=0;
        for(int i=0;i<this.newsworddata.size();i++){
            for(int j=0;j<nwd.newsworddata.size();j++){
                if(this.newsworddata.get(i).word.equals(nwd.newsworddata.get(j).word)){
                    res+=this.newsworddata.get(i).value*nwd.newsworddata.get(j).value;
                }
            }
        }
        return res;
    }

}
