package strategy;

/** Runnable demonstration of the Strategy pattern. */
public final class Demo {

    private Demo() {
    }

    public static void main(String[] args) {
        System.out.println("=== Strategy: one shipping calculator, four interchangeable algorithms ===");

        Shipment parcel = new Shipment(4.5, 0.08, 3, true);

        System.out.println("\n-- pick an algorithm by name --");
        ShippingCalculator calculator = new ShippingCalculator(StrategyFactory.of("flat"));
        calculator.quote(parcel);

        System.out.println("\n-- same shipment, other algorithms, calculator untouched --");
        calculator.setStrategy(StrategyFactory.of("per-kg"));
        calculator.quote(parcel);
        calculator.setStrategy(StrategyFactory.of("zone"));
        calculator.quote(parcel);
        calculator.setStrategy(StrategyFactory.of("express"));
        calculator.quote(parcel);

        System.out.println("\n-- a light shipment to zone 1 --");
        calculator.setStrategy(StrategyFactory.of("zone"));
        calculator.quote(new Shipment(0.8, 0.01, 1, false));

        System.out.printf("%nAlgorithm in use: %s%n", calculator.strategy().name());
        System.out.println("Adding a strategy means adding a class, not editing the calculator.");
    }
}