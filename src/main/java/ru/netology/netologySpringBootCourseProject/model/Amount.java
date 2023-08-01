package ru.netology.netologySpringBootCourseProject.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Amount {

    private BigDecimal value;
    private String currency;

    public Amount(BigDecimal value, String currency) {
        this.value = value;
        this.currency = currency;
    }

    public Amount() {
    }

    @Override
    public String toString() {
        return String.format("[%.2f %s]", value, currency);
    }
}
