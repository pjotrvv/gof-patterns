package state;

/** Second state: paid, so the order may be shipped or still cancelled (refund). */
public final class PaidState implements OrderState {

    @Override
    public String name() {
        return "PAID";
    }

    @Override
    public void pay(Order order) {
        reject(order, "pay");
    }

    @Override
    public void ship(Order order) {
        System.out.printf("    [paid   ] handing order %s to the carrier%n", order.id());
        order.transitionTo(new ShippedState());
    }

    @Override
    public void cancel(Order order) {
        System.out.printf("    [paid   ] refunding order %s%n", order.id());
        order.transitionTo(new CancelledState());
    }
}