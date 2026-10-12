package observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete observer that only collects statistics. It has no delivery channel at
 * all, which shows that a subscriber is free to do something completely different.
 */
public final class AnalyticsSubscriber implements Subscriber {

    private final List<String> headlines = new ArrayList<>();

    @Override
    public void onArticle(Article article) {
        headlines.add(article.headline());
        System.out.printf("    [stats] tracked %d article(s)%n", headlines.size());
    }

    public List<String> tracked() {
        return List.copyOf(headlines);
    }
}