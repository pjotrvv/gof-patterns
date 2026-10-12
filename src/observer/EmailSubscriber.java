package observer;

/** Concrete observer that turns every article into an e-mail. */
public final class EmailSubscriber implements Subscriber {

    private final String address;

    public EmailSubscriber(String address) {
        this.address = address;
    }

    @Override
    public void onArticle(Article article) {
        System.out.printf("    [mail ] to %s -> \"%s\"%n", address, article.headline());
    }

    @Override
    public String name() {
        return "EmailSubscriber(" + address + ")";
    }
}