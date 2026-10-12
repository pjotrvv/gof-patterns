package strategy;

import java.math.BigDecimal;

/** Strategy 2: price per kilogram, with a minimum. */
public final class PerKgStrategy implements CostStrategy {

    private static final BigDecimal PER_KG = new BigDecimal("2.50");
    private static final Money MINIMUM = new Money(5.00);

    @Override
    public Money cost(Shipment shipment) {
        Money price = new Money(BigDecimal.valueOf(shipment.weightKg())).times(PER_KG);
        return price.amount().compareTo(MINIMUM.amount()) < 0 ? MINIMUM : price;
    }
}