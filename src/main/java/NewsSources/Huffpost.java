
package NewsSources;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public enum Huffpost {

    SPORTS ("https://chaski.huffpost.com/us/auto/vertical/sports"),
    TECHNOLOGY("https://chaski.huffpost.com/us/auto/vertical/technology"),
    ENTERTAINMENT("https://chaski.huffpost.com/us/auto/vertical/entertainment"),
    HEALTH("https://chaski.huffpost.com/us/auto/vertical/health"),
    SCIENCE("https://chaski.huffpost.com/us/auto/vertical/science");

    private String action;

    Huffpost(String s) {
        this.action = s;
    }

    String getURL(){
        return action;
    }
}