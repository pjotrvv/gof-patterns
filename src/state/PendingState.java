package state;

/** First state: the order exists but nothing has happened yet. */
public final class PendingState implements OrderState {

    @Override
    public String name() {
        return "PENDING";
    }

    @Override
    public void pay(Order order) {
        System.out.printf("    [pending] marking order %s as paid%n", order.id());
        order.transitionTo(new PaidState());
    }

    @Override
    public void ship(Order order) {
        reject(order, "ship");
    }

    @Override
    public void cancel(Order order) {
        System.out.printf("    [pending] cancelling order %s before payment%n", order.id());
        order.transitionTo(new CancelledState());
    }
}