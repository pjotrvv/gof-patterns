package state;

/**
 * The context: it holds a state object and forwards every call to it.
 *
 * <p>There is deliberately no {@code switch} and no chain of {@code if}s here -
 * that is the whole point of the pattern. Adding {@code refund()} later only
 * touches the state classes.
 */
public final class Order {

    private final String id;
    private OrderState state;

    public Order(String id) {
        this(id, new PendingState());
        System.out.printf("  order %s created, state = %s%n", id, state.name());
    }

    private Order(String id, OrderState state) {
        this.id = id;
        this.state = state;
    }

    public String id() {
        return id;
    }

    public OrderState state() {
        return state;
    }

    public void pay() {
        state.pay(this);
    }

    public void ship() {
        state.ship(this);
    }

    public void cancel() {
        state.cancel(this);
    }

    /** Called by the current state when it hands over to the next one. */
    void transitionTo(OrderState next) {
        System.out.printf("  order %s: %s -> %s%n", id, state.name(), next.name());
        this.state = next;
    }
}