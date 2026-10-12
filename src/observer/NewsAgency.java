package observer;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Observable side of the pattern.
 *
 * <p>The agency keeps a list of {@link Subscriber}s and pushes every published
 * {@link Article} to them. It knows nothing about e-mail, SMS or analytics.
 *
 * <p>A {@link CopyOnWriteArrayList} is used so that publishing is safe even if a
 * subscriber would subscribe or unsubscribe from inside {@link Subscriber#onArticle}
 * - the plain {@code ArrayList} version throws
 * {@code ConcurrentModificationException} in that case.
 */
public final class NewsAgency {

    private final List<Subscriber> subscribers = new CopyOnWriteArrayList<>();

    public void subscribe(Subscriber subscriber) {
        subscribers.add(Objects.requireNonNull(subscriber, "subscriber"));
        System.out.printf("  [agency] %s subscribed%n", subscriber.name());
    }

    public void unsubscribe(Subscriber subscriber) {
        if (subscribers.remove(subscriber)) {
            System.out.printf("  [agency] %s unsubscribed%n", subscriber.name());
        }
    }

    public void publish(Article article) {
        System.out.printf("%n  [agency] publishing \"%s\" by %s%n", article.headline(), article.author());
        for (Subscriber subscriber : subscribers) {
            subscriber.onArticle(article);
        }
    }

    public int subscriberCount() {
        return subscribers.size();
    }
}