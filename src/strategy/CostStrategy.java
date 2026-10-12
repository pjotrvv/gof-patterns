package strategy;

/**
 * The Strategy interface: one algorithm of the family.
 *
 * <p>Every strategy is interchangeable and can be handed to
 * {@link ShippingCalculator} at any time, at construction or later.
 */
public interface CostStrategy {

    /** Price for one shipment. */
    Money cost(Shipment shipment);

    /** Label used in the log output. */
    default String name() {
        return getClass().getSimpleName();
    }
}