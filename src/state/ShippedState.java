package state;

/** Third state: shipped. This is a final state, every action is refused. */
public final class ShippedState implements OrderState {

    @Override
    public String name() {
        return "SHIPPED";
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