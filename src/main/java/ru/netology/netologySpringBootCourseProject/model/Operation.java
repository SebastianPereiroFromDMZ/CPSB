package ru.netology.netologySpringBootCourseProject.model;

import lombok.Getter;
import lombok.Setter;
import ru.netology.netologySpringBootCourseProject.log.LogBuilder;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
public class Operation {
    private String operationId;
    private String cardFromNumber;
    private String cardToNumber;
    private Amount amount;
    private Amount commission;
    private final String secretCode;
    private Amount sum;
    private BigDecimal balance;


    public Operation(LogBuilder logBuilder) {
        this.operationId = logBuilder.getOperationId();
        this.cardFromNumber = logBuilder.getCardNumberFrom();
        this.cardToNumber = logBuilder.getCardNumberTo();
        this.amount = logBuilder.getAmount();
        this.commission = logBuilder.getCommission();
        this.sum = logBuilder.getSum();
        this.balance = logBuilder.getBalance();
        this.secretCode = "0000";
    }

    @Override
    public String toString() {
        return "cardFromNumber='" + cardFromNumber + '\'' +
                ", cardToNumber='" + cardToNumber + '\'' +
                ", amount=" + amount +
                ", commission=" + commission +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return Objects.equals(cardFromNumber, operation.cardFromNumber) && Objects.equals(cardToNumber, operation.cardToNumber) && Objects.equals(amount, operation.amount) && Objects.equals(commission, operation.commission) && Objects.equals(secretCode, operation.secretCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardFromNumber, cardToNumber, amount, commission, secretCode);
    }
}
