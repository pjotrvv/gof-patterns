package strategy;

/** The input every shipping strategy works on. */
public record Shipment(double weightKg, double volumeM3, int zone, boolean fragile) {

    public String describe() {
        return String.format("%.1f kg, %.2f m3, zone %d%s",
                weightKg, volumeM3, zone, fragile ? ", fragile" : "");
    }
}