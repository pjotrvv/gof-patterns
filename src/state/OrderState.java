package state;

/**
 * State side of the pattern. Every state decides what each action means right
 * now, and which state comes next.
 *
 * <p>Each state is a separate class, so adding a new state means adding a new
 * class - not editing a growing {@code if}/{@code else} chain inside the
 * context.
 */
public interface OrderState {

    /** Name shown in the log output. */
    String name();

    void pay(Order order);

    void ship(Order order);

    void cancel(Order order);

    /** Helper for logging a transition that is not allowed in this state. */
    default void reject(Order order, String action) {
        System.out.printf("    ! cannot %s order %s while it is %s%n", action, order.id(), name());
    }
}