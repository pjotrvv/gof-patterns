package strategy;

import java.util.Locale;

/**
 * Creates strategies by name. The {@code switch} lives here - in the factory
 * that picks an algorithm - and not inside {@link ShippingCalculator}, which
 * stays unchanged when a new strategy is added.
 */
public final class StrategyFactory {

    private StrategyFactory() {
    }

    public static CostStrategy of(String name) {
        return switch (name.toLowerCase(Locale.ROOT)) {
            case "flat" -> new FlatRateStrategy();
            case "per-kg" -> new PerKgStrategy();
            case "zone" -> new ZoneBasedStrategy();
            case "express" -> new ExpressStrategy();
            default -> throw new IllegalArgumentException("unknown strategy: " + name);
        };
    }
}