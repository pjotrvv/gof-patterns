package observer;

/** Concrete observer that sends a short text message; ignores long headlines. */
public final class SmsSubscriber implements Subscriber {

    private static final int MAX_LENGTH = 60;

    private final String phoneNumber;

    public SmsSubscriber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void onArticle(Article article) {
        String text = article.headline();
        if (text.length() > MAX_LENGTH) {
            text = text.substring(0, MAX_LENGTH - 1) + "~";
        }
        System.out.printf("    [sms  ] to %s -> \"%s\"%n", phoneNumber, text);
    }

    @Override
    public String name() {
        return "SmsSubscriber(" + phoneNumber + ")";
    }
}