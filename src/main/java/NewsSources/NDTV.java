package NewsSources;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public enum NDTV {

    SPORTS ("https://feeds.feedburner.com/ndtvsports-latest"),
    TECHNOLOGY("https://feeds.feedburner.com/gadgets360-latest"),
    ENTERTAINMENT("https://feeds.feedburner.com/ndtvmovies-latest"),
    HEALTH("https://feeds.feedburner.com/ndtvcooks-latest"),
    BUSINESS("https://feeds.feedburner.com/ndtvprofit-latest"),
    AUTO("https://feeds.feedburner.com/carandbike-latest");

    private String action;

    NDTV(String s) {
        this.action = s;
    }

    String getURL(){
        return action;
    }
}