package strategy;

import java.math.BigDecimal;

/** Strategy 4: base price plus 25% per kilogram, insurance folded in. */
public final class ExpressStrategy implements CostStrategy {

    private static final Money BASE = new Money(12.00);
    private static final BigDecimal PER_KG = new BigDecimal("0.25");

    @Override
    public Money cost(Shipment shipment) {
        Money perKilogram = new Money(BigDecimal.valueOf(shipment.weightKg())).times(PER_KG);
        return BASE.plus(perKilogram);
    }
}