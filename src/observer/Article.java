package observer;

/**
 * The message that travels from the observable ({@link NewsAgency}) to its
 * subscribers. Subscribers only ever see this record - never each other and
 * never the agency internals.
 */
public record Article(String headline, String author) {

    public Article {
        if (headline == null || headline.isBlank()) {
            throw new IllegalArgumentException("headline must not be blank");
        }
    }
}