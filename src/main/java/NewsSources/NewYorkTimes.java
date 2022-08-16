
package NewsSources;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public enum NewYorkTimes {

    SPORTS ("https://rss.nytimes.com/services/xml/rss/nyt/Sports.xml"),
    TECHNOLOGY("https://rss.nytimes.com/services/xml/rss/nyt/Technology.xml"),
    HEALTH("https://rss.nytimes.com/services/xml/rss/nyt/Health.xml"),
    BUSINESS("https://rss.nytimes.com/services/xml/rss/nyt/Business.xml"),
    AUTO("https://rss.nytimes.com/services/xml/rss/nyt/Automobiles.xml"),
    SCIENCE("https://rss.nytimes.com/services/xml/rss/nyt/Science.xml");

    private String action;

    NewYorkTimes(String s) {
        this.action = s;
    }

    String getURL(){
        return action;
    }
}