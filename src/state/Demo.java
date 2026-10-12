package state;

/** Runnable demonstration of the State pattern. */
public final class Demo {

    private Demo() {
    }

    public static void main(String[] args) {
        System.out.println("=== State: an order behaves differently per state ===");

        System.out.println("\n-- order 1001 walks the happy path --");
        Order happy = new Order("1001");
        happy.ship();
        happy.pay();
        happy.pay();
        happy.ship();

        System.out.println("\n-- order 1002 is paid and then cancelled --");
        Order refunded = new Order("1002");
        refunded.pay();
        refunded.cancel();
        refunded.ship();

        System.out.println("\n-- order 1003 is cancelled straight away --");
        Order cancelled = new Order("1003");
        cancelled.cancel();
        cancelled.cancel();

        System.out.printf("%nFinal states: %s=%s, %s=%s, %s=%s%n",
                happy.id(), happy.state().name(),
                refunded.id(), refunded.state().name(),
                cancelled.id(), cancelled.state().name());

        System.out.println("\nOrder holds no if/else: each state class decides what every action means.");
    }
}