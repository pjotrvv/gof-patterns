package state;

/** Terminal state: the order is cancelled and can never come back to life. */
public final class CancelledState implements OrderState {

    @Override
    public String name() {
        return "CANCELLED";
    }

    @Override
    public void pay(Order order) {
        reject(order, "pay");
    }

    @Override
    public void ship(Order order) {
        reject(order, "ship");
    }

    @Override
    public void cancel(Order order) {
        reject(order, "cancel");
    }
}