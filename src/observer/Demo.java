package observer;

/** Runnable demonstration of the Observer pattern. */
public final class Demo {

    private Demo() {
    }

    public static void main(String[] args) {
        System.out.println("=== Observer: a news agency pushes articles to subscribers ===");

        NewsAgency agency = new NewsAgency();
        AnalyticsSubscriber analytics = new AnalyticsSubscriber();
        Subscriber email = new EmailSubscriber("pjotrvv@example.com");
        Subscriber sms = new SmsSubscriber("+31 6 1234 5678");

        System.out.println("\n-- subscribing --");
        agency.subscribe(email);
        agency.subscribe(sms);
        agency.subscribe(analytics);

        System.out.println("\n-- publishing --");
        agency.publish(new Article("Gang of Four patterns, explained", "Ada"));
        agency.publish(new Article("Observer in three lines of code", "Grace"));

        System.out.println("\n-- the SMS subscriber loses interest --");
        agency.unsubscribe(sms);

        agency.publish(new Article("State versus Strategy", "Alan"));

        System.out.println("\nAgency subscribers left: " + agency.subscriberCount());
        System.out.println("Analytics collected: " + analytics.tracked());
        System.out.println("\nThe agency only knows Subscriber, the subscribers only know Article:");
        System.out.println("neither side needs a compile-time dependency on the other.");
    }
}