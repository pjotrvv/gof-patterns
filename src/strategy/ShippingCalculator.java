package strategy;

/**
 * The context: it delegates the actual pricing to a {@link CostStrategy} and
 * knows nothing about the individual algorithms.
 *
 * <p>Because the strategy is just a field, it can be swapped at any time with
 * {@link #setStrategy(CostStrategy)} - the calculator code itself never changes.
 */
public final class ShippingCalculator {

    private CostStrategy strategy;

    public ShippingCalculator(CostStrategy strategy) {
        setStrategy(strategy);
    }

    public void setStrategy(CostStrategy strategy) {
        CostStrategy previous = this.strategy;
        this.strategy = strategy;
        if (previous == null) {
            System.out.printf("  calculator created with %s%n", strategy.name());
        } else {
            System.out.printf("  strategy swapped: %s -> %s%n", previous.name(), strategy.name());
        }
    }

    public CostStrategy strategy() {
        return strategy;
    }

    public Money quote(Shipment shipment) {
        Money price = strategy.cost(shipment);
        System.out.printf("  [%s] %s -> %s%n", strategy.name(), shipment.describe(), price.format());
        return price;
    }
}