package strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

/** Small value object so the strategies all return the same type. */
public record Money(BigDecimal amount) {

    public Money(double amount) {
        this(BigDecimal.valueOf(amount));
    }

    public Money {
        amount = amount.setScale(2, RoundingMode.HALF_UP);
    }

    public Money plus(Money other) {
        return new Money(amount.add(other.amount));
    }

    public Money times(BigDecimal factor) {
        return new Money(amount.multiply(factor));
    }

    public String format() {
        return "€ " + amount;
    }
}