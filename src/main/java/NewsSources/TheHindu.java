
package NewsSources;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public enum TheHindu {

    SPORTS ("https://www.thehindu.com/sport/feeder/default.rss"),
    ENTERTAINMENT("https://www.thehindu.com/entertainment/feeder/default.rss"),
    HEALTH("https://www.thehindu.com/life-and-style/feeder/default.rss"),
    BUSINESS("https://www.thehindu.com/business/feeder/default.rss"),
    SCIENCE("https://www.thehindu.com/sci-tech/science/feeder/default.rss");

    private String action;

    TheHindu(String s) {
        this.action = s;
    }

    String getURL(){
        return action;
    }
}