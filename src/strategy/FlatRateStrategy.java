package strategy;

/** Strategy 1: one fixed price, whatever the shipment looks like. */
public final class FlatRateStrategy implements CostStrategy {

    private static final Money RATE = new Money(9.95);

    @Override
    public Money cost(Shipment shipment) {
        return RATE;
    }
}