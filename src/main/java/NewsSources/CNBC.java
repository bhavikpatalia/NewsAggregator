
package NewsSources;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public enum CNBC {

    TECHNOLOGY("https://search.cnbc.com/rs/search/combinedcms/view.xml?partnerId=wrss01&id=19854910"),
    HEALTH("https://search.cnbc.com/rs/search/combinedcms/view.xml?partnerId=wrss01&id=10000108"),
    BUSINESS("https://search.cnbc.com/rs/search/combinedcms/view.xml?partnerId=wrss01&id=10001147"),
    AUTO("https://search.cnbc.com/rs/search/combinedcms/view.xml?partnerId=wrss01&id=10000101");
    private String action;

    CNBC(String s) {
        this.action = s;
    }

    String getURL(){
        return action;
    }
}