package NewsSources;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public enum TimesOfIndia {

    SPORTS ("https://timesofindia.indiatimes.com/rssfeeds/4719148.cms"),
    TECHNOLOGY("https://timesofindia.indiatimes.com/rssfeeds/66949542.cms"),
    ENTERTAINMENT("https://timesofindia.indiatimes.com/rssfeeds/1081479906.cms"),
    SCIENCE("https://timesofindia.indiatimes.com/rssfeeds/-2128672765.cms"),
    BUSINESS("https://timesofindia.indiatimes.com/rssfeeds/1898055.cms"),
    AUTO("https://timesofindia.indiatimes.com/rssfeeds/74317216.cms");

    private String action;

    TimesOfIndia(String s) {
        this.action = s;
    }

    String getURL(){
        return action;
    }
}
