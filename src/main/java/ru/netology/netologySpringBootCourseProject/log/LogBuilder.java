package ru.netology.netologySpringBootCourseProject.log;

import ru.netology.netologySpringBootCourseProject.model.Amount;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

public class LogBuilder {
    private String operationId;
    private String cardNumberFrom;
    private String cardNumberTo;
    private Amount amount;
    private Amount commission;
    private Amount sum;
    private String result;
    private BigDecimal balance;

    public Amount getSum() {
        return sum;
    }

    public LogBuilder setSum(Amount sum) {
        this.sum = sum;
        return this;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public LogBuilder setBalance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }

    public String getOperationId() {
        return operationId;
    }

    public LogBuilder setOperationId(String operationId) {
        this.operationId = operationId;
        return this;
    }

    public LogBuilder setCardNumberFrom(String cardNumberFrom) {
        this.cardNumberFrom = cardNumberFrom;
        return this;
    }

    public LogBuilder setCardNumberTo(String cardNumberTo) {
        this.cardNumberTo = cardNumberTo;
        return this;
    }

    public LogBuilder setAmount(Amount amount) {
        this.amount = amount;
        return this;
    }

    public LogBuilder setCommission(Amount commission) {
        this.commission = commission;
        return this;
    }

    public LogBuilder setResult(String result) {
        this.result = result;
        return this;
    }

    public String getCardNumberFrom() {
        return cardNumberFrom;
    }

    public String getCardNumberTo() {
        return cardNumberTo;
    }

    public Amount getAmount() {
        return amount;
    }

    public Amount getCommission() {
        return commission;
    }

    public String getResult() {
        return result;
    }

}
