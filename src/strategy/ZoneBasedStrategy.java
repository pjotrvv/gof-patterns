package strategy;

import java.math.BigDecimal;
import java.util.Map;

/** Strategy 3: a base price plus a per-zone surcharge. */
public final class ZoneBasedStrategy implements CostStrategy {

    private static final Money BASE = new Money(4.50);
    private static final Map<Integer, Money> ZONE_SURCHARGE = Map.of(
            1, new Money(0.00),
            2, new Money(3.00),
            3, new Money(6.50),
            4, new Money(11.00));

    @Override
    public Money cost(Shipment shipment) {
        Money surcharge = ZONE_SURCHARGE.get(shipment.zone());
        if (surcharge == null) {
            throw new IllegalArgumentException("unknown shipping zone: " + shipment.zone());
        }
        Money fragileSurcharge = shipment.fragile() ? new Money(2.25) : new Money(0.00);
        return BASE.plus(surcharge).plus(fragileSurcharge);
    }
}