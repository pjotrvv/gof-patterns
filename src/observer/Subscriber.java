package observer;

/**
 * Observer side of the pattern. Everyone who wants to hear news implements
 * this interface; the agency never needs to know the concrete type.
 */
public interface Subscriber {

    /** Called once for every article the agency publishes. */
    void onArticle(Article article);

    /** Human readable label, used by the agency when logging subscriptions. */
    default String name() {
        return getClass().getSimpleName();
    }
}