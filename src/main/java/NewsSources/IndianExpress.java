
package NewsSources;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public enum IndianExpress {

    SPORTS ("https://indianexpress.com/section/sports/feed/"),
    TECHNOLOGY("https://indianexpress.com/section/technology/feed/"),
    ENTERTAINMENT("https://indianexpress.com/section/entertainment/feed/"),
    HEALTH("https://indianexpress.com/section/lifestyle/health/feed/"),
    SCIENCE("https://indianexpress.com/section/technology/feed/");

    private String action;

    IndianExpress(String s) {
        this.action = s;
    }

    String getURL(){
        return action;
    }
}